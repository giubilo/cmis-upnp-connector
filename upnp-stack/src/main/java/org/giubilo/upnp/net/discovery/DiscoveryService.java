package org.allegria.upnp.net.discovery;

/* 
 * TODO put some awareness in here for whether IPv4 or IPv6
 * interfaces / groups / addresses used here
 * io.netty.channel.socket.InternetProtocolFamily;
 * io.netty.util.NetUtil;
 * that will allow for removing the JVM argument -Djava.net.preferIPv4Stack=true
 * test with  nc -u 239.255.255.250 1900
 * test with  ./socat -v PIPE udp-recvfrom:1900,ip-add-membership=239.255.255.250:10.1.1.16,fork
 */

import static org.allegria.upnp.net.ssdp.SsdpHeaders.Values.SSDP_ALIVE;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;

import org.allegria.upnp.Device;
import org.allegria.upnp.net.NetService;
import org.allegria.upnp.net.description.Description;
import org.allegria.upnp.net.ssdp.SsdpNotification;
import org.allegria.upnp.net.ssdp.SsdpRequestDecoder;
import org.allegria.upnp.net.ssdp.SsdpRequestHandler;
import org.allegria.upnp.net.ssdp.SsdpResponseEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.ImmediateEventExecutor;

public class DiscoveryService implements NetService {

	
	private EmbeddedChannel m2sBridge = new EmbeddedChannel(); 
	
	private final ChannelGroup channelGroup =
	        new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup group = new NioEventLoopGroup();
	private NioDatagramChannel channel;

	private final ScheduledExecutorService scheduledExecutorService =  Executors.newSingleThreadScheduledExecutor();
		
	
	private String iNet;
	private int port;
	private InetSocketAddress multiCastInet;
	private Description descDevice;
	
	private int bootID;
   
    public DiscoveryService(String iNet, int port, InetSocketAddress multiCastInet, Description descDevice) {
    	this.iNet = iNet;
    	this.port = port;
    	this.multiCastInet = multiCastInet;
    	this.descDevice = descDevice;
    	
    	/*
    	 * set the discovery message device header parameters for BOOTID.UPNP.ORG, CONFIGID.UPNP.ORG
    	 * and SEARCHPORT.UPNP.ORG
    	 * TODO allow SEARCHPORT.UPNP.ORG to be set either by
    	 *  - config file
    	 *  - auto set by retrieving a viable port if 1900 not viable
    	 */
    	
    	this.bootID = (int) Math.abs(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis()/1000);
    	
    }
    
	@Override
    public void NetStart() throws Exception {
        
    	System.out.println("Bootstrapping the discovery server...");
    	Bootstrap bootstrap = new Bootstrap();
    	bootstrap.group(group)
         
    	 /* TODO work out why this has no effect on the channel statement further down
          * i.e need to include (NioDatagramChannel) again and maybe use ChannelFactory
          * and why do have to have .localAddress here as well if have ChannelOption IF 
          */
    	
    	 /* TODO work out utility to allow adding to more interfaces and also to check
    	  * when interfaces are added or removed 
    	  */
    	
    	 .channel(NioDatagramChannel.class)
    	 .option(ChannelOption.SO_REUSEADDR, true)
    	 .option(ChannelOption.IP_MULTICAST_TTL, 2)
    	 .option(ChannelOption.IP_MULTICAST_LOOP_DISABLED, true)
         .localAddress(this.port)
         .handler(createInitializer(channelGroup));
        

    	//set the channel.
    	channel = (NioDatagramChannel) bootstrap.bind().sync().channel();
        channel.joinGroup(this.multiCastInet, NetworkInterface.getByName("lo0")).syncUninterruptibly();
        channel.joinGroup(this.multiCastInet, NetworkInterface.getByName(this.iNet)).syncUninterruptibly();
        
        //set the bridge
        m2sBridge.pipeline().addFirst(new LoggingHandler(LogLevel.INFO));
        //m2sBridge.pipeline().addLast("decoder", new SsdpRequestDecoder());
        //m2sBridge.pipeline().addLast("encoder", new SsdpResponseEncoder());
        //m2sBridge.pipeline().addLast(new HttpObjectAggregator(64 * 1024));
        //m2sBridge.pipeline().addLast(new ChunkedWriteHandler());
        m2sBridge.pipeline().addLast(new SsdpRequestHandler(descDevice)); 
        m2sBridge.pipeline().addLast("decoder", new HttpRequestDecoder());
        //m2sBridge.pipeline().addLast("encoder", new HttpResponseEncoder());
    }

	protected ChannelInitializer<Channel> createInitializer(ChannelGroup channelGroup) {
        return new DiscoveryServiceInitializer(channelGroup, this.descDevice, this.m2sBridge);
     }
      
	@Override
	public void NetStop() throws Exception {

   	System.out.println("Shutting SSDP server down...");
    	
    	if (channel != null) {
            channel.close();
        }
    	this.StopAdvertising();
    	channelGroup.close();
        group.shutdownGracefully();		
	}

	public void StartAdvertising() {
		
		/*
		 * TODO need to create the group of SSDP notifications based on the
		 * 3+2d+k rule in the spec
		 */
		
		
		final SsdpNotification aliveMsg = 
				new SsdpNotification(SSDP_ALIVE, this.descDevice);
		
		/*
		 * TODO add a try / catch to the runnable as “If any execution of the task encounters an exception, 
		 * subsequent executions are suppressed.” - i.e the task will stop and you won't know about it
		 * 
		 */
		Runnable advertisement = new Runnable() {

			@Override
			public void run() {

				System.out.println(aliveMsg.toString());
				channel.writeAndFlush(aliveMsg);
				
			}			
		};
		
		/*
		 * TODO add a variable delay to the schedule "Spreading refreshments of advertisements over time 
		 * rather than being sent as a group improves reliability in case there are network glitches" 
		 * 
		 * java concurrency in practice - Brian Goetz  
		 */
		scheduledExecutorService.scheduleWithFixedDelay(advertisement, 0, 10, TimeUnit.SECONDS);
		
	}
	
	private void StopAdvertising() {
        scheduledExecutorService.shutdownNow();
		
	}
	
	
}
