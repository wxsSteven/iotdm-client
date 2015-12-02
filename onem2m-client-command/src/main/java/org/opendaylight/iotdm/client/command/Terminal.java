package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.command.api.Executable;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by wenxshi on 11/12/15.
 */
public class Terminal {

    private enum Status {
        MAN, EXIT
    }

    private final static String MANUAL = "hello world";

    public static void main(String[] args) {
        if (args.length == 0) {
            runInternalTerminal();
        } else {
            Executable executable = Parser.parse(args);
            executable.execute();
        }
    }

    private static void runInternalTerminal(){
        boolean flag = true;
        while (flag) {
            try {
                System.out.print(">>");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String commandStr = br.readLine().toLowerCase().trim();

                if (commandStr.equalsIgnoreCase(Status.MAN.name())) {
                    Logger.log(MANUAL);
                    continue;
                } else if (commandStr.equalsIgnoreCase(Status.EXIT.name())) {
                    return;
                } else if (commandStr.isEmpty())
                    continue;
                Executable executable = Parser.parse(commandStr);
                executable.execute();
            } catch (Throwable e) {
                Logger.log(e.getMessage());
            }
        }
    }
}
