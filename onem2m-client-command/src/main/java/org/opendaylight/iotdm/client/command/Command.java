package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;
import org.opendaylight.iotdm.client.api.Client;

/**
 * Created by wenxshi on 11/12/15.
 */
public abstract class Command {
    protected static Request request;
    protected static Response response;
    protected static Client client;

    protected int maxArgsNumber;
    protected Arguments arguments;

    abstract public void execute();
    abstract public void parseArgs();
    abstract public Command createCommand(Arguments arguments);
}
