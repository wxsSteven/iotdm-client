package org.opendaylight.iotdm.client.impl;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.scandium.dtls.cipher.CipherSuite;
import org.eclipse.californium.scandium.dtls.pskstore.StaticPskStore;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.logging.Level;

import org.eclipse.californium.scandium.DTLSConnector;
import org.eclipse.californium.scandium.ScandiumLogger;
import org.eclipse.californium.scandium.config.DtlsConnectorConfig;

/**
 * Created by ychau on 7/20/16.
 */

public class Coaps extends Coap {

    private DTLSConnector dtlsConnector;

    private String trustStore = null;
    private String trustStorePassword = null;
    private String certificateName = null;

    private String cseName = null;
    private String csePsk = null;

    public Coaps(final String trustStore, final String trustStorePassword, final String certificateName) {
        initializeDtls(trustStore, trustStorePassword, certificateName);
    }

    public Coaps(final String trustStore, final String trustStorePassword, final String certificateName,
                 boolean debug) {
        turnOnScandiumDebuging(debug);
        initializeDtls(trustStore, trustStorePassword, certificateName);
    }

    public Coaps(final String cseName, final String csePsk) {
        initializeDtlsPsk(cseName, csePsk);
    }

    public Coaps(final String cseName, final String csePsk, boolean debug) {
        turnOnScandiumDebuging(debug);
        initializeDtlsPsk(cseName, csePsk);
    }

    private void turnOnScandiumDebuging(boolean debug) {
        // Turn on debugging of DTLS
        if (debug) {
            ScandiumLogger.initialize();
            ScandiumLogger.setLevel(Level.FINE);
        }
    }

    private void initializeDtlsPsk(final String cseName, final String csePsk) {
        if (null == cseName || cseName.isEmpty() ||
            null == csePsk || csePsk.isEmpty()) {
            throw new IllegalArgumentException("Invalid argument passed");
        }

        this.trustStore = null;
        this.trustStorePassword = null;
        this.certificateName = null;

        this.cseName = cseName;
        this.csePsk = csePsk;

        //Set up DTLS PSK
        DtlsConnectorConfig.Builder builder = new DtlsConnectorConfig.Builder(new InetSocketAddress(0));
        builder.setClientOnly();

        builder.setPskStore(new StaticPskStore(cseName, csePsk.getBytes()));
        builder.setSupportedCipherSuites(new CipherSuite[]{CipherSuite.TLS_PSK_WITH_AES_128_CCM_8,
                                                           CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256});

        dtlsConnector = new DTLSConnector(builder.build());
        EndpointManager.getEndpointManager().setDefaultSecureEndpoint(
                new CoapEndpoint(dtlsConnector, NetworkConfig.getStandard()));
    }

    private void initializeDtls(final String trustStore, final String trustStorePassword, final String certificateName) {
        if (trustStore == null || trustStore.isEmpty() ||
            trustStorePassword == null || trustStorePassword.isEmpty() ||
            certificateName == null || certificateName.isEmpty()) {
            throw new IllegalArgumentException("Invalid arguments passed");
        }

        this.trustStore = trustStore;
        this.trustStorePassword = trustStorePassword;
        this.certificateName = certificateName;

        this.cseName = null;
        this.csePsk = null;

        //Set up DTLS
        try {
            // load trust store
            KeyStore trust = KeyStore.getInstance("JKS");
            InputStream inTrust = new FileInputStream(this.trustStore);
            trust.load(inTrust, this.trustStorePassword.toCharArray());

            // You can load multiple certificates if needed
            Certificate[] trustedCertificates = new Certificate[1];
            trustedCertificates[0] = trust.getCertificate(this.certificateName);

            DtlsConnectorConfig.Builder builder = new DtlsConnectorConfig.Builder(new InetSocketAddress(0));
            builder.setClientOnly();

            builder.setSupportedCipherSuites(new CipherSuite[]{CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256,
                                                               CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8});
            builder.setTrustStore(trustedCertificates);

            dtlsConnector = new DTLSConnector(builder.build());
            EndpointManager.getEndpointManager().setDefaultSecureEndpoint(
                    new CoapEndpoint(dtlsConnector, NetworkConfig.getStandard()));
        } catch (GeneralSecurityException | IOException e) {
            System.err.println("Could not load the keystore");
            e.printStackTrace();
        }
    }

    @Override
    public Response send(Request request) {
        org.eclipse.californium.core.coap.Request coapRequest = new Coap.CoapRequestBuilder(request).build();
        EndpointManager.getEndpointManager().getDefaultSecureEndpoint().sendRequest(coapRequest);
        return getOnem2mResponse(coapRequest, request);
    }
}
