package org.opendaylight.iotdm.client.exception;

/**
 * Created by wenxshi on 11/18/15.
 */
public class Onem2mComplianceError extends AssertionError {
    public Onem2mComplianceError(String type, String attribute) {
        super(String.format("The Attribute %s of type %s is not onem2m compliant", attribute, type));
    }
}
