package org.opendaylight.iotdm.client.command;

import org.junit.Test;
import org.opendaylight.iotdm.client.command.exception.NoArgumentError;
import org.opendaylight.iotdm.client.command.exception.NoValueOfArgumentError;
import org.opendaylight.iotdm.client.command.exception.NotArgumentError;
import org.opendaylight.iotdm.client.command.exception.NotCommandError;

/**
 * Created by wenxshi on 11/23/15.
 */
public class ParserTest {

    @Test(expected = NotCommandError.class)
    public void not_command() {
        String str = "ss";
        Parser.parse(str);
    }

    @Test(expected = NoArgumentError.class)
    public void no_argument() {
        String str = "coap";
        Parser.parse(str);
    }

    @Test(expected = NotArgumentError.class)
    public void not_argument() {
        String str = "coap --s";
        Parser.parse(str);
    }

    @Test(expected = NoValueOfArgumentError.class)
    public void no_argument_value() {
        String str = "coap --operation";
        Parser.parse(str);
    }
}
