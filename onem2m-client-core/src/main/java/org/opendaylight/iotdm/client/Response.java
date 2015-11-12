package org.opendaylight.iotdm.client;

import org.onem2m.xml.protocols.PrimitiveContent;
import org.opendaylight.iotdm.constant.OneM2M;

/**
 * Created by wenxshi on 11/5/15.
 */
public class Response {
    private OneM2M.ResponseStatusCodes responseStatusCode;
    private String requestIdentifier;
    private PrimitiveContent primitiveContent;
    private String to;
    private String from;
    private OneM2M.Time originatingTimestamp;
    private OneM2M.Time resultExpirationTimestamp;
    private OneM2M.StdEventCats eventCategory;


    public Response(OneM2M.ResponseStatusCodes responseStatusCode, String requestIdentifier, PrimitiveContent primitiveContent, String to, String from, OneM2M.Time originatingTimestamp, OneM2M.Time resultExpirationTimestamp, OneM2M.StdEventCats eventCategory) {
        this.responseStatusCode = responseStatusCode;
        this.requestIdentifier = requestIdentifier;
        this.primitiveContent = primitiveContent;
        this.to = to;
        this.from = from;
        this.originatingTimestamp = originatingTimestamp;
        this.resultExpirationTimestamp = resultExpirationTimestamp;
        this.eventCategory = eventCategory;
    }

    public OneM2M.ResponseStatusCodes getResponseStatusCode() {
        return responseStatusCode;
    }

    public String getRequestIdentifier() {
        return requestIdentifier;
    }

    public PrimitiveContent getPrimitiveContent() {
        return primitiveContent;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public OneM2M.Time getOriginatingTimestamp() {
        return originatingTimestamp;
    }

    public OneM2M.Time getResultExpirationTimestamp() {
        return resultExpirationTimestamp;
    }

    public OneM2M.StdEventCats getEventCategory() {
        return eventCategory;
    }
}
