package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.command.api.Creator;
import org.opendaylight.iotdm.client.command.exception.NotCommandError;
import org.opendaylight.iotdm.client.command.impl.CoapExecutable;
import org.opendaylight.iotdm.client.command.impl.HttpExecutable;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by wenxshi on 11/17/15.
 */
public class ExecutableFactory {
    private static HashMap<String, Creator> map = new HashMap<String, Creator>();
    private static ExecutableFactory instance;

    public static void register(String name, Creator creator) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(creator);
        String lowerCase=name.toLowerCase();
        map.put(lowerCase, creator);
    }

    static {
        try {
            Class.forName(CoapExecutable.class.getName());
            Class.forName(HttpExecutable.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ExecutableFactory() {
    }

    public static ExecutableFactory getInstance() {
        if (instance == null)
            instance = new ExecutableFactory();
        return instance;
    }

    public Creator getCreate(String id) {
        Objects.requireNonNull(id);
        String lowerCase=id.toLowerCase();
        if (map.containsKey(lowerCase))
            return map.get(lowerCase);
        throw new NotCommandError(id);
    }
}
