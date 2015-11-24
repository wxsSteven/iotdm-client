package org.opendaylight.iotdm.client.command.impl;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.command.ArgumentFactory;
import org.opendaylight.iotdm.client.command.api.Argument;
import org.opendaylight.iotdm.client.command.api.Executable;
import org.opendaylight.iotdm.client.command.api.Interpret;

/**
 * Created by wenxshi on 11/23/15.
 */
public class PortArgument implements Argument {

    public final static String LONG_NAME = "--port";
    public final static String SHORT_NAME = "-p";

    static {
        ArgumentFactory.register(LONG_NAME, new PortArgument());
        ArgumentFactory.register(SHORT_NAME, new PortArgument());
    }

    private int port;
    private Request request;

    private PortArgument(){}

    private PortArgument(Request request) {
        this.request = request;
    }

    public Interpret create(Request request) {
        return new PortArgument(request);
    }

    public void execute() {
        request.port(port);
    }

    public Executable interpret(String... args) {
        port = Integer.valueOf(args[0]);
        return this;
    }
}
