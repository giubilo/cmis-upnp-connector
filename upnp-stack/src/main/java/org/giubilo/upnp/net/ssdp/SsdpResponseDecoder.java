package org.allegria.upnp.net.ssdp;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * Decodes {@link ByteBuf}s into SSDP responses represented in
 * {@link HttpResponse}s.
 * <p>
 * <h3>Parameters that prevents excessive memory consumption</h3>
 * <table border="1">
 * <tr>
 * <th>Name</th><th>Meaning</th>
 * </tr>
 * <tr>
 * <td>{@code maxInitialLineLength}</td>
 * <td>The maximum length of the initial line (e.g. {@code "SSDP/1.1 200 OK"})
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
public class SsdpResponseDecoder extends SsdpObjectDecoder {

    private static final HttpResponseStatus UNKNOWN_STATUS = new HttpResponseStatus(999, "Unknown");

    /**
     * Creates a new instance with the default
     * {@code maxInitialLineLength (4096}}, {@code maxHeaderSize (8192)}, and
     * {@code maxContentLength (8192)}.
     */
    public SsdpResponseDecoder() {
    }

    /**
     * Creates a new instance with the specified parameters.
     */
    public SsdpResponseDecoder(int maxInitialLineLength, int maxHeaderSize,
            int maxContentLength) {
        super(maxInitialLineLength, maxHeaderSize, maxContentLength);
    }

    public SsdpResponseDecoder(int maxInitialLineLength, int maxHeaderSize,
                               int maxContentLength, boolean validateHeaders) {
        super(maxInitialLineLength, maxHeaderSize, maxContentLength, validateHeaders);
    }

    @Override
    protected HttpMessage createMessage(String[] initialLine) throws Exception {
        return new DefaultHttpResponse(
                SsdpVersions.valueOf(initialLine[0]),
                new HttpResponseStatus(Integer.valueOf(initialLine[1]), initialLine[2]), validateHeaders);
    }

    @Override
    protected HttpMessage createInvalidMessage() {
        return new DefaultHttpResponse(SsdpVersions.SSDP_1_1, UNKNOWN_STATUS, validateHeaders);
    }

    @Override
    protected boolean isDecodingRequest() {
        return false;
    }
}
