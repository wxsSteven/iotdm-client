package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.command.api.Argument;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by wenxshi on 11/17/15.
 */
public class ArgumentFactory {
    private static HashMap<String, Argument> map = new HashMap<String, Argument>();

    public static void register(String name, Argument argument) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(argument);
        map.put(name, argument);
    }

    public static Argument create(String id) {
        return map.get(id).create();
    }
}
