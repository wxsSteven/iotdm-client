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
public class ResourceTypeArgument implements Argument {
    public static final String LONG_NAME = "--resourceType";
    public static final String SHORT_NAME = "-ty";

    static {
        ArgumentFactory.register(LONG_NAME, new ResourceTypeArgument());
        ArgumentFactory.register(SHORT_NAME, new ResourceTypeArgument());
    }

    private OneM2M.ResourceType ResourceType;
    private Request request;

    private ResourceTypeArgument(Request request) {
        this.request = request;
    }

    private ResourceTypeArgument() {
    }

    public void execute() {
        request.resourceType(ResourceType);

    }

    public Executable interpret(String... args) {
        ResourceType = OneM2M.ResourceType.valueOf(args[0].toUpperCase());
        return this;
    }

    public Interpret create(Request request) {
        return new ResourceTypeArgument(request);
    }
}
