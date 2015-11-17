package org.opendaylight.iotdm.client.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * Created by wenxshi on 11/17/15.
 */
public class CommandFactory {

    private static HashMap<String, Command> longOption = new HashMap<String, Command>();
    private static HashMap<String, Command> shortOption = new HashMap<String, Command>();

    private static HashMap<String, Command> keywords = new HashMap<String, Command>();

    public static void registerLongOption(String commandId, Command command) {
        Objects.requireNonNull(commandId);
        Objects.requireNonNull(command);
        longOption.put(commandId, command);
    }

    public static void registerShortOption(String commandId, Command command) {
        Objects.requireNonNull(commandId);
        Objects.requireNonNull(command);
        shortOption.put(commandId, command);
    }

    public static void registerKeyword(String keyword, Command command) {
        Objects.requireNonNull(keyword);
        Objects.requireNonNull(command);
        keywords.put(keyword, command);
    }

    public static Command createCommand(Arguments arguments) {
        Arguments.Argument argument = arguments.peek();
        String keyword = argument.getKeyword();
        if (keywords.containsKey(argument.getKeyword())) {
            return keywords.get(keyword).createCommand(arguments);
        }
        return new ErrorCommand(String.format("No such command:%s", keyword));
    }

    public static boolean isKeyword(String keyword) {
        Objects.requireNonNull(keyword);
        return keywords.containsKey(keyword);
    }

    public static boolean isOption(String option) {
        Objects.requireNonNull(option);
        return longOption.containsKey(option) || shortOption.containsKey(option);
    }

}
