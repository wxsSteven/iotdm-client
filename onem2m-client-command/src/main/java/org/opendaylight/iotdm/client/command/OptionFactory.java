package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.command.api.Option;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by wenxshi on 11/17/15.
 */
public class OptionFactory {
    private static HashMap<String, Option> map = new HashMap<String, Option>();

    public static void register(String name, Option option) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(option);
        map.put(name, option);
    }

    public static Option create(String id) {
        return map.get(id).create();
    }
}
