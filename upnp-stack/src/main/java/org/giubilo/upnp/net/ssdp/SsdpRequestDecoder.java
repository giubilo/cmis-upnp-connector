package org.allegria.upnp.net.ssdp;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpRequest;

/**
 * Decodes {@link ByteBuf}s into SSDP requests represented in
 * {@link HttpRequest}s.
 * <p>
 * <h3>Parameters that prevents excessive memory consumption</h3>
 * <table border="1">
 * <tr>
 * <th>Name</th><th>Meaning</th>
 * </tr>
 * <tr>
 * <td>{@code maxInitialLineLength}</td>
 * <td>The maximum length of the initial line (e.g. {@code "NOTIFY * HTTP/1.1"})
 *     If the length of the initial line exceeds this value, a
 *     {@link TooLongFrameException} will be raised.</td>
 * </tr>
 * <tr>
 * <td>{@code maxHeaderSize}</td>
 * <td>The maximum length of all headers.  If the sum of the length of each
 *     header exceeds this value, a {@link TooLongFrameException} will be raised.</td>
 * </tr>
 * <tr>
 * <td>{@code maxContentLength}</td>
 * <td>The maximum length of the content.  If the content length exceeds this
 *     value, a {@link TooLongFrameException} will be raised.</td>
 * </tr>
 * </table>
 */
public class SsdpRequestDecoder extends SsdpObjectDecoder {

    /**
     * Creates a new instance with the default
     * {@code maxInitialLineLength (4096}}, {@code maxHeaderSize (8192)}, and
     * {@code maxContentLength (8192)}.
     */
    public SsdpRequestDecoder() {
    }

    /**
     * Creates a new instance with the specified parameters.
     */
    public SsdpRequestDecoder(int maxInitialLineLength, int maxHeaderSize, int maxContentLength) {
        super(maxInitialLineLength, maxHeaderSize, maxContentLength);
    }

    public SsdpRequestDecoder(
            int maxInitialLineLength, int maxHeaderSize, int maxContentLength, boolean validateHeaders) {
        super(maxInitialLineLength, maxHeaderSize, maxContentLength, validateHeaders);
    }

    @Override
    protected HttpMessage createMessage(String[] initialLine) throws Exception {
        return new DefaultHttpRequest(SsdpVersions.valueOf(initialLine[2]),
                SsdpMethods.valueOf(initialLine[0]), initialLine[1], validateHeaders);
    }

    @Override
    protected HttpMessage createInvalidMessage() {
        return new DefaultHttpRequest(SsdpVersions.SSDP_1_1, SsdpMethods.OPTIONS, "/bad-request", validateHeaders);
    }

    @Override
    protected boolean isDecodingRequest() {
        return true;
    }
}
