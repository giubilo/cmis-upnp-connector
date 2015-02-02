package org.allegria.upnp.net.ssdp;

import io.netty.handler.codec.http.HttpHeaders;


/**
 * Standard SSDP header names and values.
 */
public final class SsdpHeaders {

    /**
     * Standard SSDP header names.
     */
    public static final class Names {
 
        /**
         * {@code "Host"}
         */
        public static final CharSequence HOST = HttpHeaders.Names.HOST;    	
        /**
         * {@code "Cache-Control"}
         */
        public static final CharSequence CACHE_CONTROL = HttpHeaders.Names.CACHE_CONTROL;
        /**
         * {@code "Location"}
         */
        public static final CharSequence LOCATION = HttpHeaders.newEntity("Location");
        /**
         * {@code "NT"}
         */
        public static final CharSequence NT = HttpHeaders.newEntity("NT");
        /**
         * {@code "NTS"}
         */
        public static final CharSequence NTS = HttpHeaders.newEntity("NTS");
        /**
         * {@code "Server"}
         */
        public static final CharSequence SERVER = HttpHeaders.Names.SERVER;
        /**
         * {@code "USN"}
         */
        public static final CharSequence USN = HttpHeaders.newEntity("USN");
        /**
         * {@code "BOOTID.UPNP.ORG"}
         */
        public static final CharSequence BOOTID_UPNP_ORG = HttpHeaders.newEntity("BOOTID.UPNP.ORG");
        /**
         * {@code "NEXTBOOTID.UPNP.ORG"}
         */
        public static final CharSequence NEXTBOOTID_UPNP_ORG = HttpHeaders.newEntity("NEXTBOOTID.UPNP.ORG");

        /**
         * {@code "CONFIGID.UPNP.ORG"}
         */
        public static final CharSequence CONFIGID_UPNP_ORG = HttpHeaders.newEntity("CONFIGID.UPNP.ORG");
        /**
         * {@code "SEARCHPORT.UPNP.ORG"}
         */
        public static final CharSequence SEARCHPORT_UPNP_ORG = HttpHeaders.newEntity("SEARCHPORT.UPNP.ORG");
        /**
         * {@code "MAN"}
         */
        public static final CharSequence MAN = HttpHeaders.newEntity("MAN");
        /**
         * {@code "MX"}
         */
        public static final CharSequence MX = HttpHeaders.newEntity("MX");
        /**
         * {@code "ST"}
         */
        public static final CharSequence ST = HttpHeaders.newEntity("ST");
        /**
         * {@code "User-Agent"}
         */
        public static final CharSequence USER_AGENT = HttpHeaders.Names.USER_AGENT;
        /**
         * {@code "EXT"}
         */
        public static final CharSequence EXT = HttpHeaders.newEntity("EXT");
        /**
         * {@code "Content-Length"}
         */
        public static final CharSequence CONTENT_LENGTH = HttpHeaders.Names.CONTENT_LENGTH;

        private Names() {
        }
    }

    /**
     * Standard SSDP header values.
     */
    public static final class Values {

        /**
         * {@code "max-age"}
         */
        public static final CharSequence MAX_AGE = HttpHeaders.Values.MAX_AGE;
    	/**
         * {@code "ssdp:alive"}
         */
        public static final CharSequence SSDP_ALIVE = HttpHeaders.newEntity("ssdp:alive");
    	/**
         * {@code "ssdp:byebye"}
         */
        public static final CharSequence SSDP_BYEBYE = HttpHeaders.newEntity("ssdp:byebye");
    	/**
         * {@code "ssdp:update"}
         */
        public static final CharSequence SSDP_UPDATE = HttpHeaders.newEntity("ssdp:update");
    	/**
         * {@code "ssdp:discover"}
         */
        public static final CharSequence SSDP_DISCOVER = HttpHeaders.newEntity("ssdp:discover");

        private Values() { }
    }

    private SsdpHeaders() { }
}
