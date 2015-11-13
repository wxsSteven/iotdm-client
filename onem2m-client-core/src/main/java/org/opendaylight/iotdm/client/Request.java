package org.opendaylight.iotdm.client;

import org.onem2m.xml.protocols.*;
import org.opendaylight.iotdm.constant.OneM2M;

import java.math.BigInteger;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by wenxshi on 11/5/15.
 */
public class Request {
    private Rqp requestPrimitive = new Rqp();
    private String host = "localhost";
    private int port = 8282;
    private String contentMIME = "application/json";
    private String acceptMIME = contentMIME;
    private long timeout = 1000;

    public Request() {
        requestPrimitive.setFc(new FilterCriteria());
        requestPrimitive.setRt(new ResponseTypeInfo());
        requestPrimitive.setPc(new PrimitiveContent());
    }

    /**
     * Destination port number
     *
     * @param port
     * @return
     */
    public Request port(int port) {
        this.port = port;
        return this;
    }

    /**
     * Destination host
     *
     * @param host
     * @return
     */

    public Request host(String host) {
        Objects.requireNonNull(host);
        this.host = host;
        return this;
    }

    /**
     * Request MIME info (application/json or application/xml)
     *
     * @param contentMIME
     * @return
     */

    public Request contentMIME(String contentMIME) {
        Objects.requireNonNull(contentMIME);
        this.contentMIME = contentMIME;
        return this;
    }

    /**
     * Required Response MIME (application/json or application/xml)
     *
     * @param acceptMIME
     * @return
     */

    public Request acceptMIME(String acceptMIME) {
        Objects.requireNonNull(acceptMIME);
        this.acceptMIME = acceptMIME;
        return this;
    }

