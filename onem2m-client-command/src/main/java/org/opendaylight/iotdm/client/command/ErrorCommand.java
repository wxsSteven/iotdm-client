package org.opendaylight.iotdm.client.command;

/**
 * Created by wenxshi on 11/17/15.
 */
public class ErrorCommand extends Command {
    private String errorMessage;

    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute() {
        System.out.println(errorMessage);
    }

    @Override
    public void parseArgs() {
    }

    @Override
    public Command createCommand(Arguments args) {
        return this;
    }
}
