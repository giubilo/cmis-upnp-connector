package org.allegria.upnp.net.description;

import java.net.InetSocketAddress;

import org.allegria.upnp.net.NetService;
import org.allegria.upnp.net.description.Description;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

public class DescriptionService implements NetService {

	/*
	 * Description documents MUST be sent using the same IP address on which 
	 * the HTTP GET request was received
	 * 
	 * MAKE SURE SHARE THE EVENTLOOP
	 * 
	 */

	private final ChannelGroup channelGroup =
	        new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
	private final EventLoopGroup group = new NioEventLoopGroup();
	private Channel channel;		
	
	private DeviceDescription descDevice;
	private InetSocketAddress address;
	
    public DescriptionService(InetSocketAddress address, DeviceDescription descDevice) {
    	this.descDevice = descDevice;
    	this.address = address;
    	
    }
    
	@Override
	public void NetStart() throws Exception {
	//public ChannelFuture NetStart() throws Exception {
        
    	System.out.println("Bootstrapping the description server...");

            ServerBootstrap bootstrap  = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(createInitializer(channelGroup));
            ChannelFuture future = bootstrap.bind(address);
            future.syncUninterruptibly();
            channel = future.channel();
            //return future;
	}

	protected ChannelInitializer<Channel> createInitializer(ChannelGroup channelGroup) {
        return new DescriptionServiceInitializer(channelGroup, descDevice);
    }
      
	@Override
	public void NetStop() throws Exception {

   	System.out.println("Shutting Description server down...");
    	
    	if (channel != null) {
            channel.close();
        }
    	channelGroup.close();
        group.shutdownGracefully();		
	}

	
}
