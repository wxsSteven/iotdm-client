package org.opendaylight.iotdm.client.impl;

import com.google.api.client.auth.oauth2.ClientCredentialsTokenRequest;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.eclipse.jetty.client.api.ContentResponse;
import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;
import org.opendaylight.iotdm.constant.OneM2M;

import javax.annotation.Nonnull;
import java.io.IOException;

/*
 * Class extends standard HTTP client with Oauth2 authentication method.
 * Client credentials grant type (specified by Oauth2 framework) is used.
 *
 * Application using this class acts as Onem2m Application Entity (AE) and
 * must have specified it's AE-ID (used as name) and password which are
 * used as client credentials when Oauth2 access token is requested.
 *
 * Requests for access token are being sent to the IoTDM's "/access_token"
 * endpoint. Basic HTTP authentication method is used.
 */
public class HttpOauth2 extends Http {

    public final String aeId;
    public final String aePassword;

    private String accessToken = null;

    public HttpOauth2(@Nonnull final String aeId,
                      @Nonnull final String aePassword) {
        this.aeId = aeId;
        this.aePassword = aePassword;
    }

    private Response sendHttpRequest(org.eclipse.jetty.client.api.Request httpRequest) {
        ContentResponse contentResponse;
        try {
            contentResponse = httpRequest.send();
        } catch (Exception e) {
            System.err.println("Exception: " + e);
            throw new AssertionError(e.getMessage());
        }

        Response response = new ResponseBuilder(contentResponse).build();
        return response;
    }

    @Override
    public Response send(Request request) {
        Response rsp = null;
        org.eclipse.jetty.client.api.Request httpRequest = new HttpRequestBuilder(request).build();

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

            // build URL of the access_token endpoint at IoTDM
            StringBuilder builder = new StringBuilder()
                    .append("http://")
                    .append(request.getHost());

            int port = request.getPort();
            if (port > 0) {
                builder.append(":").append(port);
            }
            builder.append("/access_token");

            this.accessToken = getNewAccessToken(builder.toString());
            if (null != this.accessToken) {
                // we have new access token, let's try the same request again
                httpRequest = new HttpRequestBuilder(request).build();
                httpRequest.param("access_token", this.accessToken);
                rsp = sendHttpRequest(httpRequest);
            }
        }

        return rsp;
    }

    private String getNewAccessToken(@Nonnull final String authServerURL) {
        /*
         * Use client credentials in HTTP basic authentication.
         * Request Oauth2 access token with client_credentials grant type.
         */
        try {
            TokenResponse response =
                    new ClientCredentialsTokenRequest(new NetHttpTransport(), new JacksonFactory(),
                                                      new GenericUrl(authServerURL))
                            .setClientAuthentication(
                                    new BasicAuthentication(this.aeId, this.aePassword)).execute();
            System.out.println("Debug: Access token: " + response.getAccessToken());
            return response.getAccessToken();
        } catch (TokenResponseException e) {
            System.err.println("Token request resulted with exception: " + e);
        } catch (IOException e) {
            System.err.println("Access token request failed: " + e);
        } catch (Exception e) {
            System.err.println("Unknown error: " + e);
        }

        return null;
    }
}
