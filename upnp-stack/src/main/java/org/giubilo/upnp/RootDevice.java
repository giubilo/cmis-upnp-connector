package org.allegria.upnp;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import org.allegria.upnp.net.NetService;
import org.allegria.upnp.net.description.Description;

@XmlRootElement
public class RootDevice extends AbstractDevice implements Device{

	protected Set<Device> embeddedDeviceList;
	protected Set<NetService> netServiceList = new HashSet<NetService>();
	protected Description deviceDescription;

	public RootDevice(Set<Service> upnpServiceList, Set<NetService> netServiceList, Description deviceDescription) {
		
		super(upnpServiceList);
		
		this.netServiceList = netServiceList;
		this.deviceDescription = deviceDescription;
		
	}	

	
	public void AddEmbeddedDevice(EmbeddedDevice embeddedDevice) {
		
		embeddedDeviceList.add(embeddedDevice);
		
	}

	
	public void Announce() {
		
		// TODO add timer to spread announcement messages
		// TODO add the consolidation of service type spec requirement s1.2.2 3+2d+k requests 
		
		/*
		 * Before announcement is made the following must be in place
		 * - root device and all embedded devices started
		 * - root services and embedded device services started
		 * - root uuid and embedded device uuid set
		 * - BOOTID.UPNP.ORG calculated
		 * - CONFIGID.UPNP.ORG determined
		 * - optionally SEARCHPORT.UPNP.ORG determined
		 */
		
		
		//for Service 
		
	}

	
}
