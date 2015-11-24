package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.command.api.Executable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by wenxshi on 11/17/15.
 */
public class Parser {
    public static Executable parse(String command) {
        Objects.requireNonNull(command);
        command = command.trim().replaceAll(" +", " ");
        String[] commands = command.split(" ");
        if(commands.length==0)
            ;

        return ExecutableFactory.getInstance().getCreate(commands[0]).create()
                .interpret(Arrays.copyOfRange(commands, 1, commands.length));
    }
}
