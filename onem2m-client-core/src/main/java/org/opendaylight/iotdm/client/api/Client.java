package org.opendaylight.iotdm.client.api;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;

/**
 * Created by wenxshi on 6/2/15.
 */
public interface Client {
    public void start();
    public void stop();
    public Response send(Request request);
}
