package org.opendaylight.iotdm.client.command;

import org.onem2m.xml.protocols.PrimitiveContent;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;
import org.opendaylight.iotdm.client.api.Client;
import org.opendaylight.iotdm.client.impl.Coap;
import org.opendaylight.iotdm.client.util.Json;
import org.opendaylight.iotdm.constant.OneM2M;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxshi on 11/12/15.
 */
public class Command implements Instruction {

    private Client client;
    private Request request;
    private Response response;

    private Map<String, Instruction> shortNameMap = new HashMap<String, Instruction>();
    private Map<String, Instruction> longNameMap = new HashMap<String, Instruction>();

    private List<Instruction> instructionChain = new ArrayList<Instruction>();

    public Command() {
        shortNameMap.put(HostInstruction.SHORT_NAME, new HostInstruction());
        shortNameMap.put(PortInstruction.SHORT_NAME, new PortInstruction());
        shortNameMap.put(OperationInstruction.SHORT_NAME, new OperationInstruction());
        shortNameMap.put(ToInstruction.SHORT_NAME, new ToInstruction());
        shortNameMap.put(FromInstruction.SHORT_NAME, new FromInstruction());
        shortNameMap.put(NameInstruction.SHORT_NAME, new NameInstruction());
        shortNameMap.put(ResourceTypeInstruction.SHORT_NAME, new ResourceTypeInstruction());
        shortNameMap.put(RequestIdentifierInstruction.SHORT_NAME, new RequestIdentifierInstruction());
        shortNameMap.put(PrimitiveContentInstruction.SHORT_NAME, new PrimitiveContentInstruction());
        shortNameMap.put(CoapInstruction.SHORT_NAME, new CoapInstruction());

        longNameMap.put(HostInstruction.LONG_NAME, new HostInstruction());
        longNameMap.put(PortInstruction.LONG_NAME, new PortInstruction());
        longNameMap.put(OperationInstruction.LONG_NAME, new OperationInstruction());
        longNameMap.put(ToInstruction.LONG_NAME, new ToInstruction());
        longNameMap.put(FromInstruction.LONG_NAME, new FromInstruction());
        longNameMap.put(NameInstruction.LONG_NAME, new NameInstruction());
        longNameMap.put(ResourceTypeInstruction.LONG_NAME, new ResourceTypeInstruction());
        longNameMap.put(RequestIdentifierInstruction.LONG_NAME, new RequestIdentifierInstruction());
        longNameMap.put(PrimitiveContentInstruction.LONG_NAME, new PrimitiveContentInstruction());
        longNameMap.put(CoapInstruction.LONG_NAME, new CoapInstruction());
    }

    public void parse(String command) {
        command = command.trim().replaceAll(" +", " ");
        String[] commands = command.split(" ");

        for (int i = 0; i < commands.length; i++) {
            String str = commands[i].toLowerCase();
            if (str.startsWith("--")) {
                String key = str.substring(2);
                String value = commands[i + 1];
                Instruction instruction = longNameMap.get(key);
                instruction.parse(value);
                instructionChain.add(instruction);
                i++;
            } else if (str.startsWith("-")) {
                String key = str.substring(1);
                String value = commands[i + 1];
                Instruction instruction = shortNameMap.get(key);
                instruction.parse(value);
                instructionChain.add(instruction);
            } else if (shortNameMap.containsKey(str)) {
                instructionChain.add(shortNameMap.get(str));
            } else {
                instructionChain.add(new ErrorInstruction("error message"));
                break;
            }
        }
    }


    public void execute() {
        request = new Request();
        for (Instruction instruction : instructionChain) {
            instruction.execute();
        }

        client.start();
        response = client.send(request);
        client.stop();
        System.out.println(Json.newInstance().toJson(response));
    }

    public class CoapInstruction implements Instruction {
        public static final String LONG_NAME = "coap";
        public static final String SHORT_NAME = "coap";

        public void execute() {
            client = new Coap();
        }

        public void parse(String instruction) {

        }
    }

    private class HostInstruction implements Instruction {
        public static final String LONG_NAME = "host";
        public static final String SHORT_NAME = "h";

        private String host;

        public void execute() {
            request.host(host);
        }

        public void parse(String instruction) {
            host = instruction;
        }
    }

    private class PortInstruction implements Instruction {
        public static final String LONG_NAME = "port";
        public static final String SHORT_NAME = "p";

        private int port;

        public void execute() {
            request.port(port);
        }

        public void parse(String instruction) {
            port = Integer.valueOf(instruction);
        }
    }

    private class OperationInstruction implements Instruction {
        public static final String LONG_NAME = "operation";
        public static final String SHORT_NAME = "op";

        private OneM2M.Operation operation;

        public void execute() {
            request.operation(operation);

        }

        public void parse(String instruction) {
            operation = OneM2M.Operation.valueOf(instruction.toUpperCase());
        }
    }

    private class ToInstruction implements Instruction {
        public static final String LONG_NAME = "to";
        public static final String SHORT_NAME = "to";

        private String to;

        public void execute() {
            request.to(to);

        }

        public void parse(String instruction) {
            to = instruction;
        }
    }

    private class FromInstruction implements Instruction {
        public static final String LONG_NAME = "from";
        public static final String SHORT_NAME = "fr";


        private String from;

        public void execute() {
            request.from(from);
        }

        public void parse(String instruction) {
            from = instruction;
        }
    }

    private class RequestIdentifierInstruction implements Instruction {
        public static final String LONG_NAME = "requestidentifier";
        public static final String SHORT_NAME = "rqi";

        private String requestIdentifier;

        public void execute() {
            request.requestIdentifier(requestIdentifier);

        }

        public void parse(String instruction) {
            requestIdentifier = instruction;
        }
    }

    private class ResourceTypeInstruction implements Instruction {
        public static final String LONG_NAME = "resourcetype";
        public static final String SHORT_NAME = "ty";

        private OneM2M.ResourceType resourceType;

        public void execute() {
            request.resourceType(resourceType);

        }

        public void parse(String instruction) {
            resourceType = OneM2M.ResourceType.valueOf(instruction.toUpperCase());
        }
    }

    private class NameInstruction implements Instruction {
        public static final String LONG_NAME = "name";
        public static final String SHORT_NAME = "nm";

        private String name;

        public void execute() {
            request.name(name);
        }

        public void parse(String instruction) {
            name = instruction;
        }
    }

    private class PrimitiveContentInstruction implements Instruction {
        public static final String LONG_NAME = "primitivecontent";
        public static final String SHORT_NAME = "pc";

        private PrimitiveContent pc;

        public void execute() {
            request.primitiveContent(pc);
        }

        public void parse(String instruction) {
            pc = Json.newInstance().fromJson(instruction, PrimitiveContent.class);
        }
    }

    private class ErrorInstruction implements Instruction {

        private String errorMessage = "Wrong command";

        public ErrorInstruction(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public void execute() {
            throw new AssertionError(errorMessage);

        }

        public void parse(String instruction) {
        }
    }
}
