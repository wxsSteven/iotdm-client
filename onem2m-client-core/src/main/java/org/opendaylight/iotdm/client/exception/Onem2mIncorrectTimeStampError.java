package org.opendaylight.iotdm.client.exception;

/**
 * Created by wenxshi on 11/5/15.
 */
public class Onem2mIncorrectTimeStampError extends AssertionError {
    public final static String MESSAGE_FORMAT ="\"%s\" is not OneM2M timestamp format";

    public Onem2mIncorrectTimeStampError(String timeStamp){
        super(String.format(MESSAGE_FORMAT,timeStamp));
    }
}
