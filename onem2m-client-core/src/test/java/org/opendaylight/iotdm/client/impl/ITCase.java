package org.opendaylight.iotdm.client.impl;

import com.google.gson.JsonObject;
import org.junit.Assert;
import org.onem2m.xml.protocols.Ae;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;
import org.opendaylight.iotdm.client.api.Client;
import org.opendaylight.iotdm.client.util.Json;
import org.opendaylight.iotdm.constant.OneM2M;

import java.util.concurrent.TimeUnit;

/**
 * Created by wenxshi on 11/9/15.
 */
public class ITCase {

    private Client client;
    private String host;
    private long timeout;
    private int port;

    public ITCase(Client client, String host, int port, long timeout) {
        this.client = client;
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    //InCSE1
    public static void suitUp(String host) {
        RestConf.defaultProvision(host);
    }

    //
    public static void suitDown(String host) {
        RestConf.defaultClear();
    }

    private Request newRequest() {
        return new Request()
                .host(host)
                .port(port)
                .timeout(timeout, TimeUnit.MILLISECONDS);
    }

    private Request newCleanRequest(String path) {
        return newRequest()
                .to(path)
                .operation(OneM2M.Operation.DELETE)
                .from(host)
                .requestIdentifier("1234");
    }

    //InCSE1->AE
    public void create_ae() {
        Request request = newRequest()
                .to("InCSE1")
                .operation(OneM2M.Operation.CREATE)
                .resourceType(OneM2M.ResourceType.AE)
                .from("testAEClient")
                .requestIdentifier("1234")
                .name("testAEClient");

        Ae ae = new Ae();
        ae.setOr("iphone");
        ae.setRr(true);
        ae.setApi("12345");
        ae.setOr(Json.STRING_NULL);

        request.addPrimitiveContent(ae);
        Response response = client.send(request);
        ae = (Ae) response.getPrimitiveContent().getAny().get(0);

        Assert.assertNull("or should be not null", ae.getOr());
        Assert.assertEquals("requireIdentifer of response", "1234", response.getRequestIdentifier());
        Assert.assertEquals("response code should be Created", response.getResponseStatusCode(), OneM2M.ResponseStatusCodes.CREATED);
    }

    //InCSE1->AE
    public void update_ae() {
        Request request = newRequest()
                .to("InCSE1/testAEClient")
                .operation(OneM2M.Operation.UPDATE)
                .from("testAEClient")
                .requestIdentifier("1234");

        Ae ae = new Ae();
        ae.setOr("ipad");

        request.addPrimitiveContent(ae);
        Response response = client.send(request);

        ae = (Ae) response.getPrimitiveContent().getAny().get(0);
        Assert.assertEquals("requireIdentifer of response", "1234", response.getRequestIdentifier());
        Assert.assertEquals("response code should be changed", response.getResponseStatusCode(), OneM2M.ResponseStatusCodes.CHANGED);
        Assert.assertEquals(ae.getOr(), "ipad");
    }

    //InCSE1->AE
    public void retrieve_ae() {
        Request request = newRequest()
                .to("InCSE1/testAEClient")
                .operation(OneM2M.Operation.RETRIEVE)
                .from("testAEClient")
                .requestIdentifier("1234");

        Response response = client.send(request);
        Assert.assertEquals("requireIdentifer of response", "1234", response.getRequestIdentifier());
        Assert.assertEquals("response code should be OK", response.getResponseStatusCode(), OneM2M.ResponseStatusCodes.OK);
    }

    //InCSE1
    public void delete_ae() {
        Request request = newRequest()
                .to("InCSE1/testAEClient")
                .operation(OneM2M.Operation.DELETE)
                .from("testAEClient")
                .requestIdentifier("1234");

        Response response = client.send(request);
        Assert.assertEquals("requireIdentifer of response", "1234", response.getRequestIdentifier());
        Assert.assertEquals("response code should be deleted", response.getResponseStatusCode(), OneM2M.ResponseStatusCodes.DELETED);
    }


    public void create_ae_without_mandatory_attribute_request_identifier_and_result_expect_error() {
        Request request = newRequest()
                .to("InCSE1")
                .operation(OneM2M.Operation.CREATE)
                .resourceType(OneM2M.ResourceType.AE)
                .from("localhost")
                .name("testAEClient");

        Ae ae = new Ae();
        ae.setOr("iphone");
        ae.setRr(true);
        ae.setApi("12345");

        request.addPrimitiveContent(ae);
        Response response = client.send(request);
        JsonObject object = (JsonObject) response.getPrimitiveContent().getAny().get(0);
        System.out.println(object);

        Assert.assertTrue("This should be error message", object.has(OneM2M.ERROR_INDICATOR));
        String message = object.get(OneM2M.ERROR_INDICATOR).getAsString();
        System.out.println(message);
        Assert.assertTrue("Error message should indicate  missing mandatory 'requestIdentifier' attribute", message.contains("requestIdentifier") || message.contains("rqi"));
    }

    public void create_ae_without_mandatory_attribute_from_and_result_expect_error() {
        Request request = newRequest()
                .to("InCSE1")
                .operation(OneM2M.Operation.CREATE)
                .resourceType(OneM2M.ResourceType.AE)
                .requestIdentifier("12345")
                .name("testAEClient");

        Ae ae = new Ae();
        ae.setOr("iphone");
        ae.setRr(true);
        ae.setApi("12345");

        request.addPrimitiveContent(ae);
        Response response = client.send(request);

        JsonObject object = (JsonObject) response.getPrimitiveContent().getAny().get(0);
        System.out.println(object);

        Assert.assertEquals("requireIdentifer of response", "12345", response.getRequestIdentifier());
        Assert.assertTrue("This should be error message", object.has(OneM2M.ERROR_INDICATOR));
        String message = object.get(OneM2M.ERROR_INDICATOR).getAsString();
        Assert.assertTrue("Error message should indicate  missing mandatory 'from' attribute", message.contains("from") || message.contains("fr"));
    }

    public void create_ae_without_mandatory_attribute_resource_type_and_result_expect_error() {
        Request request = newRequest()
                .to("InCSE1")
                .operation(OneM2M.Operation.CREATE)
                .requestIdentifier("1234")
                .from("testAEClient")
                .name("testAEClient");

        Ae ae = new Ae();
        ae.setOr("iphone");
        ae.setRr(true);
        ae.setApi("12345");

        request.addPrimitiveContent(ae);
        Response response = client.send(request);
        JsonObject object = (JsonObject) response.getPrimitiveContent().getAny().get(0);
        System.out.println(object);

        Assert.assertEquals("requireIdentifer of response", "1234", response.getRequestIdentifier());
        Assert.assertTrue("This should be error message", object.has(OneM2M.ERROR_INDICATOR));
        String message = object.get(OneM2M.ERROR_INDICATOR).getAsString();
        Assert.assertTrue("Error message should indicate  missing mandatory 'resourceType' attribute", message.contains("resourceType") || message.contains("ty"));
    }

    public void create_ae_with_wrong_resource_type_and_result_expect_error() {
        Request request = newRequest()
                .to("InCSE1")
                .operation(OneM2M.Operation.CREATE)
                .resourceType(OneM2M.ResourceType.CONTENT_INSTANCE)
                .from("testAEClient")
                .requestIdentifier("1234")
                .name("testAEClient");

        Ae ae = new Ae();
        ae.setOr("iphone");
        ae.setRr(true);
        ae.setApi("12345");

        request.addPrimitiveContent(ae);
        Response response = client.send(request);
        JsonObject object = (JsonObject) response.getPrimitiveContent().getAny().get(0);
        System.out.println(object);

        Assert.assertEquals("requireIdentifer of response", "1234", response.getRequestIdentifier());
        Assert.assertTrue("This should be error message", object.has(OneM2M.ERROR_INDICATOR));
        String message = object.get(OneM2M.ERROR_INDICATOR).getAsString();
        Assert.assertTrue("Error message should indicate  missing mandatory 'resourceType' attribute", message.contains("mismatch"));
    }

    public void update_ae_with_resource_type_and_result_expect_error() {
        Request request = newRequest()
                .to("InCSE1")
                .operation(OneM2M.Operation.CREATE)
                .resourceType(OneM2M.ResourceType.AE)
                .from("testAEClient")
                .requestIdentifier("1234")
                .name("testAEClient");

        Ae ae = new Ae();
        ae.setOr("iphone");
        ae.setRr(true);
        ae.setApi("12345");

        request.addPrimitiveContent(ae);
        client.send(request);

        request = newRequest().to("InCSE1/testAEClient").operation(OneM2M.Operation.UPDATE).requestIdentifier("1234").from("localhost").resourceType(OneM2M.ResourceType.ACCESS_CONTROL_POLICY);
        ae.setOr("ipad");
        request.addPrimitiveContent(ae);
        Response response = client.send(request);

        JsonObject error = (JsonObject) response.getPrimitiveContent().getAny().get(0);
        System.out.println(error);
        String message = error.get(OneM2M.ERROR_INDICATOR).getAsString();
        Assert.assertTrue("This should be error message", error.has(OneM2M.ERROR_INDICATOR));
        Assert.assertTrue("Error message should indicate tt's wrong to contain resourceType in update", message.contains("resourceType") || message.contains("ty"));
    }

    public void update_ae_by_delete_one_attribute() {
        Request request = newRequest()
                .to("InCSE1")
                .operation(OneM2M.Operation.CREATE)
                .resourceType(OneM2M.ResourceType.AE)
                .from("localhost")
                .requestIdentifier("1234")
                .name("testAEClient");

        Ae ae = new Ae();
        ae.setOr("iphone");
        ae.setRr(true);
        ae.setApi("12345");

        request.addPrimitiveContent(ae);
        client.send(request);

        request = newRequest().to("InCSE1/testAEClient").operation(OneM2M.Operation.UPDATE).requestIdentifier("1234").from("localhost").resourceType(OneM2M.ResourceType.ACCESS_CONTROL_POLICY);
        ae.setOr(Json.STRING_NULL);
        request.addPrimitiveContent(ae);
        Response response = client.send(request);

        JsonObject error = (JsonObject) response.getPrimitiveContent().getAny().get(0);
        System.out.println(error);
        String message = error.get(OneM2M.ERROR_INDICATOR).getAsString();
        Assert.assertTrue("This should be error message", error.has(OneM2M.ERROR_INDICATOR));
        Assert.assertTrue("Error message should indicate tt's wrong to contain resourceType in update", message.contains("resourceType") || message.contains("ty"));
    }
}
