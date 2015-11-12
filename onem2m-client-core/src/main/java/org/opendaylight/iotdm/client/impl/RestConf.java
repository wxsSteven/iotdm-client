package org.opendaylight.iotdm.client.impl;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.junit.Assert;
import org.opendaylight.iotdm.constant.OneM2M;

import java.util.Base64;

/**
 * Created by wenxshi on 11/3/15.
 */
public class RestConf {
    public final static String SCHEMA="http";
    public final static String HOST="localhost";
    public final static String PORT="8181";
    public final static String PROVISIONING_PATH ="/restconf/operations/onem2m:onem2m-cse-provisioning";
    public final static String CLEANUP_STORE_PATH="/restconf/operations/onem2m:onem2m-cleanup-store";


    public final static String URL_PROVISON="http://localhost:8181/restconf/operations/onem2m:onem2m-cse-provisioning";
    public final static String URL_CLEAR="http://localhost:8181/restconf/operations/onem2m:onem2m-cleanup-store";
    public final static String PAYLOAD="{    \"input\": {\n" +
            "        \"onem2m-primitive\": [\n" +
            "           {\n" +
            "                \"name\": \"CSE_ID\",\n" +
            "                \"value\": \"InCSE1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"CSE_TYPE\",\n" +
            "                \"value\": \"IN-CSE\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";
    public final static String USERNAME="admin";
    public final static String PASSWORD="admin";

    private static HttpClient httpClient=new HttpClient();

    public static String send (String url,String payload,String username,String password){
        Assert.assertNotNull(url);
        Assert.assertNotNull(username);
        Assert.assertNotNull(password);
        try {
            httpClient.start();
            Request request = httpClient.newRequest(url).method(Http.CREATE_IN_HTTP);
            request.content(new StringContentProvider(payload));
            request.accept(OneM2M.Http.MIME.APPLICATION_JSON);
            request.header(OneM2M.Http.Header.CONTENT_TYPE,OneM2M.Http.MIME.APPLICATION_JSON);
            String authString = username + ":" + password;
            request.header("Authorization", "Basic " + Base64.getEncoder().encodeToString(authString.getBytes()));
            ContentResponse contentResponse=request.send();
            String response=contentResponse.getContentAsString();
            httpClient.stop();
            return response;
        } catch (Exception e) {
            throw new AssertionError(e.getMessage());
        }
    }

    public static String defaultProvision(){
        return send(URL_PROVISON,PAYLOAD,USERNAME,PASSWORD);
    }

    public static String defaultClear(){
        return send(URL_CLEAR,"",USERNAME,PASSWORD);
    }

    public static String defaultProvision(String host){
        StringBuilder sb=new StringBuilder();
        sb.append(SCHEMA);
        sb.append("://");
        sb.append(host);
        sb.append(":");
        sb.append(PORT);
        sb.append(PROVISIONING_PATH);
        String uri=sb.toString();
        return send(uri,PAYLOAD,USERNAME,PASSWORD);
    }

    public static String defaultClear(String host){
        StringBuilder sb=new StringBuilder();
        sb.append(SCHEMA);
        sb.append("://");
        sb.append(host);
        sb.append(PORT);
        sb.append(CLEANUP_STORE_PATH);
        return send(sb.toString(),PAYLOAD,USERNAME,PASSWORD);
    }
}
