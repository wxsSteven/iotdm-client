package org.opendaylight.iotdm.client.command.exception;

/**
 * Created by wenxshi on 11/23/15.
 */
public class NotArgumentError extends AssertionError{
    public NotArgumentError(String argument){
        super(String.format("%s is not argument",argument));
    }
}
