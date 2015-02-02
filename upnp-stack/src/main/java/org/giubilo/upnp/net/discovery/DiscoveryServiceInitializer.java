package org.allegria.upnp.net.discovery;

import org.allegria.upnp.net.description.Description;
import org.allegria.upnp.net.ssdp.SsdpRequestDecoder;
import org.allegria.upnp.net.ssdp.SsdpRequestHandler;
import org.allegria.upnp.net.ssdp.SsdpResponseEncoder;
import org.allegria.upnp.net.ssdp.UdpSsdpDecoder;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class DiscoveryServiceInitializer extends ChannelInitializer<Channel> {
    private final ChannelGroup channelGroup;
	private Description descDevice;
	private EmbeddedChannel m2sBridge; 

    public DiscoveryServiceInitializer(ChannelGroup channelGroup, 
    							Description descDevice,
    							EmbeddedChannel m2sBridge) {
        
    	this.descDevice = descDevice;
    	this.channelGroup = channelGroup;
    	this.m2sBridge = m2sBridge;
    	
    }   

	@Override
    protected void initChannel(Channel ch) throws Exception {
    	ChannelPipeline pipeline = ch.pipeline();
    	pipeline.addFirst(new LoggingHandler(LogLevel.INFO));
    	pipeline.addLast("m2sBridge", new UdpSsdpDecoder(m2sBridge));
    	//pipeline.addLast("udpdecoder", new UdpSsdpDecoder());
    	//pipeline.addLast("decoder", new SsdpRequestDecoder());
    	//pipeline.addLast("encoder", new SsdpResponseEncoder());
        //pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        //pipeline.addLast(new ChunkedWriteHandler());
        //pipeline.addLast(new SsdpRequestHandler(descDevice)); 
    }
}
