package org.opendaylight.iotdm.client.command.exception;

/**
 * Created by wenxshi on 11/17/15.
 */
public class NotKeyWordException extends AssertionError{
    public NotKeyWordException(String keyword){
        super(String.format("%s is not keyword",keyword));
    }
}
