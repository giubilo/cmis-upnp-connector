package org.allegria.upnp.net.ssdp;

import io.netty.handler.codec.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * The request getMethod of SSDP.
 */
public final class SsdpMethods {

    /**
     * When a device is added to the network, it MUST send a multicast message with method NOTIFY.
     */
    public static final HttpMethod NOTIFY = new HttpMethod("NOTIFY");

    /**
     * When a control point is added to the network, the UPnP discovery protocol (SSDP) allows that
     * control point to search for devices of interest on the network. It does this by multicasting
     * on the reserved address and port (239.255.255.250:1900) a search message, M-SEARCH, with a 
     * pattern, or target, equal to a type or identifier for a device or service.
     */
    public static final HttpMethod M_SEARCH = new HttpMethod("M-SEARCH");

    /**
     * The OPTIONS getMethod represents a request for information about the communication options
     * available on the request/response chain identified by the Request-URI. This getMethod allows
     * the client to determine the options and/or requirements associated with a resource, or the
     * capabilities of a server, without implying a resource action or initiating a resource
     * retrieval.
     */
    public static final HttpMethod OPTIONS = HttpMethod.OPTIONS;

    
    private static final Map<String, HttpMethod> methodMap = new HashMap<String, HttpMethod>();

    static {
        methodMap.put(NOTIFY.toString(), NOTIFY);
        methodMap.put(M_SEARCH.toString(), M_SEARCH);
        methodMap.put(OPTIONS.toString(), OPTIONS);

    }

    /**
     * Returns the {@link HttpMethod} represented by the specified name.
     * If the specified name is a standard SSDP getMethod name, a cached instance
     * will be returned.  Otherwise, a new instance will be returned.
     */
    public static HttpMethod valueOf(String name) {
        if (name == null) {
            throw new NullPointerException("name");
        }

        name = name.trim().toUpperCase();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }

        HttpMethod result = methodMap.get(name);
        if (result != null) {
            return result;
        } else {
            return new HttpMethod(name);
        }
    }

    private SsdpMethods() {
    }
}
