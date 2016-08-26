/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.iotdm.client.impl.oauth2;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.FormContentProvider;
import org.eclipse.jetty.http.HttpScheme;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.util.Fields;
import org.json.JSONObject;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;
import org.opendaylight.iotdm.client.impl.Http;
import org.opendaylight.iotdm.client.impl.RequestHelper;
import org.opendaylight.iotdm.constant.OneM2M;

import java.util.concurrent.TimeUnit;

/*
 * Class extends standard HTTP client class (and class which extends the HTTP client)
 * with Oauth2 authentication method.
 * Client credentials grant type (specified by Oauth2 framework) is used.
 *
 * Application using this class acts as Onem2m Application Entity (AE) and
 * must have specified it's AE-ID (used as name) and password which are
 * used as client credentials when Oauth2 access token is requested.
 *
 * Requests for access token are being sent to the IoTDM's "/access_token"
 * endpoint. Basic HTTP authentication method is used.
 */
public class Oauth2Common <T extends Http> {

    protected final T httpImpl;
    protected final String aeId;
    protected final String aePassword;
    protected final boolean secureConnection;

    protected String accessToken = null;

    public Oauth2Common(final String aeId,
                        final String aePassword,
                        final T httpImpl,
                        final boolean secureConnection) {
        this.aeId = aeId;
        this.aePassword = aePassword;
        this.httpImpl = httpImpl;
        this.secureConnection = secureConnection;
    }

    public Response sendHttpRequest(org.eclipse.jetty.client.api.Request httpRequest) {
        ContentResponse contentResponse;
        try {
            contentResponse = httpRequest.send();
        } catch (Exception e) {
            System.err.println("Exception: " + e);
            throw new AssertionError(e.getMessage());
        }

        Response response = new T.ResponseBuilder(contentResponse).build();
        return response;
    }

    public Response send(Request request) {
        Response rsp = null;
        org.eclipse.jetty.client.api.Request httpRequest = httpImpl.buildHttpRequest(request);

        // Make the first attempt without access token and retry if failed
        if (null == accessToken) {
            // send request without Oauth2 token
            rsp = sendHttpRequest(httpRequest);
        } else {
            // there's access_token stored, use it
            httpRequest.param("access_token", this.accessToken);
            rsp = sendHttpRequest(httpRequest);
        }

        if (rsp.getResponseStatusCode() == OneM2M.ResponseStatusCodes.ACCESS_DENIED) {
            /* Access token has not been used but must be used.
             * Or access token has been used but has already expired.
             * So request access token and retry.
             */
            this.accessToken = getNewAccessToken(request);
            if (null != this.accessToken) {
                // we have new access token, let's try the same request again
                httpRequest = httpImpl.buildHttpRequest(request);
                httpRequest.param("access_token", this.accessToken);
                rsp = sendHttpRequest(httpRequest);
            }
        }

        return rsp;
    }

    private String getNewAccessToken(final Request request) {
        org.eclipse.jetty.client.api.Request httpRequest = null;
        RequestHelper requestHelper = new RequestHelper(request);

        httpRequest = httpImpl.httpClient.newRequest(requestHelper.getHost(), requestHelper.getPort())
                              .timeout(requestHelper.getTimeout(), TimeUnit.MILLISECONDS);

        httpRequest.method("post");
        httpRequest.path("/access_token");
        httpRequest.header(OneM2M.Http.Header.CONTENT_TYPE, "application/x-www-form-urlencoded");

        // basic authentication
        String basic_auth = "Basic " + (new String(Base64.encodeBase64((aeId + ":" + aePassword).getBytes())));
        httpRequest.header("Authorization", basic_auth);

        Fields payload = new Fields();
        payload.add("grant_type", "client_credentials");
        FormContentProvider contentProvider = new FormContentProvider(payload);
        httpRequest.content(contentProvider);

        if (this.secureConnection) {
            httpRequest.scheme(HttpScheme.HTTPS.toString());
        }

        ContentResponse contentResponse;
        try {
            contentResponse = httpRequest.send();
        } catch (Exception e) {
            System.err.println("Exception: " + e);
            throw new AssertionError(e.getMessage());
        }

        if (contentResponse.getStatus() != HttpStatus.OK_200) {
            System.err.println("Invalid access token retrieve status: " + contentResponse.getStatus());
            return null;
        }

        try {
            String content = new String(contentResponse.getContent());
            JSONObject jsonObj = new JSONObject(content);

            return jsonObj.getString("access_token");
        } catch (Exception e) {
            System.err.println("Failed to process access token response content: " + e.toString());
        }

        return null;
    }

    /*
     * Can't use custom keystore with the google oauth api
     */
//    private String getNewAccessToken(@Nonnull final String authServerURL) {
//        /*
//         * Use client credentials in HTTP basic authentication.
//         * Request Oauth2 access token with client_credentials grant type.
//         */
//        try {
//            TokenResponse response =
//                    new ClientCredentialsTokenRequest(new NetHttpTransport(), new JacksonFactory(),
//                                                      new GenericUrl(authServerURL))
//                            .setClientAuthentication(
//                                    new BasicAuthentication(this.aeId, this.aePassword)).execute();
//            System.out.println("Debug: Access token: " + response.getAccessToken());
//            return response.getAccessToken();
//        } catch (TokenResponseException e) {
//            System.err.println("Token request resulted with exception: " + e);
//        } catch (IOException e) {
//            System.err.println("Access token request failed: " + e);
//        } catch (Exception e) {
//            System.err.println("Unknown error: " + e);
//        }
//
//        return null;
//    }
}
