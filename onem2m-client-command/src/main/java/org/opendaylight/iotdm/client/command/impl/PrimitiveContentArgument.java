package org.opendaylight.iotdm.client.command.impl;

import org.onem2m.xml.protocols.PrimitiveContent;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.command.ArgumentFactory;
import org.opendaylight.iotdm.client.command.api.Argument;
import org.opendaylight.iotdm.client.command.api.Executable;
import org.opendaylight.iotdm.client.command.api.Interpret;
import org.opendaylight.iotdm.client.util.Json;

/**
 * Created by wenxshi on 11/23/15.
 */
public class PrimitiveContentArgument implements Argument {
    public static final String LONG_NAME = "--primitiveContent";
    public static final String SHORT_NAME = "-pc";

    static {
        ArgumentFactory.register(LONG_NAME, new PrimitiveContentArgument());
        ArgumentFactory.register(SHORT_NAME, new PrimitiveContentArgument());
    }

    private PrimitiveContent primitiveContent;
    private Request request;

    private PrimitiveContentArgument(Request request) {
        this.request = request;
    }

    private PrimitiveContentArgument(){}

    public void execute() {
        request.primitiveContent(primitiveContent);
    }

    public Executable interpret(String... args) {
        primitiveContent = Json.newInstance().fromJson(args[0], PrimitiveContent.class);
        return this;
    }

    public Interpret create(Request request) {
        return new PrimitiveContentArgument(request);
    }
}
