package org.opendaylight.iotdm.client.util;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.onem2m.xml.protocols.PrimitiveContent;
import org.opendaylight.iotdm.constant.OneM2M;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.*;

/**
 * This main class is to meet Onem2m short-version Json Serialization and Deserialization.
 * To enable null value in json message. Any data model should set to specific null value of type,which is in the OneM2M class
 * For example, if want to set Name of Ae is null in Json.
 * new Ae().setName(Json.BIG_INTEGER_NULL).
 * So this name of Ae attribute will serialized into NULL value. {"nm":null}
 *
 * @author wenxin shi
 */
public class Json {
    private final static String NULL = "NULL";
    private final static String EMPTY = "EMPTY";
    private final static String NORMAL_PREFIX = "NORMAL";

    private static String PACKAGE = "";
    private static Json json = null;

    private static Set<Integer> nullSet = new HashSet<>();

    public static final BigInteger BIG_INTEGER_NULL = new BigInteger(BigInteger.ZERO.toString());
    public static final Boolean BOOLEAN_NULL = new Boolean(false);
    public static final String STRING_NULL = new String();

    static {
        nullSet.add(System.identityHashCode(BIG_INTEGER_NULL));
        nullSet.add(System.identityHashCode(BOOLEAN_NULL));
        nullSet.add(System.identityHashCode(STRING_NULL));
    }

    private static boolean isNull(Object object) {
        return object != null && nullSet.contains(System.identityHashCode(object));
    }

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(PrimitiveContent.class, new PrimitiveContentAdapter())
            .registerTypeAdapter(Boolean.class, BOOLEAN_ADAPTER)
            .registerTypeAdapter(String.class, STRING_ADAPTER)
            .registerTypeAdapter(BigInteger.class, BIG_INTEGER_ADAPTER)
            .create();

    private Json() {
    }

    public static Json newInstance() {
        if (json == null)
            json = new Json();
        return json;
    }

    /**
     * @param object from Data model class generated from OneM2M xsd
     * @return serialized string
     */
    public String toJson(Object object) {
        JsonElement json = gson.toJsonTree(object);
        filter(json);
        return json.toString();
    }

    /**
     * @param str   Json String
     * @param clazz Deserialized class
     * @param <T>
     * @return the instance of specified class
     */

    public <T> T fromJson(String str, Class<T> clazz) {
        try {
            PACKAGE = clazz.getPackage().getName();
            JsonElement root = new JsonParser().parse(str);
            recover(root);
            return gson.fromJson(root, clazz);
        } catch (JsonSyntaxException e) {
            throw new AssertionError(String.format("%s is not json",str));
        } catch (Exception e) {
            throw new AssertionError(String.format("%s can't be deserialized to %s.class", str.trim(), clazz.getSimpleName()));
        }
    }

    /**
     * Type adapter for Boolean, so the Boolean attribute with Json.BOOLEAN_NULL value can be serialized to Null in Json.
     */
    private static TypeAdapter<Boolean> BOOLEAN_ADAPTER = new TypeAdapter<Boolean>() {
        @Override
        public void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else if (isNull(value)) {
                out.value(NULL);
            } else
                out.value(value);
        }

