package org.opendaylight.iotdm.client.command.api;

/**
 * Created by wenxshi on 11/17/15.
 */
public interface Option{
    public void parse(String value);
    public void Execute();
    public Option create();
}
