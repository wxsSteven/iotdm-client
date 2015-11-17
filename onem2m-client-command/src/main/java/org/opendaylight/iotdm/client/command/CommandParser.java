package org.opendaylight.iotdm.client.command;

/**
 * Created by wenxshi on 11/17/15.
 */
public class CommandParser {

    public static Command parse(String command) {
        command = command.trim().replaceAll(" +", " ");
        String[] commands = command.split(" ");
        return createCommand(commands);
    }

    private static Command createCommand(String[] args) {
        if (args == null || args.length == 0)
            return null;

        Arguments arguments=new Arguments(args);
        Command command = CommandFactory.createCommand(arguments);
        return command;
    }
}
