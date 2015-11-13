package org.opendaylight.iotdm.client;

import org.onem2m.xml.protocols.PrimitiveContent;
import org.opendaylight.iotdm.constant.OneM2M;

/**
 * Created by wenxshi on 11/5/15.
 */

/**
 * This class present the onem2m response sent back from CSE
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

    /**
     * @param responseStatusCode        (ACCEPTED,
     *                                  OK,
     *                                  CREATED,
     *                                  DELETED,
     *                                  UPDATED,
     *                                  BAD_REQUEST,
     *                                  NOT_FOUND,
     *                                  OPERATION_NOT_ALLOWED,
     *                                  REQUEST_TIMEOUT,
     *                                  SUBSCRIPTION_CREATOR_HAS_NO_PRIVILEGE,
     *                                  CONTENTS_UNACCEPTABLE,
     *                                  ACCESS_DENIED,
     *                                  GROUP_REQUEST_IDENTIFIER_EXISTS,
     *                                  CONFLICT,
     *                                  BAD_REQUEST,
     *                                  NOT_FOUND,
     *                                  OPERATION_NOT_ALLOWED,
     *                                  REQUEST_TIMEOUT,
     *                                  SUBSCRIPTION_CREATOR_HAS_NO_PRIVILEGE,
     *                                  CONTENTS_UNACCEPTABLE,
     *                                  ACCESS_DENIED,
     *                                  GROUP_REQUEST_IDENTIFIER_EXISTS,
     *                                  CONFLICT,
     *                                  EXTERNAL_OBJECT_NOT_REACHABLE,
     *                                  EXTERNAL_OBJECT_NOT_FOUND,
     *                                  MAX_NUMBER_OF_MEMBER_EXCEEDED,
     *                                  MEMBER_TYPE_INCONSISTENT,
     *                                  MGMT_SESSION_CANNOT_BE_ESTABLISHED,
     *                                  MGMT_SESSION_ESTABLISHMENT_TIMEOUT,
     *                                  INVALID_CMDTYPE,
     *                                  INVALID_ARGUMENTS,
     *                                  INSUFFICIENT_ARGUMENTS,
     *                                  MGMT_CONVERSION_ERROR,
     *                                  MGMT_CANCELLATION_FAILED,
     *                                  ALREADY_COMPLETE,
     *                                  MGMT_COMMAND_NOT_CANCELLABLE)
     * @param requestIdentifier         Request ID (used to match Request)
     * @param primitiveContent          Details resource info
     * @param to
     * @param from
     * @param originatingTimestamp      Response created time.
     * @param resultExpirationTimestamp This result expiration time.
     * @param eventCategory             (Immediate,
     *                                  BestEffort,
     *                                  Latest)
     */
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

    /**
     * Get response code
     *
     * @return ACCEPTED,
     * OK,
     * CREATED,
     * DELETED,
     * UPDATED,
     * BAD_REQUEST,
     * NOT_FOUND,
     * OPERATION_NOT_ALLOWED,
     * REQUEST_TIMEOUT,
     * SUBSCRIPTION_CREATOR_HAS_NO_PRIVILEGE,
     * CONTENTS_UNACCEPTABLE,
     * ACCESS_DENIED,
     * GROUP_REQUEST_IDENTIFIER_EXISTS,
     * CONFLICT,
     * BAD_REQUEST,
     * NOT_FOUND,
     * OPERATION_NOT_ALLOWED,
     * REQUEST_TIMEOUT,
     * SUBSCRIPTION_CREATOR_HAS_NO_PRIVILEGE,
     * CONTENTS_UNACCEPTABLE,
     * ACCESS_DENIED,
     * GROUP_REQUEST_IDENTIFIER_EXISTS,
     * CONFLICT,
     * EXTERNAL_OBJECT_NOT_REACHABLE,
     * EXTERNAL_OBJECT_NOT_FOUND,
     * MAX_NUMBER_OF_MEMBER_EXCEEDED,
     * MEMBER_TYPE_INCONSISTENT,
     * MGMT_SESSION_CANNOT_BE_ESTABLISHED,
     * MGMT_SESSION_ESTABLISHMENT_TIMEOUT,
     * INVALID_CMDTYPE,
     * INVALID_ARGUMENTS,
     * INSUFFICIENT_ARGUMENTS,
     * MGMT_CONVERSION_ERROR,
     * MGMT_CANCELLATION_FAILED,
     * ALREADY_COMPLETE,
     * MGMT_COMMAND_NOT_CANCELLABLE
     */
    public OneM2M.ResponseStatusCodes getResponseStatusCode() {
        return responseStatusCode;
    }

    /**
     * RequestIdentifer to match request
     *
     * @return
     */
    public String getRequestIdentifier() {
        return requestIdentifier;
    }

    /**
     * Resource info
     *
     * @return
     */
    public PrimitiveContent getPrimitiveContent() {
        return primitiveContent;
    }

    /**
     * Optional:ID of the Originator or the Transit CSE
     *
     * @return
     */
    public String getTo() {
        return to;
    }

    /**
     * Optional:ID of the Receiver.
     *
     * @return
     */
    public String getFrom() {
        return from;
    }

    /**
     * Originating timestamp of when the response was built.
     *
     * @return
     */
    public OneM2M.Time getOriginatingTimestamp() {
        return originatingTimestamp;
    }

    /**
     * Indicate when this response is expired.
     *
     * @return
     */
    public OneM2M.Time getResultExpirationTimestamp() {
        return resultExpirationTimestamp;
    }

    /**
     * Indicates the event category that should be used to handle this response
     *
     * @return
     */
    public OneM2M.StdEventCats getEventCategory() {
        return eventCategory;
    }
}
