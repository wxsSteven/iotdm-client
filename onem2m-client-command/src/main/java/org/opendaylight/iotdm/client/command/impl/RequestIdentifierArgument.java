package org.opendaylight.iotdm.client.command.impl;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.command.ArgumentFactory;
import org.opendaylight.iotdm.client.command.api.Argument;
import org.opendaylight.iotdm.client.command.api.Executable;
import org.opendaylight.iotdm.client.command.api.Interpret;

/**
 * Created by wenxshi on 11/23/15.
 */
public class RequestIdentifierArgument implements Argument {

    public final static String LONG_NAME="--requestIdentifier";
    public final static String SHORT_NAME="-rqi";

    static{
        ArgumentFactory.register(LONG_NAME,new RequestIdentifierArgument());
        ArgumentFactory.register(SHORT_NAME,new RequestIdentifierArgument());
    }

    private String requestIdentifier;
    private Request request;

    private RequestIdentifierArgument(){}

    private RequestIdentifierArgument(Request request) {
        this.request = request;
    }

    public void execute() {
        request.requestIdentifier(requestIdentifier);
    }

    public Executable interpret(String... args) {
        requestIdentifier = args[0];
        return this;
    }

    public Interpret create(Request request) {
        return new RequestIdentifierArgument(request);
    }
}