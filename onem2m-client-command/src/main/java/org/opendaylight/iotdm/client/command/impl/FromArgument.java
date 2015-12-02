package org.opendaylight.iotdm.client.command.impl;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.command.ArgumentFactory;
import org.opendaylight.iotdm.client.command.api.Argument;
import org.opendaylight.iotdm.client.command.api.Executable;
import org.opendaylight.iotdm.client.command.api.Interpret;

/**
 * Created by wenxshi on 11/23/15.
 */
public class FromArgument implements Argument {
    public final static String LONG_NAME = "--from";
    public final static String SHORT_NAME = "-fr";

    static {
        ArgumentFactory.register(LONG_NAME, new FromArgument());
        ArgumentFactory.register(SHORT_NAME, new FromArgument());
    }

    private String from;
    private Request request;

    private FromArgument() {
    }

    private FromArgument(Request request) {
        this.request = request;
    }

    public void execute() {
        request.from(from);
    }

    public Executable interpret(String... args) {
        from = args[0];
        return this;
    }

    public Interpret create(Request request) {
        return new FromArgument(request);
    }
}
