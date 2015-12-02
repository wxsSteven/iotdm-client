package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.command.api.Argument;
import org.opendaylight.iotdm.client.command.exception.NotArgumentError;
import org.opendaylight.iotdm.client.command.impl.*;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by wenxshi on 11/17/15.
 */
public class ArgumentFactory {

    private static HashMap<String, Argument> map = new HashMap<String, Argument>();
    private static ArgumentFactory instance;

    static {
        try {
            Class.forName(FromArgument.class.getName());
            Class.forName(HostArgument.class.getName());
            Class.forName(NameArgument.class.getName());
            Class.forName(OperationArgument.class.getName());
            Class.forName(PortArgument.class.getName());
            Class.forName(PrimitiveContentArgument.class.getName());
            Class.forName(RequestIdentifierArgument.class.getName());
            Class.forName(ResourceTypeArgument.class.getName());
            Class.forName(ToArgument.class.getName());
        } catch (Exception e) {

        }
    }

    public static ArgumentFactory getInstance() {
        if (instance == null)
            instance = new ArgumentFactory();
        return instance;
    }

    public static void register(String name, Argument argument) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(argument);
        String lowerCase = name.toLowerCase();
        map.put(lowerCase, argument);
    }

    public Argument getCreator(String id) {
        Objects.requireNonNull(id);
        String lowerCase = id.toLowerCase();
        if (map.containsKey(lowerCase))
            return map.get(lowerCase);
        throw new NotArgumentError(id);
    }
}
