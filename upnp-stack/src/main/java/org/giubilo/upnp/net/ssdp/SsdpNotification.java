package org.allegria.upnp.net.ssdp;

import org.allegria.upnp.net.description.Description;

import io.netty.handler.codec.http.DefaultHttpRequest;
import static io.netty.handler.codec.http.HttpVersion.*;
import static org.allegria.upnp.net.ssdp.SsdpHeaders.Names.*;
import static org.allegria.upnp.net.ssdp.SsdpHeaders.Values.*;
import static org.allegria.upnp.net.ssdp.SsdpMethods.*;

public class SsdpNotification extends DefaultHttpRequest {
    
	
		// "//@configId" xpath for getting ConfigId from description file
		// System.out.println(descMediaServer.GetValue("//@configId"));
	
		// xpath for getting host description info
		// System.out.println(descHost.GetValue("os.name") + "/" + descHost.GetValue("os.version"));		


	
	public SsdpNotification(CharSequence nts, Description descDevice) {
    	
		super(HTTP_1_1, NOTIFY, "*");
		
		this.headers().add(HOST, "127.0.0.1");
		this.headers().add(CACHE_CONTROL, MAX_AGE + "=600");
		this.headers().add(LOCATION, "/description.xml");
		this.headers().add(NT, "upnp:rootdevice");
		this.headers().add(NTS, nts.toString());
		this.headers().add(SERVER, "Mac OSX Mountain Lion");
		this.headers().add(USN, "uuid:" + "f46cd690-ffd4-11e3-9191-0800200c9a66" + "::upnp:rootdevice");
		this.headers().add(BOOTID_UPNP_ORG, 1000);
		this.headers().add(CONFIGID_UPNP_ORG, 2000);
	}

}