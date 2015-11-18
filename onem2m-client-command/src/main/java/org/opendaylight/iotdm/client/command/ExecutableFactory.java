package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.command.api.Executable;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by wenxshi on 11/17/15.
 */
public class ExecutableFactory {
    private static HashMap<String,Executable> map=new HashMap<String, Executable>();

    public static void register(String name,Executable executable){
        Objects.requireNonNull(name);
        Objects.requireNonNull(executable);
        map.put(name,executable);
    }

    public static Executable create(String id){
        return map.get(id).create();
    }
}
