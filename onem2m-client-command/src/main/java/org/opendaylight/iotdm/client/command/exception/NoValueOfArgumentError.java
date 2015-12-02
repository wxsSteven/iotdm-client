package org.opendaylight.iotdm.client.command.exception;

/**
 * Created by wenxshi on 11/23/15.
 */
public class NoValueOfArgumentError extends AssertionError {
    public NoValueOfArgumentError(String name){
        super(String.format("%s doesn't have value",name));
    }
}
