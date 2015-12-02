package org.opendaylight.iotdm.client.command.api;

import org.opendaylight.iotdm.client.Request;

/**
 * Created by wenxshi on 11/23/15.
 */
public interface Argument extends Executable, Interpret {
    public Interpret create(Request request);
}
