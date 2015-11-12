package org.opendaylight.iotdm.client.impl;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.api.Client;

/**
 * Created by wenxshi on 10/27/15.
 */
public class HttpTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery(){{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    public Client client = context.mock(Http.class);

    @Test
    public void http_send_function() {
        final Request iotdmExchange = new Request();
        context.checking(new Expectations() {
            {
                oneOf(client).send(iotdmExchange);
            }
        });
        client.send(iotdmExchange);
    }
}
