package org.opendaylight.iotdm.client.command;

import org.junit.Test;
import org.onem2m.xml.protocols.Ae;
import org.onem2m.xml.protocols.PrimitiveContent;
import org.opendaylight.iotdm.client.util.Json;

/**
 * Created by wenxshi on 11/12/15.
 */
public class CommandTest {

    @Test
    public void test_1_command(){
//        String str="coap --operation create --name AE --port 5683 --to InCSE1 --from localhost --requestIdentifier 1234 --resourceType AE --primitiveContent {\"m2m:ae\":{\"rr\":true,\"or\":\"iphone\",\"api\":\"1234\"}}";
//        Command command=new Command();
//        command.parse(str);
//        command.execute();
    }

    @Test
    public void test_2_command(){
//        String str="{\"m2m:ae\":{\"rr\":true,\"or\":\"iphone\"}}";
//        PrimitiveContent pc= Json.newInstance().fromJson(str,PrimitiveContent.class);
//        Ae ae=(Ae) pc.getAny().get(0);
//        System.out.println(ae.getOr());
    }
}
