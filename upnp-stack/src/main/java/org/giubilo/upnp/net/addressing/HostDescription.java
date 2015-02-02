package org.allegria.upnp.net.addressing;

import org.apache.commons.configuration.SystemConfiguration;

/*
 * InetAddress.getLocalHost().getHostAddress
 * 
 */



public class HostDescription {

	protected SystemConfiguration config;
	
	public HostDescription() {
		
		config = new SystemConfiguration();
		
	}
	
	public String GetValue(String nodeKey) {
		
		return config.getString(nodeKey);
	}

	
}
