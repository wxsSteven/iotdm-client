package org.opendaylight.iotdm.client.command;

/**
 * Created by wenxshi on 11/12/15.
 */
public interface Instruction {
    public void execute();

    public void parse(String instruction);
}