    /**
     * Request time expiration length. If onem2m-client haven't got response within the timeout.
     * Onem2m-client will seems the request unsuccessfully.
     *
     * @param timeout
     * @param timeUnit
     * @return
     */
    public Request timeout(long timeout, TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit);
        this.timeout = timeUnit.toMillis(timeout);
        return this;
    }


    /**
     * Indicate the operation to be executed at the Receiver
     *
     * @param operation (CREATE,RETRIEVE,UPDATE,DELETE,NOTIFY)
     * @return
     */
    public Request operation(OneM2M.Operation operation) {
        Objects.requireNonNull(operation);
        requestPrimitive.setOp(operation.value());
        return this;
    }

    /**
     * Destination resource path
     *
     * @param to
     * @return
     */
    public Request to(String to) {
        Objects.requireNonNull(to);
        requestPrimitive.setTo(to);
        return this;
    }

    /**
     * Identifier representing the Originator.
     *
     * @param from
     * @return
     */
    public Request from(String from) {
        Objects.requireNonNull(from);
        requestPrimitive.setFr(from);
        return this;
    }

    /**
     * Request ID (used to match response)
     *
     * @param requestIdentifier
     * @return
     */
    public Request requestIdentifier(String requestIdentifier) {
        Objects.requireNonNull(requestIdentifier);
        requestPrimitive.setRqi(requestIdentifier);
        return this;
    }

    /**
     * Onem2m resourceType only when Create Operation need to specified
     *
     * @param resourceType (accessControlPolicy,
     *                     AE,
     *                     container,
     *                     contentInstance,
     *                     CSEBase,
     *                     delivery,
     *                     eventConfig,
     *                     execInstance,
     *                     group,
     *                     locationPolicy,
     *                     m2mServiceSubscriptionProfile,
     *                     mgmtCmd,
     *                     mgmtObj,
     *                     node,
     *                     pollingChannel,
     *                     remoteCSE,
     *                     request,
     *                     schedule,
     *                     serviceSubscribedAppRule,
     *                     serviceSubscribedNode,
     *                     statsCollect,
     *                     statsConfig,
     *                     subscription,
     *                     accessControlPolicyAnnc,
     *                     AEAnnc,containerAnnc,
     *                     contentInstanceAnnc,
     *                     groupAnnc,
     *                     locationPolicyAnnc,
     *                     mgmtObjAnnc,
     *                     nodeAnnc,
     *                     remoteCSEAnnc,
     *                     scheduleAnnc)
     * @return
     */

    public Request resourceType(OneM2M.ResourceType resourceType) {
        Objects.requireNonNull(resourceType);
        requestPrimitive.setTy(resourceType.value());
        return this;
    }

    /**
     * The name of resource to be created in creation operation
     *
     * @param name
     * @return
     */
    public Request name(String name) {
        Objects.requireNonNull(name);
        requestPrimitive.setNm(name);
        return this;
    }

    /**
     * Resource detail to be created or updated
     *
     * @param object
     * @return
     */
    public Request addPrimitiveContent(Object object) {
        Objects.requireNonNull(object);
        requestPrimitive.getPc().getAny().add(object);
        return this;
    }


    public Request primitiveContent(PrimitiveContent primitiveContent) {
        Objects.requireNonNull(primitiveContent);
        requestPrimitive.setPc(primitiveContent);
        return this;
    }

    /**
     * Required when role based access control is applied
     *
     * @param role
     * @return
     */
    public Request role(Object role) {
        Objects.requireNonNull(role);
        requestPrimitive.setRol(role);
        return this;
    }

    /**
     * Originating timestamp of when the request was built
     *
     * @param originatingTimestamp (Onem2m timestamp format should be compliant)
     * @return
     */
    public Request originatingTimestamp(OneM2M.Time originatingTimestamp) {
        Objects.requireNonNull(originatingTimestamp);
        requestPrimitive.setOt(originatingTimestamp.toString());
        return this;
    }

    /**
     * The timestamp is for request expiration
     *
     * @param requestExpirationTimestamp (Onem2m timestamp format should be compliant)
     * @return
     */
    public Request requestExpirationTimestamp(OneM2M.Time requestExpirationTimestamp) {
        Objects.requireNonNull(requestExpirationTimestamp);
        requestPrimitive.setRqet(requestExpirationTimestamp.toString());
        return this;
    }

    /**
     * The timestamp is for result expiration
     * Example usage of the result expiration timestamp:
     * An Originator indicates when result messages (including delay-tolerant) should expire and informs message scheduling/prioritization.
     * It can be used to set the maximum allowed total request/result message sequence round trip deadline
     *
     * @param resultExpirationTimestamp (Onem2m timestamp format should be compliant)
     * @return
     */
    public Request resultExpirationTimestamp(OneM2M.Time resultExpirationTimestamp) {
        Objects.requireNonNull(resultExpirationTimestamp);
        requestPrimitive.setRset(resultExpirationTimestamp.toString());
        return this;
    }

    /**
     * Indicates the time when the specified operation Operation is to be executed by the target CSE
     *
     * @param operationExecutionTime
     * @return
     */
    public Request operationExecutionTime(OneM2M.Time operationExecutionTime) {
        Objects.requireNonNull(operationExecutionTime);
        requestPrimitive.setOet(operationExecutionTime.toString());
        return this;
    }

    /**
     * ResourceType can be blockingRequest,nonBlockingRequestSynch,nonBlockingRequestAsynch
     * Currently only blockingRequest is supported.
     *
     * @param responseTypeValue
     * @return
     */
    public Request responseTypeValue(OneM2M.ResponseType responseTypeValue) {
        Objects.requireNonNull(responseTypeValue);
        requestPrimitive.getRt().setRtv(responseTypeValue.value());
        return this;
    }

    /**
     * If request is nonBlockingRequestAsynch,
     * the response will be sent back by notify operation through this notificationUri
     *
     * @param notificationURI
     * @return
     */
    public Request addNotificationURI(String notificationURI) {
        Objects.requireNonNull(notificationURI);
        requestPrimitive.getRt().getNu().add(notificationURI);
        return this;
    }

    /**
     * Indicates the duration for which the address containing the responses is to persist.
     * Example usage of response persistence includes requesting sufficient persistence for analytics
     * to process the response content aggregated asynchronously over time.
     * If a result expiration timestamp is specified then the response persistence should last beyond the result expiration time.
     *
     * @param resultPersistence
     * @return
     */
    public Request resultPersistence(OneM2M.Time resultPersistence) {
        Objects.requireNonNull(resultPersistence);
        requestPrimitive.setRp(resultPersistence.toString());
        return this;
    }

    /**
     * Indicates what are the expected components of the result of the requested operation.
     * The Originator of a request may not need to get back a result of an operation at all.
     *
     * @param resultContent (nothing,attributes,
     *                      hierarchical address,
     *                      hierarchical address and attributes,
     *                      attributes and child resources,
     *                      attributes and child resource references,
     *                      child resource references,
     *                      original resource)
     * @return
     */
    public Request resultContent(OneM2M.ResultContent resultContent) {
        Objects.requireNonNull(resultContent);
        requestPrimitive.setRcn(resultContent.value());
        return this;
    }

    //todo doesn't support user defined eventCategory

    /**
     * Indicates the event category that should be used to handle this request
     *
     * @param eventCategory (Immediate,
     *                      BestEffort,
     *                      Latest)
     * @return
     */
    public Request eventCategory(OneM2M.StdEventCats eventCategory) {
        Objects.requireNonNull(eventCategory);
        requestPrimitive.setEc(eventCategory.toString());
        return this;
    }

    /**
     * Delivery aggregation on/off:
     * Use CRUD operations of <delivery> resources to express forwarding of one or more original requests to the same target CSE(s).
     * When this parameter is not given in the request, the default behaviour is determined per the provisioned CMDH policy if available.
     * If there is no such CMDH policy, then the default value is “aggregation off”.
     *
     * @param deliveryAggregation
     * @return
     */
    public Request deliveryAggregation(boolean deliveryAggregation) {
        Objects.requireNonNull(deliveryAggregation);
        requestPrimitive.setDa(deliveryAggregation);
        return this;
    }

    /**
     * Identifier optionally added to the group request that is to be fanned out to each member of the group in order to detect loops and avoid duplicated handling of operation in case of loops of group and common members between groups that have parent-child relationship.
     *
     * @param groupRequestIdentifier
     * @return
     */
    public Request groupRequestIdentifier(String groupRequestIdentifier) {
        Objects.requireNonNull(groupRequestIdentifier);
        requestPrimitive.setGid(groupRequestIdentifier);
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * The creationTime attribute of the resource is chronologically before the specified value.
     *
     * @param createdBefore
     * @return
     */
    public Request fliterCreatedBefore(OneM2M.Time createdBefore) {
        Objects.requireNonNull(createdBefore);
        requestPrimitive.getFc().setCrb(createdBefore.toString());
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * The creationTime attribute of the resource is chronologically after the specified value.
     *
     * @param createdAfter
     * @return
     */
    public Request fliterCreatedAfter(OneM2M.Time createdAfter) {
        Objects.requireNonNull(createdAfter);
        requestPrimitive.getFc().setCra(createdAfter.toString());
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.￼
     * The lastModifiedTime attribute of the resource is chronologically after the specified value.
     *
     * @param modifiedSince
     * @return
     */
    public Request fliterModifiedSince(OneM2M.Time modifiedSince) {
        Objects.requireNonNull(modifiedSince);
        requestPrimitive.getFc().setMs(modifiedSince.toString());
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * ￼The lastModifiedTime attribute of the resource is chronologically before the specified value.
     * ￼
     *
     * @param unmodifiedSince
     * @return
     */

    public Request fliterUnmodifiedSince(OneM2M.Time unmodifiedSince) {
        Objects.requireNonNull(unmodifiedSince);
        requestPrimitive.getFc().setUs(unmodifiedSince.toString());
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * ￼
     * The stateTag attribute of the resource is smaller than the specified value.
     *
     * @param stateTagSmaller
     * @return
     */
    public Request fliterStateTagSmaller(int stateTagSmaller) {
        Objects.requireNonNull(stateTagSmaller);
        requestPrimitive.getFc().setSts(BigInteger.valueOf(stateTagSmaller));
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * ￼
     * The stateTag attribute of the resource is bigger than the specified value.
     *
     * @param stateTagBigger
     * @return
     */

    public Request fliterStateTagBigger(int stateTagBigger) {
        Objects.requireNonNull(stateTagBigger);
        requestPrimitive.getFc().setStb(BigInteger.valueOf(stateTagBigger));
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * ￼
     * The expirationTime attribute of the resource is chronologically before the specified value.
     * ￼
     *
     * @param expireBefore
     * @return
     */
    public Request fliterExpireBefore(OneM2M.Time expireBefore) {
        Objects.requireNonNull(expireBefore);
        requestPrimitive.getFc().setExb(expireBefore.toString());
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * ￼The expirationTime attribute of the resource is chronologically after the specified value.
     * ￼
     *
     * @param expireAfter
     * @return
     */

    public Request fliterExpireAfter(OneM2M.Time expireAfter) {
        Objects.requireNonNull(expireAfter);
        requestPrimitive.getFc().setExa(expireAfter.toString());
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * The labels attributes of the resource matches the specified value
     *
     * @param label
     * @return
     */

    public Request addfliterLabels(String label) {
        Objects.requireNonNull(label);
        requestPrimitive.getFc().getLbl().add(label);
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * ￼
     * The resourceType attribute of the resource is the same as the specified value.
     * It also allows differentiating between normal and announced resources.
     *
     * @param resourceType
     * @return
     */
    public Request fliterResourceType(OneM2M.ResourceType resourceType) {
        Objects.requireNonNull(resourceType);
        requestPrimitive.getFc().setTy(resourceType.value());
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.￼
     * The contentSize attribute of the <contentInstance> resource is equal to or greater than the specified value.
     *
     * @param sizeAbove
     * @return
     */
    public Request fliterSizeAbove(int sizeAbove) {
        Objects.requireNonNull(sizeAbove);
        requestPrimitive.getFc().setSza(BigInteger.valueOf(sizeAbove));
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * ￼The contentSize attribute of the <contentInstance> resource is smaller than the specified value.
     * ￼
     *
     * @param sizeBelow
     * @return
     */

    public Request fliterSizeBelow(int sizeBelow) {
        Objects.requireNonNull(sizeBelow);
        requestPrimitive.getFc().setSzb(BigInteger.valueOf(sizeBelow));
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.￼
     * The contentInfo attribute of the <contentInstance> resource matches the specified value.
     * ￼
     *
     * @param contentType
     * @return
     */
    public Request addFliterContentType(String contentType) {
        Objects.requireNonNull(contentType);
        requestPrimitive.getFc().getCty().add(contentType);
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * ￼This is an attribute of resource types. Therefore, a real tag name is variable and depends on its usage and the value of the attribute can have wild card *. E.g. creator of container resource type can be used as a filter criteria tag as "creator=Sam" , "creator=Sam*" , "creator=*Sam"
     *
     * @param name
     * @param value
     * @return
     */

    public Request addFliterAttribute(String name, Object value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);
        Attribute attribute = new Attribute();
        attribute.setNm(name);
        attribute.setVal(value);
        requestPrimitive.getFc().getAtr().add(attribute);
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * ￼Indicates how the filter criteria is used.
     *
     * @param filterUsage (Discovery Criteria,Conditional Retrieval)
     * @return
     */

    public Request filterUsage(OneM2M.FilterUsage filterUsage) {
        Objects.requireNonNull(filterUsage);
        requestPrimitive.getFc().setFu(filterUsage.value());
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     * ￼Limitation the number of matching resources to the specified value.
     *
     * @param limit
     * @return
     */

    public Request fliterLimit(int limit) {
        Objects.requireNonNull(limit);
        requestPrimitive.getFc().setLim(BigInteger.valueOf(limit));
        return this;
    }

    /**
     * This is used for resource discovery and general retrieve, update, delete requests.
     *
     * @param discoveryResultType (structured,unstructured)
     * @return
     */

    public Request discoveryResultType(OneM2M.DiscResType discoveryResultType) {
        Objects.requireNonNull(discoveryResultType);
        requestPrimitive.setDrt(discoveryResultType.value());
        return this;
    }


    public Rqp getRequestPrimitive() {
        return requestPrimitive;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getContentMIME() {
        return contentMIME;
    }

    public String getAcceptMIME() {
        return acceptMIME;
    }

    public long getTimeout() {
        return timeout;
    }

}
