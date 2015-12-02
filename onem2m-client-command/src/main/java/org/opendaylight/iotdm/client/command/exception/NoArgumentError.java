package org.opendaylight.iotdm.client.command.exception;

/**
 * Created by wenxshi on 11/23/15.
 */
public class NoArgumentError extends AssertionError {
    public NoArgumentError(){
        super("No argument error");
    }
}
