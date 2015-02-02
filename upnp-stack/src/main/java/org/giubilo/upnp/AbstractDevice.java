package org.allegria.upnp;

import java.util.HashSet;
import java.util.Set;

import org.allegria.upnp.net.NetService;


/*
 * TODO - QUESTIONS
 * - singleton for the RootDevice class?????
 * - synchronising objects vs making them threadsafe
 * - IOC / DIP / {abstract/interface}
 * - walkthrough netty ch5
 * - what is [ChannelPipeline offers an advanced form of the interception filter pattern]
 * - JDBC only offers blocking IO?
 * - avoiding race condition
 * - how to you performance tune the application {monitor|identify|report|remediate}
 * - SOLID / 10 design principles
 * - composition over inheritance
 * - interface not implementation
 * 
 */

public abstract class AbstractDevice implements Device {

	protected String deviceType;
	protected String friendlyName;
	protected String manufacturer;
	protected String manufacturerURL;
	protected String modelDescription;
	protected String modelName;
	protected String modelNumber;
	protected String modelURL;
	protected String serialNumber;
	protected String UDN;
	protected String UPC;
	protected Set<Icon> iconList = new HashSet<Icon>();	
	protected Set<Service> upnpServiceList = new HashSet<Service>();
	protected String presentationURL;
	
	public AbstractDevice(Set<Service> upnpServiceList) {
		
		this.upnpServiceList = upnpServiceList;
		
	}		
	
	@Override
	public void startDevice() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopDevice() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
