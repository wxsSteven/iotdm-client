package org.opendaylight.iotdm.client.command.impl;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.api.Client;
import org.opendaylight.iotdm.client.command.ArgumentFactory;
import org.opendaylight.iotdm.client.command.ExecutableFactory;
import org.opendaylight.iotdm.client.command.Logger;
import org.opendaylight.iotdm.client.command.api.Argument;
import org.opendaylight.iotdm.client.command.api.Creator;
import org.opendaylight.iotdm.client.command.api.Executable;
import org.opendaylight.iotdm.client.command.api.Interpret;
import org.opendaylight.iotdm.client.command.exception.NoValueOfArgumentError;
import org.opendaylight.iotdm.client.impl.Http;
import org.opendaylight.iotdm.client.util.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenxshi on 11/23/15.
 */
public class HttpExecutable implements Executable,Interpret,Creator{
    public final static String NAME = "http";
    private static Client client = new Http();
    private static HttpExecutable httpExecutable;

    static {
        httpExecutable = new HttpExecutable();
        ExecutableFactory.register(NAME, httpExecutable);
    }

    private Request request;
    private List<Executable> arguments = new ArrayList<Executable>();

    public HttpExecutable() {
    }

    public void execute() {
        for (Executable arg : arguments)
            arg.execute();
        client.start();
        Logger.log(Json.newInstance().toJson(client.send(request)));
        client.stop();
    }

    public Executable interpret(String... args) {
        int i = 0;
        while (i < args.length) {
            String key = args[i++];
            Argument argument=ArgumentFactory.getInstance().getCreator(key);
            if (i < args.length) {
                String value = args[i];
                arguments.add(argument.create(request).interpret(value));
            } else {
                throw new NoValueOfArgumentError(key);
            }
        }
        return this;
    }

    public Interpret create() {
        request = new Request();
        return this;
    }
}
