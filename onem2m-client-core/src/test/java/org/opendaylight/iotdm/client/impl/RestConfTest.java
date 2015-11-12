package org.opendaylight.iotdm.client.impl;

import org.junit.Test;

/**
 * Created by wenxshi on 11/3/15.
 */
public class RestConfTest {
    @Test(expected = AssertionError.class)
    public void restConf_null_value_of_url(){
        RestConf.send(RestConf.USERNAME,RestConf.PAYLOAD,RestConf.USERNAME,RestConf.PASSWORD);
    }

    @Test(expected = AssertionError.class)
    public void restConf_null_value_of_username(){
        RestConf.send(RestConf.USERNAME,RestConf.PAYLOAD,null,RestConf.PASSWORD);
    }

    @Test(expected = AssertionError.class)
    public void restConf_null_value_of_password(){
        RestConf.send(RestConf.USERNAME,RestConf.PAYLOAD,null,RestConf.PASSWORD);
    }
}
