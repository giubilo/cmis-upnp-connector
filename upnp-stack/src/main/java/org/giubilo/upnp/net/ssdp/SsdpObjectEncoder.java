package org.allegria.upnp.net.ssdp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObjectEncoder;

/**
 * Encodes an SSDP message represented in {@link FullHttpMessage} into
 * a {@link ByteBuf}.
 */
@Sharable
public abstract class SsdpObjectEncoder<H extends HttpMessage> extends HttpObjectEncoder<H> {

    /**
     * Creates a new instance.
     */
    protected SsdpObjectEncoder() {
    }

    @Override
    public boolean acceptOutboundMessage(Object msg) throws Exception {
        return msg instanceof FullHttpMessage;
    }
}
