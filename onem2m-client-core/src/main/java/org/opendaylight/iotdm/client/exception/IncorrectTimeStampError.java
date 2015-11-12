package org.opendaylight.iotdm.client.exception;

/**
 * Created by wenxshi on 11/5/15.
 */
public class IncorrectTimeStampError extends AssertionError {
    public IncorrectTimeStampError(String timeStamp){
        super(String.format("\"%s\" is not OneM2M timestamp format",timeStamp));
    }
}
