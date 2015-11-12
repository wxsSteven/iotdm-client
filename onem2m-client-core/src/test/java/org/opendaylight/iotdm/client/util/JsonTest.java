
/**
 * Created by wenxshi on 10/14/15.
 */
package org.opendaylight.iotdm.client.util;

import com.google.gson.JsonSyntaxException;
import org.junit.Assert;
import org.junit.Test;
import org.onem2m.xml.protocols.Ae;
import org.onem2m.xml.protocols.Cnt;
import org.onem2m.xml.protocols.PrimitiveContent;
import org.onem2m.xml.protocols.Rqp;
import org.opendaylight.iotdm.constant.OneM2M;

import java.math.BigInteger;

public class JsonTest {

    @Test
    public void aE_readability_to_be_null_serialization(){
        Ae ae=new Ae();
        ae.setRr(null);
        String str=Json.newInstance().toJson(ae);
        assert(!str.contains("\"rr\""));
    }

    @Test
    public void aE_readability_to_be_json_null_serialization(){
        Ae ae=new Ae();
        ae.setRr(Json.BOOLEAN_NULL);
        String str=Json.newInstance().toJson(ae);
        assert(str.contains("\"rr\":null"));
    }

    @Test
    public void aE_resource_name_to_be_json_null_serialization(){
        Ae ae=new Ae();
        ae.setRn(Json.STRING_NULL);
        String str=Json.newInstance().toJson(ae);
        assert(str.contains("\"rn\":null"));
    }

    @Test
    public void aE_resource_type_to_be_json_null_serialization(){
        Ae ae=new Ae();
        ae.setTy(Json.BIG_INTEGER_NULL);
        String str=Json.newInstance().toJson(ae);
        assert(str.contains("\"ty\":null"));
    }

    @Test
    public void request_primitive_contain_Ae() {
        Rqp requestPrimitive = new Rqp();
        requestPrimitive.setRqi("1234");
        requestPrimitive.setPc(new PrimitiveContent());
        Ae ae=new Ae();
        ae.setRr(false);
        ae.setTy(OneM2M.ResourceType.AE.value());
        requestPrimitive.getPc().getAny().add(ae);
        String str=Json.newInstance().toJson(requestPrimitive);
        assert(str.contains("\"rqi\":\"1234\""));
        assert(str.contains("\"m2m:ae\":{"));
    }

    @Test
    public void request_primitive_contain_Ae_list_serialization(){
        Rqp requestPrimitive = new Rqp();
        requestPrimitive.setRqi("1234");
        requestPrimitive.setPc(new PrimitiveContent());
        Ae ae=new Ae();
        ae.setRr(false);
        ae.setTy(OneM2M.ResourceType.AE.value());
        requestPrimitive.getPc().getAny().add(ae);
        requestPrimitive.getPc().getAny().add(ae);
        String str=Json.newInstance().toJson(requestPrimitive);
        assert(str.contains("\"rqi\":\"1234\""));
        assert(str.contains("\"m2m:ae\":["));
    }

    @Test
    public void request_primitive_contain_Ae_list_and_container_serialization(){
        Rqp requestPrimitive = new Rqp();
        requestPrimitive.setRqi("1234");
        requestPrimitive.setPc(new PrimitiveContent());
        Ae ae=new Ae();
        ae.setRr(false);
        ae.setTy(OneM2M.ResourceType.AE.value());
        requestPrimitive.getPc().getAny().add(ae);
        requestPrimitive.getPc().getAny().add(ae);
        Cnt cnt=new Cnt();
        cnt.setTy(BigInteger.ONE);
        cnt.setCni(BigInteger.TEN);
        requestPrimitive.getPc().getAny().add(cnt);
        String str=Json.newInstance().toJson(requestPrimitive);
        assert(str.contains("\"rqi\":\"1234\""));
        assert(str.contains("\"m2m:ae\":["));
        assert(str.contains("\"m2m:cnt\":{"));
    }

    @Test
    public void ae_json_deserialization(){
        String str="{\"rr\":false}";
        Ae ae=Json.newInstance().fromJson(str,Ae.class);
        Assert.assertEquals(ae.getRr().booleanValue(),false);
    }

