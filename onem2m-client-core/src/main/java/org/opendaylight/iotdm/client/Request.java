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

    public Request port(int port) {
        this.port = port;
        return this;
    }

    public Request host(String host) {
        Objects.requireNonNull(host);
        this.host = host;
        return this;
    }

    public Request contentMIME(String contentMIME) {
        Objects.requireNonNull(contentMIME);
        this.contentMIME = contentMIME;
        return this;
    }

    public Request acceptMIME(String acceptMIME) {
        Objects.requireNonNull(acceptMIME);
        this.acceptMIME = acceptMIME;
        return this;
    }

    public Request timeout(long timeout, TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit);
        this.timeout = timeUnit.toMillis(timeout);
        return this;
    }


    public Request operation(OneM2M.Operation operation) {
        Objects.requireNonNull(operation);
        requestPrimitive.setOp(operation.value());
        return this;
    }

    public Request to(String to) {
        Objects.requireNonNull(to);
        requestPrimitive.setTo(to);
        return this;
    }

    public Request from(String from) {
        Objects.requireNonNull(from);
        requestPrimitive.setFr(from);
        return this;
    }

    public Request requestIdentifier(String requestIdentifier) {
        Objects.requireNonNull(requestIdentifier);
        requestPrimitive.setRqi(requestIdentifier);
        return this;
    }

    public Request resourceType(OneM2M.ResourceType resourceType) {
        Objects.requireNonNull(resourceType);
        requestPrimitive.setTy(resourceType.value());
        return this;
    }

    public Request name(String name) {
        Objects.requireNonNull(name);
        requestPrimitive.setNm(name);
        return this;
    }

    public Request addPrimitiveContent(Object object) {
        Objects.requireNonNull(object);
        requestPrimitive.getPc().getAny().add(object);
        return this;
    }

    public Request role(Object role) {
        Objects.requireNonNull(role);
        requestPrimitive.setRol(role);
        return this;
    }

    public Request originatingTimestamp(OneM2M.Time originatingTimestamp) {
        Objects.requireNonNull(originatingTimestamp);
        requestPrimitive.setOt(originatingTimestamp.toString());
        return this;
    }

    public Request requestExpirationTimestamp(OneM2M.Time requestExpirationTimestamp) {
        Objects.requireNonNull(requestExpirationTimestamp);
        requestPrimitive.setRqet(requestExpirationTimestamp.toString());
        return this;
    }

    public Request resultExpirationTimestamp(OneM2M.Time resultExpirationTimestamp) {
        Objects.requireNonNull(resultExpirationTimestamp);
        requestPrimitive.setRset(resultExpirationTimestamp.toString());
        return this;
    }

    public Request operationExecutionTime(OneM2M.Time operationExecutionTime) {
        Objects.requireNonNull(operationExecutionTime);
        requestPrimitive.setOet(operationExecutionTime.toString());
        return this;
    }

    public Request responseTypeValue(OneM2M.ResponseType responseTypeValue) {
        Objects.requireNonNull(responseTypeValue);
        requestPrimitive.getRt().setRtv(responseTypeValue.value());
        return this;
    }

    public Request addNotificationURI(String notificationURI) {
        Objects.requireNonNull(notificationURI);
        requestPrimitive.getRt().getNu().add(notificationURI);
        return this;
    }

    public Request resultPersistence(OneM2M.Time resultPersistence) {
        Objects.requireNonNull(resultPersistence);
        requestPrimitive.setRp(resultPersistence.toString());
        return this;
    }

    public Request resultContent(OneM2M.ResultContent resultContent) {
        Objects.requireNonNull(resultContent);
        requestPrimitive.setRcn(resultContent.value());
        return this;
    }

    //todo doesn't support user defined eventCategory
    public Request eventCategory(OneM2M.StdEventCats eventCategory) {
        Objects.requireNonNull(eventCategory);
        requestPrimitive.setEc(eventCategory.toString());
        return this;
    }

    public Request deliveryAggregation(boolean deliveryAggregation) {
        Objects.requireNonNull(deliveryAggregation);
        requestPrimitive.setDa(deliveryAggregation);
        return this;
    }

    public Request groupRequestIdentifier(String groupRequestIdentifier) {
        Objects.requireNonNull(groupRequestIdentifier);
        requestPrimitive.setGid(groupRequestIdentifier);
        return this;
    }

    public Request fliterCreatedBefore(OneM2M.Time createdBefore) {
        Objects.requireNonNull(createdBefore);
        requestPrimitive.getFc().setCrb(createdBefore.toString());
        return this;
    }

    public Request fliterCreatedAfter(OneM2M.Time createdAfter) {
        Objects.requireNonNull(createdAfter);
        requestPrimitive.getFc().setCra(createdAfter.toString());
        return this;
    }

    public Request fliterModifiedSince(OneM2M.Time modifiedSince) {
        Objects.requireNonNull(modifiedSince);
        requestPrimitive.getFc().setMs(modifiedSince.toString());
        return this;
    }

    public Request fliterUnmodifiedSince(OneM2M.Time unmodifiedSince) {
        Objects.requireNonNull(unmodifiedSince);
        requestPrimitive.getFc().setUs(unmodifiedSince.toString());
        return this;
    }

    public Request fliterStateTagSmaller(int stateTagSmaller) {
        Objects.requireNonNull(stateTagSmaller);
        requestPrimitive.getFc().setSts(BigInteger.valueOf(stateTagSmaller));
        return this;
    }

    public Request fliterStateTagBigger(int stateTagBigger) {
        Objects.requireNonNull(stateTagBigger);
        requestPrimitive.getFc().setStb(BigInteger.valueOf(stateTagBigger));
        return this;
    }

    public Request fliterExpireBefore(OneM2M.Time expireBefore) {
        Objects.requireNonNull(expireBefore);
        requestPrimitive.getFc().setExb(expireBefore.toString());
        return this;
    }

    public Request fliterExpireAfter(OneM2M.Time expireAfter) {
        Objects.requireNonNull(expireAfter);
        requestPrimitive.getFc().setExa(expireAfter.toString());
        return this;
    }

    public Request addfliterLabels(String label) {
        Objects.requireNonNull(label);
        requestPrimitive.getFc().getLbl().add(label);
        return this;
    }

    public Request fliterResourceType(OneM2M.ResourceType resourceType) {
        Objects.requireNonNull(resourceType);
        requestPrimitive.getFc().setTy(resourceType.value());
        return this;
    }

    public Request fliterSizeAbove(int sizeAbove) {
        Objects.requireNonNull(sizeAbove);
        requestPrimitive.getFc().setSza(BigInteger.valueOf(sizeAbove));
        return this;
    }

    public Request fliterSizeBelow(int sizeBelow) {
        Objects.requireNonNull(sizeBelow);
        requestPrimitive.getFc().setSzb(BigInteger.valueOf(sizeBelow));
        return this;
    }

    public Request addFliterContentType(String contentType) {
        Objects.requireNonNull(contentType);
        requestPrimitive.getFc().getCty().add(contentType);
        return this;
    }

    public Request addFliterAttribute(String name, Object value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);
        Attribute attribute = new Attribute();
        attribute.setNm(name);
        attribute.setVal(value);
        requestPrimitive.getFc().getAtr().add(attribute);
        return this;
    }

    public Request filterUsage(OneM2M.FilterUsage filterUsage) {
        Objects.requireNonNull(filterUsage);
        requestPrimitive.getFc().setFu(filterUsage.value());
        return this;
    }

    public Request fliterLimit(int limit) {
        Objects.requireNonNull(limit);
        requestPrimitive.getFc().setLim(BigInteger.valueOf(limit));
        return this;
    }

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
