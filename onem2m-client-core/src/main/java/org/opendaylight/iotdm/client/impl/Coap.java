package org.opendaylight.iotdm.client.impl;

import org.eclipse.californium.core.coap.Option;
import org.eclipse.californium.core.coap.OptionSet;
import org.onem2m.xml.protocols.PrimitiveContent;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;
import org.opendaylight.iotdm.client.api.Client;
import org.opendaylight.iotdm.client.exception.Onem2mNoOperationError;
import org.opendaylight.iotdm.client.util.Json;
import org.opendaylight.iotdm.constant.OneM2M;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by wenxshi on 10/22/15.
 */
public class Coap implements Client {

//    private CoapServer server=new CoapServer(5111);

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    public Response getOnem2mResponse(org.eclipse.californium.core.coap.Request coapRequest,
                                      Request onem2mRequest) {
        org.eclipse.californium.core.coap.Response coapResponse;
        try {
            coapResponse = coapRequest.waitForResponse(onem2mRequest.getTimeout());
            Objects.requireNonNull(coapResponse);
        } catch (InterruptedException e) {
            throw new AssertionError(e.getMessage());
        } catch (NullPointerException e) {
            throw new AssertionError("Coap response is null");
        }

        return new ResponseBuilder(coapResponse).build();
    }

    @Override
    public Response send(Request request) {
        org.eclipse.californium.core.coap.Request coapRequest = new CoapRequestBuilder(request).build();
        coapRequest.send();
        return getOnem2mResponse(coapRequest, request);
    }

    public static class CoapRequestBuilder {
        org.eclipse.californium.core.coap.Request coapRequest = null;

        public CoapRequestBuilder(Request request) {
            if (request == null) return;

            RequestHelper requestHelper = new RequestHelper(request);
            switch (OneM2M.Operation.getEnum(requestHelper.getOp())) {
                case CREATE:
                    coapRequest = org.eclipse.californium.core.coap.Request.newPost();
                    coapRequest.setPayload(requestHelper.getPayload());
                    break;
                case RETRIEVE:
                    coapRequest = org.eclipse.californium.core.coap.Request.newGet();
                    break;
                case UPDATE:
                    coapRequest = org.eclipse.californium.core.coap.Request.newPut();
                    coapRequest.setPayload(requestHelper.getPayload());
                    break;
                case DELETE:
                    coapRequest = org.eclipse.californium.core.coap.Request.newDelete();
                    break;
                case NOTIFY:
                    coapRequest = org.eclipse.californium.core.coap.Request.newPost();
                    break;
                default:
                    throw new Onem2mNoOperationError();
            }
            coapRequest.setConfirmable(true);
            OptionSet optionSet = coapRequest.getOptions();
            optionSet.setUriPath(OneM2M.Path.toToPathMapping(requestHelper.getPath()));
            optionSet.setUriHost(requestHelper.getHost());
            optionSet.setUriPort(requestHelper.getPort());
            optionSet.setContentFormat(OneM2M.CoAP.MIME.map2Int(requestHelper.getContentMIME()));
            optionSet.setAccept(OneM2M.CoAP.MIME.map2Int(requestHelper.getContentMIME()));
            addQuery(optionSet, requestHelper.getQuery());
            addOption(optionSet, requestHelper.getHeader());
            try {
                coapRequest.setDestination(InetAddress.getByName(requestHelper.getHost()));
                coapRequest.setDestinationPort(requestHelper.getPort());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        private void addQuery(OptionSet os, Map<String, Set<String>> query) {
            for (Map.Entry<String, Set<String>> entry : query.entrySet()) {
                String key = entry.getKey();
                String value = RequestHelper.concatQuery(entry.getValue());
                os.addUriQuery(key + "=" + value);
            }
        }

        private void addOption(OptionSet os, Map<String, Set<String>> map) {
            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                int key = OneM2M.CoAP.Option.map2Int(entry.getKey());
                String value = RequestHelper.concatQuery(entry.getValue());
                os.addOption(new Option(key, value));
            }
        }

        public org.eclipse.californium.core.coap.Request build() {
            return coapRequest;
        }
    }

    public static class ResponseBuilder {
        Response response = null;

        public ResponseBuilder(org.eclipse.californium.core.coap.Response coapResponse) {
            if (coapResponse == null) return;

            OneM2M.ResponseStatusCodes responseStatusCode = null;
            String requestIdentifier = null;
            PrimitiveContent primitiveContent = null;
            String to = null;
            String from = null;
            OneM2M.Time originatingTimestamp = null;
            OneM2M.Time resultExpirationTimestamp = null;
            OneM2M.StdEventCats eventCategory = null;

            OptionSet os = coapResponse.getOptions();
            for (Option option : os.asSortedList()) {
                int key = option.getNumber();
                switch (key) {
                    case OneM2M.CoAP.Option.ONEM2M_RSC:
                        responseStatusCode = OneM2M.ResponseStatusCodes.getEnum(BigInteger.valueOf(option.getIntegerValue()));
                        break;
                    case OneM2M.CoAP.Option.ONEM2M_RQI:
                        requestIdentifier = option.getStringValue();
                        break;
                    case OneM2M.CoAP.Option.ONEM2M_FR:
                        from = option.getStringValue();
                        break;
                    case OneM2M.CoAP.Option.ONEM2M_OT:
                        originatingTimestamp = new OneM2M.Time(option.getStringValue());
                        break;
                    case OneM2M.CoAP.Option.ONEM2M_RSET:
                        resultExpirationTimestamp = new OneM2M.Time(option.getStringValue());
                        break;
                    case OneM2M.CoAP.Option.ONEM2M_EC:
                        eventCategory = OneM2M.StdEventCats.getEnum(new BigInteger(option.getStringValue()));
                }
            }
            String content = coapResponse.getPayloadString();
            primitiveContent = Json.newInstance().fromJson(content, PrimitiveContent.class);
            response = new Response(responseStatusCode, requestIdentifier, primitiveContent, to, from, originatingTimestamp, resultExpirationTimestamp, eventCategory);
        }

        public Response build() {
            return response;
        }
    }
}
