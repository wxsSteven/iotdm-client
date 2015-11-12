package org.opendaylight.iotdm.client.impl;

import org.eclipse.californium.core.coap.Option;
import org.eclipse.californium.core.coap.OptionSet;
import org.onem2m.xml.protocols.PrimitiveContent;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;
import org.opendaylight.iotdm.client.api.Client;
import org.opendaylight.iotdm.client.exception.NoOperationError;
import org.opendaylight.iotdm.client.util.Adapter;
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

    @Override
    public Response send(Request request) {
        Adapter adapt = new Adapter(request);
        org.eclipse.californium.core.coap.Request  coapRequest;
        switch (OneM2M.Operation.getEnum(adapt.getOp())) {
            case CREATE:
                coapRequest = org.eclipse.californium.core.coap.Request.newPost();
                coapRequest.setPayload(adapt.getPayload());
                break;
            case RETRIEVE:
                coapRequest = org.eclipse.californium.core.coap.Request.newGet();
                break;
            case UPDATE:
                coapRequest = org.eclipse.californium.core.coap.Request.newPut();
                coapRequest.setPayload(adapt.getPayload());
                break;
            case DELETE:
                coapRequest = org.eclipse.californium.core.coap.Request.newDelete();
                break;
            case NOTIFY:
                coapRequest = org.eclipse.californium.core.coap.Request.newPost();
                break;
            default:
                throw new NoOperationError();
        }
        coapRequest.setConfirmable(true);
        OptionSet optionSet = coapRequest.getOptions();
        optionSet.setUriPath(OneM2M.Path.toToPathMapping(adapt.getPath()));
        optionSet.setUriHost(adapt.getHost());
        optionSet.setUriPort(adapt.getPort());
        addQuery(optionSet, adapt.getQuery());
        addOption(optionSet, adapt.getHeader());
        try {
            coapRequest.setDestination(InetAddress.getByName(adapt.getHost()));
            coapRequest.setDestinationPort(adapt.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        coapRequest.send();
        org.eclipse.californium.core.coap.Response coapResponse;
        try {
            coapResponse = coapRequest.waitForResponse(adapt.getTimeout());
            Objects.requireNonNull(coapResponse);
        } catch (InterruptedException e) {
            throw new AssertionError(e.getMessage());
        }catch (NullPointerException e){
            throw new AssertionError("Coap response is null");
        }

        return getResponse(coapResponse);
    }

    private Response getResponse(org.eclipse.californium.core.coap.Response coapResponse) {
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
            int key = option.getIntegerValue();
            switch (key) {
                case OneM2M.CoAP.Option.ONEM2M_RSC:
                    responseStatusCode=OneM2M.ResponseStatusCodes.getEnum(BigInteger.valueOf(option.getIntegerValue()));
                    break;
                case OneM2M.CoAP.Option.ONEM2M_RQI:
                    requestIdentifier=option.getStringValue();
                    break;
                case OneM2M.CoAP.Option.ONEM2M_FR:
                    from=option.getStringValue();
                    break;
                case OneM2M.CoAP.Option.ONEM2M_OT:
                    originatingTimestamp=new OneM2M.Time(option.getStringValue());
                    break;
                case OneM2M.CoAP.Option.ONEM2M_RSET:
                    resultExpirationTimestamp=new OneM2M.Time(option.getStringValue());
                    break;
                case OneM2M.CoAP.Option.ONEM2M_EC:
                    eventCategory=OneM2M.StdEventCats.getEnum(new BigInteger(option.getStringValue()));
            }
        }
        String content = coapResponse.getPayloadString();
        primitiveContent = Json.newInstance().fromJson(content, PrimitiveContent.class);
        return new Response(responseStatusCode, requestIdentifier, primitiveContent, to, from, originatingTimestamp, resultExpirationTimestamp, eventCategory);
    }

    private void addQuery(OptionSet os, Map<String, Set<String>> query) {
        for (Map.Entry<String, Set<String>> entry : query.entrySet()) {
            String key = entry.getKey();
            String value = Adapter.concatQuery(entry.getValue());
            os.addUriQuery(key + "=" + value);
        }
    }

    private void addOption(OptionSet os, Map<String, Set<String>> map) {
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            int key = OneM2M.CoAP.Option.map2Int(entry.getKey());
            String value = Adapter.concatQuery(entry.getValue());
            os.addOption(new Option(key, value));
        }
    }
}
