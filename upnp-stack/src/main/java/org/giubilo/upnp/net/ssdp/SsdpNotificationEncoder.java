package org.allegria.upnp.net.ssdp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.http.DefaultHttpRequest;

import java.net.InetSocketAddress;

public class SsdpNotificationEncoder extends MessageToByteEncoder<DefaultHttpRequest> {
    
	private final InetSocketAddress remoteAddress;

    public SsdpNotificationEncoder(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, DefaultHttpRequest ssdpNotification, ByteBuf out) throws Exception {
        new DatagramPacket(out, remoteAddress);
    }
}
