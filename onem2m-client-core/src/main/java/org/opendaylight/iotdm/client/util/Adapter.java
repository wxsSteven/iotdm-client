package org.opendaylight.iotdm.client.util;

import org.junit.Assert;
import org.onem2m.xml.protocols.Attribute;
import org.onem2m.xml.protocols.FilterCriteria;
import org.onem2m.xml.protocols.ResponseTypeInfo;
import org.onem2m.xml.protocols.Rqp;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.constant.OneM2M;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wenxshi on 11/2/15.
 */
public class Adapter {
    private String host;
    private int port;
    private long timeout;

    private String contentMIME;
    private String acceptMIME;
    private String payload;
    private BigInteger op;
    private String path;
    private HashMap<String, Set<String>> query = new HashMap<>();
    private HashMap<String, Set<String>> header = new HashMap<>();
    private HashMap<String, Set<String>> fragment = new HashMap<>();

    public Adapter(Request request) {
        host = request.getHost();
        port = request.getPort();
        timeout = request.getTimeout();
        contentMIME = request.getContentMIME();
        acceptMIME = request.getAcceptMIME();

        Assert.assertNotNull(host);
        Assert.assertNotNull(contentMIME);
        Assert.assertNotNull(acceptMIME);

        Rqp requestPrimitive = request.getRequestPrimitive();
        Assert.assertNotNull(requestPrimitive);

        op = requestPrimitive.getOp();
        Assert.assertNotNull("operation should not be null",op);

        path = requestPrimitive.getTo();
        Assert.assertNotNull("path should not be null",path);


        adaptQuery(requestPrimitive);
        adaptFragment(requestPrimitive);
        adaptHeader(requestPrimitive);
        adaptPayload(requestPrimitive);
    }

    private void adaptQuery(Rqp requestPrimitive) {
        if (requestPrimitive.getRt() != null)
            add(query, OneM2M.Name.RESPONSE_TYPE_VALUE, requestPrimitive.getRt().getRtv());
        add(query, OneM2M.Name.RESOURCE_TYPE, requestPrimitive.getTy());
        add(query, OneM2M.Name.RESULT_PERSISTENCE, requestPrimitive.getRp());
        add(query, OneM2M.Name.RESULT_CONTENT, requestPrimitive.getRcn());
        add(query, OneM2M.Name.DELIVERY_AGGREGATION, requestPrimitive.getDrt());

        if (requestPrimitive.getFc() != null) {
            FilterCriteria fc = requestPrimitive.getFc();
            add(query, OneM2M.Name.CREATED_BEFORE, fc.getCrb());
            add(query, OneM2M.Name.CREATED_AFTER, fc.getCra());
            add(query, OneM2M.Name.MODIFIED_SINCE, fc.getMs());
            add(query, OneM2M.Name.UNMODIFIED_SINCE, fc.getUs());
            add(query, OneM2M.Name.STATE_TAG_SMALLER, fc.getSts());
            add(query, OneM2M.Name.STATE_TAG_BIGGER, fc.getStb());
            add(query, OneM2M.Name.EXPIRE_BEFORE, fc.getExb());
            add(query, OneM2M.Name.EXPIRE_AFTER, fc.getExa());
            for (String value : fc.getLbl()) {
                add(query, OneM2M.Name.LABELS, value);
            }

            add(query, OneM2M.Name.RESOURCE_TYPE, fc.getTy());
            add(query, OneM2M.Name.SIZE_ABOVE, fc.getSza());
            add(query, OneM2M.Name.SIZE_BELOW, fc.getSzb());

            for (String value : fc.getCty()) {
                add(query, OneM2M.Name.CONTENT_TYPE, value);
            }

            add(query, OneM2M.Name.LIMIT, fc.getLim());

            if (fc.getAtr() != null) {
                for (Attribute attribute : fc.getAtr()) {
                    add(query, attribute.getNm(), attribute.getVal());
                }
            }
            add(query, OneM2M.Name.FILTER_USAGE, fc.getFu());
        }
        add(query, OneM2M.Name.DISCOVERY_RESULT_TYPE, requestPrimitive.getDrt());
    }

    private void add(HashMap<String, Set<String>> map, String name, Object object) {
        if (object == null)
            return;
        if (!map.containsKey(name))
            map.put(name, new HashSet<>());
        map.get(name).add(String.valueOf(object));
    }

    private void adaptFragment(Rqp requestPrimitive) {

    }

    private void adaptHeader(Rqp requestPrimitive) {
        add(header, OneM2M.Name.FROM, requestPrimitive.getFr());
        add(header, OneM2M.Name.REQUEST_IDENTIFIER, requestPrimitive.getRqi());
        add(header, OneM2M.Name.NAME, requestPrimitive.getNm());
        add(header, OneM2M.Name.GROUP_ID, requestPrimitive.getGid());
        if (requestPrimitive.getRt() != null) {
            ResponseTypeInfo rti = requestPrimitive.getRt();
            if (!rti.getNu().isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String ss : rti.getNu()) {
                    sb.append("&");
                    sb.append(ss);
                }
                add(header, OneM2M.Name.RESPONSE_TYPE_VALUE, sb.substring("&".length()));
            }
        }
        add(header, OneM2M.Name.ORIGINATING_TIMESTAMP, requestPrimitive.getOt());
        add(header, OneM2M.Name.RESULT_EXPIRATION_TIMESTAMP, requestPrimitive.getRset());
        add(header, OneM2M.Name.REQUEST_EXPIRATION_TIMESTAMP, requestPrimitive.getRqet());
        add(header, OneM2M.Name.OPERATION_EXECUTION_TIME, requestPrimitive.getOet());
        add(header, OneM2M.Name.EVENT_CATEGORY, requestPrimitive.getEc());
    }

    private void adaptPayload(Rqp requestPrimitive) {
        payload = Json.newInstance().toJson(requestPrimitive.getPc());
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public long getTimeout() {
        return timeout;
    }

    public String getContentMIME() {
        return contentMIME;
    }

    public String getAcceptMIME() {
        return acceptMIME;
    }

    public String getPayload() {
        return payload;
    }

    public BigInteger getOp() {
        return op;
    }

    public String getPath() {
        return path;
    }

    public HashMap<String, Set<String>> getQuery() {
        return query;
    }

    public HashMap<String, Set<String>> getHeader() {
        return header;
    }

    public HashMap<String, Set<String>> getFragment() {
        return fragment;
    }

    public static String concatQuery(Collection<String> collection) {
        StringBuilder sb = new StringBuilder();
        for (String str : collection) {
            if (str != null) {
                sb.append("+");
                sb.append(str);
            }
        }
        return sb.length() > 0 ? sb.substring("+".length()) : "";
    }
}
