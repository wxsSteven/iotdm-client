package org.opendaylight.iotdm.client.command.api;

import java.util.List;

/**
 * Created by wenxshi on 11/17/15.
 */
public interface Executable {
    public void execute(List<Argument> arguments);
    public Executable create();
}
