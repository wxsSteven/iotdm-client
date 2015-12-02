package org.opendaylight.iotdm.client.command.impl;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.command.ArgumentFactory;
import org.opendaylight.iotdm.client.command.api.Argument;
import org.opendaylight.iotdm.client.command.api.Executable;
import org.opendaylight.iotdm.client.command.api.Interpret;

/**
 * Created by wenxshi on 11/23/15.
 */
public class HostArgument implements Argument {

    public final static String LONG_NAME="--host";
    public final static String SHORT_NAME="-h";

    static{
        ArgumentFactory.register(LONG_NAME,new HostArgument());
        ArgumentFactory.register(SHORT_NAME,new HostArgument());
    }

    private String host;
    private Request request;

    private HostArgument(){}

    private HostArgument(Request request) {
        this.request = request;
    }

    public void execute() {
        request.host(host);
    }

    public Executable interpret(String... args) {
        host = args[0];
        return this;
    }

    public Interpret create(Request request) {
        return new HostArgument(request);
    }
}
