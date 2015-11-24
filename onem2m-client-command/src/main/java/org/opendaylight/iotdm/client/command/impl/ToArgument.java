package org.opendaylight.iotdm.client.command.impl;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.command.ArgumentFactory;
import org.opendaylight.iotdm.client.command.api.Argument;
import org.opendaylight.iotdm.client.command.api.Executable;
import org.opendaylight.iotdm.client.command.api.Interpret;

/**
 * Created by wenxshi on 11/23/15.
 */
public class ToArgument implements Argument {
    public final static String LONG_NAME = "--to";
    public final static String SHORT_NAME = "-t";

    static {
        ArgumentFactory.register(LONG_NAME, new ToArgument());
        ArgumentFactory.register(SHORT_NAME, new ToArgument());
    }

    private String to;
    private Request request;

    private ToArgument(){}

    private ToArgument(Request request) {
        this.request = request;
    }

    public void execute() {
        request.to(to);
    }

    public Executable interpret(String... args) {
        to = args[0];
        return this;
    }

    public Interpret create(Request request) {
        return new ToArgument(request);
    }
}
