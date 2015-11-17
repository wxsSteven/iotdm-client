package org.opendaylight.iotdm.client.command;

import org.opendaylight.iotdm.client.command.exception.NotKeyWordException;

import java.util.*;

/**
 * Created by wenxshi on 11/17/15.
 */
public class Arguments {
    private Queue<Argument> queue = new LinkedList<Argument>();

    public Arguments(String[] args) {
        Objects.requireNonNull(args);

        if (args.length == 0)
            return;

        String key = args[0];
        if (!CommandFactory.isKeyword(key))
            throw new NotKeyWordException(key);

        int k = 0;
        for (int i = k + 1; i < args.length; i++) {
            if (CommandFactory.isOption(args[i])) {
                queue.add(new Argument(args[k], Arrays.copyOfRange(args, k + 1, i)));
                k = i;
            }
        }

        if (k + 1 >= args.length)
            queue.add(new Argument(args[k], Arrays.copyOfRange(args, k + 1, args.length)));
        else
            queue.add(new Argument(args[k], new String[0]));
    }


    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Argument peek(){
        return queue.peek();
    }

    public Argument poll(){
        return queue.poll();
    }

    public class Argument {
        String keyword;
        String[] args;

        public Argument(String keyword, String[] args) {
            Objects.requireNonNull(keyword);
            Objects.requireNonNull(args);

            this.keyword = keyword;
            this.args = args;
        }

        public String getKeyword() {
            return keyword;
        }

        public String[] getArgs() {
            return args;
        }
    }
}
