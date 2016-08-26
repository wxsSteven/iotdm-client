package org.opendaylight.iotdm.client.impl;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.http.HttpScheme;
import org.opendaylight.iotdm.client.Request;

import org.eclipse.jetty.util.ssl.SslContextFactory;

/**
 * Created by Peter Chau on 07/20/16.
 */
public class Https extends Http {

    public Https(String trustStore, String storePassword) {
        if (null == trustStore || null == storePassword) {
            throw new IllegalArgumentException("Invalid TrustStore parameters");
        }

        SslContextFactory sslContextFactory = new SslContextFactory(false);
        sslContextFactory.setTrustStorePath(trustStore);
        sslContextFactory.setTrustStorePassword(storePassword);
        httpClient = new HttpClient(sslContextFactory);
    }

    @Override
    public org.eclipse.jetty.client.api.Request buildHttpRequest(Request request) {
        org.eclipse.jetty.client.api.Request httpRequest = super.buildHttpRequest(request);
        if (null != httpRequest) {
            httpRequest.scheme(HttpScheme.HTTPS.toString());
        }
        return httpRequest;
    }
}

