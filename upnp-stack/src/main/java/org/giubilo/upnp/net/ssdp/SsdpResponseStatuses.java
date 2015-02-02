package org.allegria.upnp.net.ssdp;

import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * The getStatus code and its description of a SSDP response.
 */
public final class SsdpResponseStatuses {

    /**
     * 200 OK
     */
    public static final HttpResponseStatus OK = HttpResponseStatus.OK;

    /**
     * Returns the {@link HttpResponseStatus} represented by the specified code.
     * If the specified code is a standard SSDP getStatus code, a cached instance
     * will be returned.  Otherwise, a new instance will be returned.
     */
    public static HttpResponseStatus valueOf(int code) {
        switch (code) {
        default:  return HttpResponseStatus.valueOf(code);
        }
    }

    private SsdpResponseStatuses() {
    }
}
