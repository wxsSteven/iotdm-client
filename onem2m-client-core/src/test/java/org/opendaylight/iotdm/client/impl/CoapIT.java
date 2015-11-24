package org.opendaylight.iotdm.client.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.opendaylight.iotdm.client.api.Client;

/**
 * Created by wenxshi on 11/3/15.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoapIT {
    static Client client = new Coap();
    ITCase itCase = new ITCase(client, RestConf.HOST, 5683, 1000);

    @BeforeClass
    public static void suitUp() {
        RestConf.defaultProvision();
        client.start();
    }

    @AfterClass
    public static void suitDown() {
        RestConf.defaultClear();
        client.stop();
    }

    @Test
    public void test_1_create_ae() {
        itCase.create_ae();
    }

    @Test
    public void test_2_update_ae() {
        itCase.update_ae();
    }

    @Test
    public void test_3_retrieve_ae() {
        itCase.retrieve_ae();
    }

    @Test
    public void test_4_delete_ae() {
        itCase.delete_ae();
    }

    @Test
    public void test_5_create_ae_without_request_mandatory_attribute_request_identifier_and_result_expect_error() {
        itCase.create_ae_without_mandatory_attribute_request_identifier_and_result_expect_error();
    }

    @Test
    public void test_6_create_ae_without_request_mandatory_attribute_from_and_result_expect_error() {
        itCase.create_ae_without_mandatory_attribute_from_and_result_expect_error();
    }

    @Test
    public void test_7_create_ae_without_mandatory_attribute_resource_type_and_result_expect_error() {
        itCase.create_ae_without_mandatory_attribute_resource_type_and_result_expect_error();
    }

    @Test
    public void test_8_create_ae_with_wrong_resource_type_and_result_expect_error() {
        itCase.create_ae_with_wrong_resource_type_and_result_expect_error();
    }

    @Test
    public void test_9_update_ae_with_resource_type_and_result_expect_error() {
        itCase.update_ae_with_resource_type_and_result_expect_error();
    }

//    @Test
//    public void test_5_create_container_under_ae() {
//        Cnt container=new Cnt();
//        container.setOr("iphone");
//        Request request = new Request()
//                .timeout(1000, TimeUnit.MILLISECONDS)
//                .to("InCSE1/AE")
//                .port(5683)
//                .host("localhost")
//                .operation(OneM2M.Operation.CREATE)
//                .resourceType(OneM2M.ResourceType.CONTAINER)
//                .from("localhost")
//                .name("Container")
//                .requestIdentifier("12345")
//                .addPrimitiveContent(container);
//
//        Response response=client.send(request);
//        System.out.println(Json.newInstance().toJson(response.getPrimitiveContent()));
//    }
//
//    @Test
//    public void test_6_create_content_instance_under_container() {
//        Cin contentInstance = new Cin();
//        contentInstance.setCon("5");
//        Request request = new Request()
//                .timeout(1000, TimeUnit.MILLISECONDS)
//                .to("InCSE1/AE/Container")
//                .port(5683)
//                .host("localhost")
//                .operation(OneM2M.Operation.CREATE)
//                .resourceType(OneM2M.ResourceType.CONTENT_INSTANCE)
//                .from("localhost")
//                .name("ConstantInstance")
//                .requestIdentifier("12345")
//                .addPrimitiveContent(contentInstance);
//
//        Response response=client.send(request);
//        System.out.println(Json.newInstance().toJson(response.getPrimitiveContent()));
//    }
//
//    @Test
//    public void test_7_retrieve_ae(){
//        Request request = new Request()
//                .timeout(1000,TimeUnit.MILLISECONDS)
//                .port(5683)
//                .host("localhost")
//                .to("InCSE1/AE")
//                .operation(OneM2M.Operation.RETRIEVE)
//                .from("localhost")
//                .requestIdentifier("1234");
//
//        Response response=client.send(request);
//        System.out.println(Json.newInstance().toJson(response.getPrimitiveContent()));
//    }
}
