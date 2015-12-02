package org.opendaylight.iotdm.client.command.impl;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.command.ArgumentFactory;
import org.opendaylight.iotdm.client.command.api.Argument;
import org.opendaylight.iotdm.client.command.api.Executable;
import org.opendaylight.iotdm.client.command.api.Interpret;
import org.opendaylight.iotdm.constant.OneM2M;

/**
 * Created by wenxshi on 11/23/15.
 */
public class OperationArgument implements Argument {
    public static final String LONG_NAME = "--operation";
    public static final String SHORT_NAME = "-op";

    static {
        ArgumentFactory.register(LONG_NAME, new OperationArgument());
        ArgumentFactory.register(SHORT_NAME, new OperationArgument());
    }

    private OneM2M.Operation operation;
    private Request request;

    private OperationArgument(Request request) {
        this.request = request;
    }

    private OperationArgument(){}

    public void execute() {
        request.operation(operation);

    }

    public Executable interpret(String... args) {
        operation = OneM2M.Operation.valueOf(args[0].toUpperCase());
        return this;
    }

    public Interpret create(Request request) {
        return new OperationArgument(request);
    }
}
