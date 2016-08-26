/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.iotdm.client.impl.oauth2;

import org.opendaylight.iotdm.client.Request;
import org.opendaylight.iotdm.client.Response;
import org.opendaylight.iotdm.client.impl.Https;

public class HttpsOauth2 extends Https {

    private final Oauth2Common<Https> oauth2Common;

    public HttpsOauth2(final String aeId, final String aePassword,
                       final String trustStore, final String storePassword) {
        super(trustStore, storePassword);
        this.oauth2Common = new Oauth2Common<>(aeId, aePassword, this, true);
    }

    @Override
    public Response send(Request request) {
        return this.oauth2Common.send(request);
    }
}
