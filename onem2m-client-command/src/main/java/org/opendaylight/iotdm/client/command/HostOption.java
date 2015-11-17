package org.opendaylight.iotdm.client.command;

/**
 * Created by wenxshi on 11/17/15.
 */
public class HostOption extends Command {
    public static final String LONG_NAME = "--host";
    public static final String SHORT_NAME = "-h";

    @Override
    public void execute() {

    }

    @Override
    public void parseArgs() {
        Arguments.Argument argument=arguments.poll();

    }

    @Override
    public Command createCommand(Arguments arguments) {
        HostOption option=new HostOption();
        option.arguments=arguments;
        return option;
    }
}