        @Override
        public Boolean read(JsonReader in) throws IOException {
            String str = in.nextString();
            return NULL.equals(str) ? BOOLEAN_NULL : Boolean.valueOf(str);
        }
    };

    /**
     * Type adapter for Big Integer, so the Big Integer attribute with Json.BIG_INTEGER_NULL value can be serialized to Null in Json.
     */
    private static TypeAdapter<BigInteger> BIG_INTEGER_ADAPTER = new TypeAdapter<BigInteger>() {
        @Override
        public void write(JsonWriter out, BigInteger value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else if (isNull(value)) {
                out.value(NULL);
            } else
                out.value(value);
        }

        @Override
        public BigInteger read(JsonReader in) throws IOException {
            String str = in.nextString();
            return NULL.equals(str) ? BIG_INTEGER_NULL : new BigInteger(str);
        }
    };


    /**
     * Type adapter for String, so the String attribute with Json.STRING_NULL value can be serialized to Null in Json.
     */
    private static TypeAdapter<String> STRING_ADAPTER = new TypeAdapter<String>() {
        @Override
        public void write(JsonWriter out, String value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else if (isNull(value)) {
                out.value(NULL);
            } else if (value.isEmpty())
                out.value(EMPTY);
            else
                out.value(NORMAL_PREFIX + value);
        }

        @Override
        public String read(JsonReader in) throws IOException {
            String str = in.nextString();
            if (NULL.equals(str))
                return STRING_NULL;
            else if (EMPTY.equals(str))
                return "";
            else
                return str.substring(NORMAL_PREFIX.length());
        }
    };

    /**
     * Type adapter for PrimitiveContent, so the serialization meet the requirement of OneM2M standard.
     */
    private class PrimitiveContentAdapter implements JsonSerializer<PrimitiveContent>, JsonDeserializer<PrimitiveContent> {
        @Override
        public PrimitiveContent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (!json.isJsonNull()) {
                JsonObject object = json.getAsJsonObject();
                PrimitiveContent pc = new PrimitiveContent();
                if (object.has(OneM2M.ERROR_INDICATOR)) {
                    pc.getAny().add(json);
                } else {
                    List<Object> list = pc.getAny();
                    for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                        String key = entry.getKey();
                        JsonElement value = entry.getValue();

                        String resourceName = key.split(OneM2M.NAMESPACE_DELIMITER)[1];
                        String className = resourceName.substring(0, 1).toUpperCase() + resourceName.substring(1);
                        try {

                            Class<?> clazz = Class.forName(PACKAGE + "." + className);

                            if (value.isJsonObject()) {
                                Object o = context.deserialize(value, clazz);
                                list.add(o);
                            } else if (value.isJsonArray()) {
                                for (JsonElement element : value.getAsJsonArray()) {
                                    Object o = context.deserialize(element, clazz);
                                    list.add(o);
                                }
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return pc;
            }
            return null;
        }

        @Override
        public JsonElement serialize(PrimitiveContent pc, Type typeOfSrc, JsonSerializationContext context) {
            if (pc != null) {
                JsonObject root = new JsonObject();
                List<Object> list = pc.getAny();
                for (Object o : list) {
                    String name = o.getClass().getSimpleName();
                    name = name.toLowerCase().substring(0, 1) + name.substring(1);

                    //The key should include the OneM2M namespace, according to TS-0004 8.4.1
                    String key = OneM2M.NAMESPACE + OneM2M.NAMESPACE_DELIMITER + name;
                    JsonElement value = context.serialize(o, o.getClass());

                    if (root.has(key)) {
                        JsonElement element = root.get(key);
                        if (element.isJsonArray())
                            element.getAsJsonArray().add(value);
                        else if (element.isJsonObject()) {
                            JsonArray jsonArray = new JsonArray();
                            jsonArray.add(element);
                            jsonArray.add(value);
                            root.add(key, jsonArray);
                        }
                    } else {
                        root.add(key, value);
                    }
                }
                return root;
            }
            return null;
        }
    }

    /**
     * Internal mechanism transfer internal Null to Json Null
     *
     * @param root Json Element
     */

    private void filter(JsonElement root) {
        if (root == null) return;
        Queue<JsonElement> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            JsonElement e = q.poll();
            if (e.isJsonObject()) {
                JsonObject object = e.getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    String key = entry.getKey();
                    JsonElement value = entry.getValue();
                    if (value.isJsonPrimitive()) {
                        JsonPrimitive primitive = value.getAsJsonPrimitive();
                        if (primitive.isString()) {
                            String str = value.getAsJsonPrimitive().getAsString();
                            if (str.equals(NULL))
                                object.add(key, JsonNull.INSTANCE);
                            else if (str.equals(EMPTY))
                                object.addProperty(key, "");
                            else
                                object.addProperty(key, str.substring(NORMAL_PREFIX.length()));
                        }
                    } else {
                        q.add(value);
                    }
                }
            } else if (e.isJsonArray()) {
                for (JsonElement el : e.getAsJsonArray()) {
                    q.add(el);
                }
            }
        }
    }

    /**
     * Internal mechanism to recover from Json null into internal indication of Null
     *
     * @param root
     */
    private void recover(JsonElement root) {
        if (root == null) return;
        Queue<JsonElement> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            JsonElement e = q.poll();
            if (e.isJsonObject()) {
                JsonObject object = e.getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    String key = entry.getKey();
                    JsonElement value = entry.getValue();
                    if (value.isJsonNull())
                        object.addProperty(key, NULL);
                    else if (value.isJsonPrimitive()) {
                        if (value.getAsJsonPrimitive().isString()) {
                            String strValue = value.getAsJsonPrimitive().getAsString();
                            if (strValue.equals(""))
                                object.addProperty(key, EMPTY);
                            else
                                object.addProperty(key, NORMAL_PREFIX + strValue);
                        } else
                            object.addProperty(key, value.getAsJsonPrimitive().toString());
                    } else {
                        q.add(value);
                    }
                }
            } else if (e.isJsonArray()) {
                for (JsonElement el : e.getAsJsonArray()) {
                    q.add(el);
                }
            }
        }
    }
}
