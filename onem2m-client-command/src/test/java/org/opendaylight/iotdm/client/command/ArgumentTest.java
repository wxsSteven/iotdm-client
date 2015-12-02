package org.opendaylight.iotdm.client.command;

import org.junit.Assert;
import org.junit.Test;
import org.onem2m.xml.protocols.Ae;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.command.api.Interpret;
import org.opendaylight.iotdm.client.command.impl.*;
import org.opendaylight.iotdm.constant.OneM2M;

/**
 * Created by wenxshi on 12/1/15.
 */
public class ArgumentTest {

    @Test
    public void from_test() {
        Request request=new Request();
        Interpret from= ArgumentFactory.getInstance().getCreator(FromArgument.LONG_NAME).create(request);
        from.interpret("1234").execute();
        Assert.assertEquals("1234",request.getRequestPrimitive().getFr());
    }

    @Test
    public void host_test() {
        Request request=new Request();
        Interpret host= ArgumentFactory.getInstance().getCreator(HostArgument.LONG_NAME).create(request);
        host.interpret("localhost").execute();
        Assert.assertEquals("localhost",request.getHost());
    }

    @Test
    public void name_test(){
        Request request=new Request();
        Interpret name=ArgumentFactory.getInstance().getCreator(NameArgument.LONG_NAME).create(request);
        name.interpret("name").execute();
        Assert.assertEquals("name",request.getRequestPrimitive().getNm());
    }

    @Test
    public void operation_test(){
        Request request=new Request();
        Interpret operation=ArgumentFactory.getInstance().getCreator(OperationArgument.LONG_NAME).create(request);
        operation.interpret(OneM2M.Operation.CREATE.name()).execute();
        Assert.assertEquals(OneM2M.Operation.CREATE.value(),request.getRequestPrimitive().getOp());
    }

    @Test
    public void port_test(){
        Request request=new Request();
        Interpret port=ArgumentFactory.getInstance().getCreator(PortArgument.LONG_NAME).create(request);
        port.interpret("1234").execute();
        Assert.assertEquals(1234,request.getPort());
    }


    @Test
    public void primitive_content_test(){
        Request request=new Request();
        Interpret pc=ArgumentFactory.getInstance().getCreator(PrimitiveContentArgument.LONG_NAME).create(request);
        String ae="{\"m2m:ae\":{\"or\":\"1234\"}}";
        pc.interpret(ae).execute();
        Assert.assertTrue("PrimitiveContent should be the instance of Ae", request.getRequestPrimitive().getPc().getAny().get(0) instanceof Ae);
    }
}
