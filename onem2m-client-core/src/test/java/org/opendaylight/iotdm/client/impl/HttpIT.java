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
public class HttpIT {
    static Client client = new Http();
    ITCase itCase = new ITCase(client, RestConf.HOST, 8282, 1000);

    @BeforeClass
    public static void suitUp() {
        ITCase.suitUp(RestConf.HOST);
        client.start();
    }

    @AfterClass
    public static void suitDown() {
        client.stop();
        ITCase.suitDown(RestConf.HOST);
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
    public void test_7_create_ae_without_mandatory_attribute_result_type_and_result_expect_error() {
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
}
