package org.opendaylight.iotdm.client.impl;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpFields;
import org.onem2m.xml.protocols.PrimitiveContent;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;
import org.opendaylight.iotdm.client.api.Client;
import org.opendaylight.iotdm.client.exception.Onem2mNoOperationError;
import org.opendaylight.iotdm.client.util.Json;
import org.opendaylight.iotdm.constant.OneM2M;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Created by wenxshi on 3/30/15.
 */
public class Http implements Client {

    public static final int PORT = 8989;
    public static final String CREATE_IN_HTTP = "post";
    public static final String RETRIEVE_IN_HTTP = "get";
    public static final String UPDATE_IN_HTTP = "put";
    public static final String DELETE_IN_HTTP = "delete";
    public static final String NOTIFY_IN_HTTP = "post";

    private static HttpClient httpClient = new HttpClient();
//    private static Server httpServer=new Server(PORT);


    @Override
    public void start() {
        try {
            httpClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            httpClient.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response send(Request request) {

        org.eclipse.jetty.client.api.Request httpRequest = new HttpRequestBuilder(request).build();
        ContentResponse contentResponse;
        try {
            contentResponse = httpRequest.send();
        } catch (Exception e) {
            throw new AssertionError(e.getMessage());
        }

        Response response = new ResponseBuilder(contentResponse).build();
        return response;
    }

    public static class HttpRequestBuilder {
        org.eclipse.jetty.client.api.Request httpRequest;

        public HttpRequestBuilder(Request request) {
            if (request == null) return;

            RequestHelper requestHelper = new RequestHelper(request);
            httpRequest = httpClient.newRequest(requestHelper.getHost(), requestHelper.getPort())
                    .timeout(requestHelper.getTimeout(), TimeUnit.MILLISECONDS);

            requestHelper.getQuery().remove(OneM2M.Name.RESOURCE_TYPE);
            addQuery(httpRequest, requestHelper.getQuery());
            addHeader(httpRequest.getHeaders(), requestHelper.getHeader());
            httpRequest.accept(requestHelper.getAcceptMIME());
            httpRequest.path(OneM2M.Path.toToPathMapping(requestHelper.getPath()));

            switch (OneM2M.Operation.getEnum(requestHelper.getOp())) {
                case CREATE:
                    httpRequest.method(CREATE_IN_HTTP);
                    httpRequest.content(new StringContentProvider(requestHelper.getPayload()));
                    httpRequest.header(OneM2M.Http.Header.CONTENT_TYPE, String.format("%s;%s=%s", requestHelper.getContentMIME(), OneM2M.Name.RESOURCE_TYPE, request.getRequestPrimitive().getTy()));
                    break;
                case RETRIEVE:
                    httpRequest.method(RETRIEVE_IN_HTTP);
                    httpRequest.header(OneM2M.Http.Header.CONTENT_TYPE, requestHelper.getContentMIME());
                    break;
                case UPDATE:
                    httpRequest.method(UPDATE_IN_HTTP);
                    httpRequest.content(new StringContentProvider(requestHelper.getPayload()));
                    httpRequest.header(OneM2M.Http.Header.CONTENT_TYPE, requestHelper.getContentMIME());
                    break;
                case DELETE:
                    httpRequest.method(DELETE_IN_HTTP);
                    httpRequest.header(OneM2M.Http.Header.CONTENT_TYPE, requestHelper.getContentMIME());
                    break;
                case NOTIFY:
                    httpRequest.method(NOTIFY_IN_HTTP);
                    httpRequest.header(OneM2M.Http.Header.CONTENT_TYPE, requestHelper.getContentMIME());
                    break;
                default:
                    throw new Onem2mNoOperationError();
            }
        }

        private void addHeader(HttpFields httpFields, Map<String, Set<String>> map) {
            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                String key = OneM2M.Http.Header.map(entry.getKey());
                String value = RequestHelper.concatQuery(entry.getValue());
                httpFields.add(key, value);
            }
        }

        private void addQuery(org.eclipse.jetty.client.api.Request request, Map<String, Set<String>> map) {
            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = RequestHelper.concatQuery(entry.getValue());
                request.param(key, value);
            }
        }

        public org.eclipse.jetty.client.api.Request build() {
            return httpRequest;
        }
    }

    public static class ResponseBuilder {
        private Response response = null;

        public ResponseBuilder(ContentResponse contentResponse) {
            if (contentResponse == null) return;

            OneM2M.ResponseStatusCodes responseStatusCode = null;
            String requestIdentifier = null;
            PrimitiveContent primitiveContent = null;
            String to = null;
            String from = null;
            OneM2M.Time originatingTimestamp = null;
            OneM2M.Time resultExpirationTimestamp = null;
            OneM2M.StdEventCats eventCategory = null;

            HttpFields responseHeader = contentResponse.getHeaders();
            for (String key : responseHeader.getFieldNamesCollection()) {
                switch (key) {
                    case OneM2M.Http.Header.X_M2M_RSC:
                        responseStatusCode = OneM2M.ResponseStatusCodes.getEnum(BigInteger.valueOf(responseHeader.getLongField(key)));
                        break;
                    case OneM2M.Http.Header.X_M2M_RI:
                        requestIdentifier = responseHeader.get(key);
                        break;
                    case OneM2M.Http.Header.X_M2M_ORIGIN:
                        from = responseHeader.get(key);
                        break;
                    case OneM2M.Http.Header.X_M2M_OT:
                        originatingTimestamp = new OneM2M.Time(responseHeader.get(key));
                        break;
                    case OneM2M.Http.Header.X_M2M_RST:
                        resultExpirationTimestamp = new OneM2M.Time(responseHeader.get(key));
                        break;
                    case OneM2M.Http.Header.X_M2M_EC:
                        eventCategory = OneM2M.StdEventCats.getEnum(new BigInteger(responseHeader.get(key)));
                }
            }
            String content = contentResponse.getContentAsString();
            try {
                primitiveContent = Json.newInstance().fromJson(content, PrimitiveContent.class);
            } catch (Exception e) {
                System.err.println("Failed to decode primitive content: " + e);
            }

            response = new Response(responseStatusCode, requestIdentifier, primitiveContent, to, from, originatingTimestamp, resultExpirationTimestamp, eventCategory);
        }

        public Response build() {
            return response;
        }
    }
}

