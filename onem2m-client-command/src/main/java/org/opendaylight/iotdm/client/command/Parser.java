package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.command.api.Executable;
import org.opendaylight.iotdm.client.command.api.Interpret;
import org.opendaylight.iotdm.client.command.exception.NoArgumentError;

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
        return parse(commands);
    }

    public static Executable parse(String[] commands) {
        Objects.requireNonNull(commands);
        Interpret interpret=ExecutableFactory.getInstance().getCreate(commands[0]).create();
        String[] args=Arrays.copyOfRange(commands, 1, commands.length);
        if(args.length==0)
            throw new NoArgumentError();
        Executable executable=interpret.interpret(args);
        return executable;
    }
}
