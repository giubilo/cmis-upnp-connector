package org.allegria.upnp;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmbeddedDevice extends AbstractDevice {

	public EmbeddedDevice(Set<Service> upnpServiceList) {
		
		super(upnpServiceList);
		// TODO Auto-generated constructor stub
	}

	
	
}
