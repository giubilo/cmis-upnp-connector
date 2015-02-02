package org.allegria.upnp.net.ssdp;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;

/**
 * Decode UDP SSDP packets by extracting the
 * content of the datagram and passing it to the decoder.
 */
public class UdpSsdpDecoder extends MessageToMessageDecoder<DatagramPacket>{
	
	private EmbeddedChannel m2sBridge;
	
	public UdpSsdpDecoder(EmbeddedChannel m2sBridge) {
		this.m2sBridge = m2sBridge;
		
	}
	

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {

        ByteBuf buf = msg.content();
        
        m2sBridge.writeInbound(buf);

    }
}