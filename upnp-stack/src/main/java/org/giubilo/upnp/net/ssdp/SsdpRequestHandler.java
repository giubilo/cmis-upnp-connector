package org.allegria.upnp.net.ssdp;

import org.allegria.upnp.net.description.Description;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;

public class SsdpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	private final Description descDevice;
	
	public SsdpRequestHandler(Description descDevice) {
        this.descDevice = descDevice;
                
	}

	
	@Override
    public void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        
    	if (HttpHeaders.is100ContinueExpected(request)) {
                send100Continue(ctx);
    	}
    	
    	// write response
    	ctx.write(createResponse(request));
    	
    	// write content
    	
    	//write last content
    	ChannelFuture future = ctx.write(LastHttpContent.EMPTY_LAST_CONTENT);
    	
    	if (!HttpHeaders.isKeepAlive(request)) {
    		future.addListener(ChannelFutureListener.CLOSE);
    	
    	}
    
    }
    
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}
    	
	
	private HttpResponse createResponse(FullHttpRequest request) {

		HttpResponse response = new DefaultHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=UTF-8");
        
        if (request.getMethod() == SsdpMethods.M_SEARCH) {
        	System.out.println("Hello Isabella");
        }
        
        if (HttpHeaders.isKeepAlive(request)) {
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }
		return response;
        
	}

    private static void send100Continue(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
