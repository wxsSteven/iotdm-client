package org.opendaylight.iotdm.client.impl;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wenxshi on 11/3/15.
 */
public class RestConfIT {
    @Test(expected = AssertionError.class)
    public void restConf_with_invalid_url(){
        RestConf.send("", RestConf.PAYLOAD, RestConf.USERNAME, RestConf.PASSWORD);
    }

    @Test
    public void restConf_with_wrong_url(){
        String wrongUrl="http://localhost:8181/rest/operations/onem2m:onem2m-cse-provisioning/";
        String str=RestConf.send(wrongUrl, RestConf.PAYLOAD, RestConf.USERNAME, RestConf.PASSWORD);
        Assert.assertTrue("Result payload should contains Error 405",str.contains("Error 405 HTTP method POST is not supported"));
    }

    @Test
    public void restConf(){
        String provisionResult=RestConf.defaultProvision();
        String clearResult=RestConf.defaultClear();
        Assert.assertTrue("Result payload should not contains error",!provisionResult.contains("error"));
        Assert.assertTrue(clearResult.isEmpty());
    }

    @Test
    public void Duplicated_RestConf(){
        String provisionResult1=RestConf.defaultProvision();
        String provisionResult2=RestConf.defaultProvision();
        RestConf.defaultClear();
        Assert.assertTrue("First result payload should contains error",!provisionResult1.contains("error"));
        Assert.assertTrue("Second result payload should contains error",provisionResult2.contains("error"));
    }
}
