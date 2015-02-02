package org.allegria.upnp.net.ssdp;

import io.netty.handler.codec.http.HttpVersion;

/**
 * The version of SSDP.
 */
public final class SsdpVersions {

    /**
     * HTTP/1.1
     */
    public static final HttpVersion SSDP_1_1 = new HttpVersion("HTTP", 1, 1, true);

    /**
     * Returns an existing or new {@link HttpVersion} instance which matches to
     * the specified SSDP version string.  If the specified {@code text} is
     * equal to {@code "HTTP/1.1"}, {@link #HTTP_1_1} will be returned.
     * Otherwise, a new {@link HttpVersion} instance will be returned.
     */
    public static HttpVersion valueOf(String text) {
        if (text == null) {
            throw new NullPointerException("text");
        }

        text = text.trim().toUpperCase();
        if ("HTTP/1.1".equals(text)) {
            return SSDP_1_1;
        }

        return new HttpVersion(text, true);
    }

    private SsdpVersions() {
    }
}
