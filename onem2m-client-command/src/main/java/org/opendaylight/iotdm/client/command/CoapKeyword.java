package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.impl.Coap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by wenxshi on 11/17/15.
 */
public class CoapKeyword extends Command {
    public static final String KEYWORD = "coap";

    private List<Command> options = new ArrayList<Command>();

    static {
        CommandFactory.registerKeyword(KEYWORD, new CoapKeyword());
    }

    private CoapKeyword() {
        request = new Request();
        client = new Coap();
    }

    @Override
    public void execute() {
        for (Command command : options) {
            command.execute();
        }
        client.start();
        response = client.send(request);
        client.stop();
    }

    @Override
    public void parseArgs() {
        Objects.requireNonNull(arguments);
        arguments.poll();
        while(!arguments.isEmpty()){
            options.add(CommandFactory.createCommand(arguments));
        }

        for(Command command:options){
            command.parseArgs();
        }
    }

    @Override
    public Command createCommand(Arguments arguments) {
        CoapKeyword coapKeyword = new CoapKeyword();
        coapKeyword.arguments = arguments;
        coapKeyword.maxArgsNumber = Integer.MAX_VALUE;
        return coapKeyword;
    }
}
