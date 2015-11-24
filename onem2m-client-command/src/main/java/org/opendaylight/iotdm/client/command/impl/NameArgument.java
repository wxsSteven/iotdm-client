package org.opendaylight.iotdm.client.command.impl;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.command.ArgumentFactory;
import org.opendaylight.iotdm.client.command.api.Argument;
import org.opendaylight.iotdm.client.command.api.Executable;
import org.opendaylight.iotdm.client.command.api.Interpret;

/**
 * Created by wenxshi on 11/23/15.
 */
public class NameArgument implements Argument {

    public final static String LONG_NAME = "--name";
    public final static String SHORT_NAME = "-nm";

    static {
        ArgumentFactory.register(LONG_NAME, new NameArgument());
        ArgumentFactory.register(SHORT_NAME, new NameArgument());
    }

    private String name;
    private Request request;

    private NameArgument() {
    }

    private NameArgument(Request request) {
        this.request = request;
    }

    public void execute() {
        request.name(name);
    }

    public Executable interpret(String... args) {
        name = args[0];
        return this;
    }

    public Interpret create(Request request) {
        return new NameArgument(request);
    }
}
