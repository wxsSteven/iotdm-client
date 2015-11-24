package org.opendaylight.iotdm.constant;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Created by wenxshi on 11/3/15.
 */
public class OneM2MTest {

    @Test
    public void time_parse_test() {
        String time = "20151003T060611";
        String result = OneM2M.Time.parse(time).toString();
        Assert.assertEquals(result, "2015-10-03T06:06:11");
    }

    @Test
    public void time_format_test() {
        LocalDateTime localDateTime = LocalDateTime.parse("2015-10-03T06:06:11");
        String time = OneM2M.Time.format(localDateTime);
        Assert.assertEquals(time, "20151003T060611");
    }

    @Test
    public void time_current_time_test() {
        String time = OneM2M.Time.currentTime();
        String time2 = OneM2M.Time.format(OneM2M.Time.parse(time));
        Assert.assertEquals(time, time2);
    }
}
