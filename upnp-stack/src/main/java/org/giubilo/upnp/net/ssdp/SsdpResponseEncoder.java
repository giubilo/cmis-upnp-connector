package org.allegria.upnp.net.ssdp;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpConstants.*;

/**
 * Encodes an SSDP response represented in {@link FullHttpResponse} into
 * a {@link ByteBuf}.
 */
public class SsdpResponseEncoder extends SsdpObjectEncoder<HttpResponse> {
    private static final byte[] CRLF = { CR, LF };

    @Override
    public boolean acceptOutboundMessage(Object msg) throws Exception {
        return msg instanceof FullHttpResponse;
    }

    @Override
    @SuppressWarnings("deprecation")
    protected void encodeInitialLine(ByteBuf buf, HttpResponse response) throws Exception {
        HttpHeaders.encodeAscii(response.getProtocolVersion().toString(), buf);
        buf.writeByte(SP);
        buf.writeBytes(String.valueOf(response.getStatus().code()).getBytes(CharsetUtil.US_ASCII));
        buf.writeByte(SP);
        HttpHeaders.encodeAscii(String.valueOf(response.getStatus().reasonPhrase()), buf);
        buf.writeBytes(CRLF);
    }
}
