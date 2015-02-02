package org.allegria.upnp.net.description;

import java.util.UUID;

public class DeviceDescription extends AbstractDescription {

	public DeviceDescription(String configFile) {
		super(configFile);
		
		//device needs new UUID so is unique
		this.SetValue("device/UDN", UUID.randomUUID().toString());
		
	}

}
