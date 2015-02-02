package org.allegria.cuppa;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import org.allegria.upnp.RootDevice;
import org.allegria.upnp.av.mediaserver.MediaServer;
import org.allegria.upnp.Service;
import org.allegria.upnp.net.NetService;
import org.allegria.upnp.net.description.Description;
import org.allegria.upnp.net.description.DescriptionService;
import org.allegria.upnp.net.description.DeviceDescription;
import org.allegria.upnp.net.discovery.DiscoveryService;
import org.allegria.upnp.net.addressing.HostDescription;
import org.allegria.upnp.net.description.ServiceDescription;

public class Console
{

	private MediaServer mediaServer;	
	private Set<Service> serviceList = new HashSet<Service>();
	private Set<NetService> netServiceList = new HashSet<NetService>();
	private DiscoveryService discoveryService;
	private DescriptionService descriptionService;
	private HostDescription descHost;
	private DeviceDescription descMediaServer;
	private Description descContentDirectory;
	private Description descConnectionManager;
	
	public Console(String iNet, int port, String multiCastGroup) throws Exception {
				
	    //setup a shutdown hook to make sure connections cleaned on unexpected exit
		Runtime.getRuntime().addShutdownHook(new Thread() {
	            @Override
	            public void run() {
	            	mediaServer.destroy();
	            }
	        });
	      
		/*
		 * TODO - need to set up some counters and timers
		 */
		
		
		//get the host details
		descHost = new HostDescription();
		
		//initialize the description files and adjust values for user config
		descMediaServer = new DeviceDescription("MediaServer4.xml");
	
		//descContentDirectory = new ServiceDescription("ContentDirectory4.xml");
		//descConnectionManager = new ServiceDescription("ConnectionManager3.xml");
				
		//initialize the networking services for injection
		discoveryService = new DiscoveryService(iNet, port, new InetSocketAddress(multiCastGroup, port), descMediaServer);
		discoveryService.NetStart();
		//descriptionService = new DescriptionService(new InetSocketAddress(8080), descMediaServer);
		//descriptionService.NetStart();
		
		
		netServiceList.add(discoveryService);
		//netServiceList.add(descriptionService);
		
		//initialize any root and embedded services for injection (including ServiceStateTable(s))
		
		
		
		//initialize root and embedded devices
		
		/*
		 * TODO - errors / logging
		 * TODO - junit coverage / fragility
		 * TODO - performance measurement / monitoring
		 */
		
		mediaServer = new MediaServer(new RootDevice(serviceList, netServiceList, descMediaServer));
		mediaServer.startDevice();

		//discoveryService.StartAdvertising();
		
	}
	
	
	public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println(
                    "Usage: EchoServer <interface name> <port> <multicast group>");
            System.exit(1);;
        }

        String iNet = args[0];
        int port = Integer.parseInt(args[1]);
        String multiCastGroup = args[2];
        
        new Console(iNet, port, multiCastGroup);
                
    }	
	
	
}
