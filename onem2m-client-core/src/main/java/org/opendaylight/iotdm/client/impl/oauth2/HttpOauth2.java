package org.opendaylight.iotdm.client.impl.oauth2;


import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;
import org.opendaylight.iotdm.client.impl.Http;

public class HttpOauth2 extends Http {

    private final Oauth2Common<Http> oauth2Common;

    public HttpOauth2(final String aeId,
                      final String aePassword) {
        this.oauth2Common = new Oauth2Common<>(aeId, aePassword, this, false);
    }

    @Override
    public Response send(Request request) {
        return this.oauth2Common.send(request);
    }
}
