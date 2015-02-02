package org.allegria.upnp.net.discovery;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import static org.allegria.upnp.net.ssdp.SsdpHeaders.Values.*;

public class DiscoveryServiceCmdHandler extends
					ChannelInboundHandlerAdapter {

    public static final String SHUTDOWN = "shutdown";
    
    public DiscoveryServiceCmdHandler(ChannelGroup group) {
        
    }
    
    /**
     * Class that logs any User Events triggered on this channel.
     */
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("User Event Triggered: " + evt);
        
        if (evt.equals(SHUTDOWN)) {
            //shut everything down
            System.exit(0);

        } else if (evt.equals(SSDP_ALIVE)) {
    		        	
            System.out.println("Sending notification: ");
    		
    	}
        
    }
	
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
