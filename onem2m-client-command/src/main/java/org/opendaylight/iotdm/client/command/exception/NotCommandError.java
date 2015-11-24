package org.opendaylight.iotdm.client.command.exception;

/**
 * Created by wenxshi on 11/17/15.
 */
public class NotCommandError extends AssertionError{
    public NotCommandError(String command){
        super(String.format("%s is not command",command));
    }
}
