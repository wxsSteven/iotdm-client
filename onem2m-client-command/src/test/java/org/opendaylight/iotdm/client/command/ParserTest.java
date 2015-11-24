package org.opendaylight.iotdm.client.command;

import org.junit.Test;
import org.opendaylight.iotdm.client.command.impl.CoapExecutable;

/**
 * Created by wenxshi on 11/23/15.
 */
public class ParserTest {

    @Test
    public void test1() {
        String str = "coap --operation create --name AE --port 5683 --to InCSE1 --from localhost --requestIdentifier 1234 --resourceType AE --primitiveContent {\"m2m:ae\":{\"rr\":true,\"or\":\"iphone\",\"api\":\"1234\"}}";
        Parser.parse(str).execute();
    }
}
