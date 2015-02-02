package org.allegria.upnp.net.discovery;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.CharsetUtil;
import static org.allegria.upnp.net.ssdp.SsdpHeaders.Values.*;


@Sharable
public class DiscoveryServiceHandler extends MessageToMessageDecoder<DatagramPacket> {

    public static final String SHUTDOWN = "shutdown";
	
    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket datagramPacket, List<Object> in) throws Exception {
    	ByteBuf data = datagramPacket.content();
        
    	/*
    	 * TODO check for a size here so don't try and bring in a huge string
    	 */
    	String msg = data.toString(CharsetUtil.UTF_8);

    	/*
    	 * TODO the handling here should probably follow this logic:
    	 * - check if msg is of instance httprequest
    	 * - see if the method is one of the upnp types like M-SEARCH
    	 * - if requestmethod is NOTIFY then check NT and NTS headers
    	 * - if requestmethod is M-SEARCH check the MAN headers
    	 * - if requestmethod is SUBSCRIBE check the NT header
    	 * 
    	 */
    	
    	//ctx.channel().eventLoop().shutdownGracefully();
    	
    	if (data instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) data;
            //if (req.getMethod().)
    	} else if (msg.equalsIgnoreCase(SHUTDOWN + "\n")) {
    		ctx.fireUserEventTriggered(SHUTDOWN);
    	} else if (msg.equalsIgnoreCase(SSDP_ALIVE + "\n")) {
    		ctx.fireUserEventTriggered(SSDP_ALIVE);    		
    	} else {
    		System.out.println("Server received: " + msg);
    	}
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}