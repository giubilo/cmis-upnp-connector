package org.allegria.upnp.net.ssdp;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpConstants.*;

/**
 * Encodes an SSDP request represented in {@link FullHttpRequest} into
 * a {@link ByteBuf}.

 */
public class SsdpRequestEncoder extends SsdpObjectEncoder<HttpRequest> {
    private static final byte[] CRLF = { CR, LF };

    @Override
    public boolean acceptOutboundMessage(Object msg) throws Exception {
        return msg instanceof FullHttpRequest;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void encodeInitialLine(ByteBuf buf, HttpRequest request) throws Exception {
        HttpHeaders.encodeAscii(request.getMethod().toString(), buf);
        buf.writeByte(SP);
        buf.writeBytes(request.getUri().getBytes(CharsetUtil.UTF_8));
        buf.writeByte(SP);
        HttpHeaders.encodeAscii(request.getProtocolVersion().toString(), buf);
        buf.writeBytes(CRLF);
    }
}