    @Test
    public void ae_json_null_attribute_deserialization(){
        String str="{\"rr\":null}";
        Ae ae=Json.newInstance().fromJson(str,Ae.class);
        Assert.assertEquals(ae.getRr(),Json.BOOLEAN_NULL);
    }

    @Test
    public void ae_json_string_attribute_deserialization(){
        String str="{\"rr\":false,\"api\":\"a\"}";
        Ae ae=Json.newInstance().fromJson(str,Ae.class);
        Assert.assertEquals(ae.getApi(),"a");
    }

    @Test
    public void request_primitive_contain_ae_deserialization(){
        String str="{\"rqi\":\"1234\",\"pc\":{\"m2m:ae\":{\"rr\":false,\"ty\":2}}}";
        Rqp rqp=Json.newInstance().fromJson(str,Rqp.class);
        Assert.assertEquals(rqp.getRqi(),"1234");
        Assert.assertEquals(rqp.getPc().getAny().get(0).getClass(),Ae.class);
        Ae ae=(Ae) rqp.getPc().getAny().get(0);
        Assert.assertEquals(ae.getRr(),false);
        Assert.assertEquals(ae.getTy(),BigInteger.valueOf(2));
    }

    @Test
    public void request_primitive_contain_ae_array_deserialization(){
        String str="{\"rqi\":\"1234\",\"pc\":{\"m2m:ae\":[{\"rr\":false,\"ty\":2},{\"rr\":true,\"ty\":3}]}}";
        Rqp rqp=Json.newInstance().fromJson(str,Rqp.class);
        Assert.assertEquals(rqp.getRqi(),"1234");
        Assert.assertEquals(rqp.getPc().getAny().get(1).getClass(),Ae.class);
        Ae ae=(Ae) rqp.getPc().getAny().get(1);
        Assert.assertEquals(ae.getRr(),true);
        Assert.assertEquals(ae.getTy(),BigInteger.valueOf(3));
    }

    @Test
    public void request_primitive_contain_ae_array_container_deserialization(){
        String str="{\"rqi\":\"1234\",\"pc\":{\"m2m:ae\":[{\"rr\":false,\"ty\":1},{\"rr\":true,\"ty\":2}],\"m2m:cnt\":{\"cni\":10,\"ty\":1}}}";
        Rqp rqp=Json.newInstance().fromJson(str,Rqp.class);
        Assert.assertEquals(rqp.getRqi(),"1234");
        Assert.assertEquals(rqp.getPc().getAny().get(0).getClass(),Ae.class);
        Ae ae=(Ae) rqp.getPc().getAny().get(0);
        Assert.assertEquals(ae.getRr(),false);
        Assert.assertEquals(ae.getTy(),BigInteger.valueOf(1));
        Cnt cnt=(Cnt) rqp.getPc().getAny().get(2);
        Assert.assertEquals(cnt.getCni(),BigInteger.valueOf(10));
    }

    @Test
    public void request_primitive_serialization_and_deserialization(){
        Rqp requestPrimitive = new Rqp();
        requestPrimitive.setRqi("1234");
        requestPrimitive.setPc(new PrimitiveContent());
        Ae ae=new Ae();
        ae.setRr(false);
        ae.setTy(OneM2M.ResourceType.AE.value());
        requestPrimitive.getPc().getAny().add(ae);
        requestPrimitive.getPc().getAny().add(ae);
        Cnt cnt=new Cnt();
        cnt.setTy(BigInteger.ONE);
        cnt.setCni(BigInteger.TEN);
        requestPrimitive.getPc().getAny().add(cnt);
        String str=Json.newInstance().toJson(requestPrimitive);

        Rqp requestPrimitiveD=Json.newInstance().fromJson(str,Rqp.class);
        String strD=Json.newInstance().toJson(requestPrimitiveD);
        Assert.assertEquals(str,strD);
    }

    @Test(expected = AssertionError.class)
    public void invalid_json_deserialzation(){
        String json="{lsjfjjakf}";
        Json.newInstance().fromJson(json,String.class);
    }
}
