package org.allegria.upnp.net.description;

//import org.allegria.upnp.net.http.HttpRequestHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class DescriptionServiceInitializer extends ChannelInitializer<Channel> {

   private final ChannelGroup group;
   private final DeviceDescription descDevice;

    public DescriptionServiceInitializer(ChannelGroup group, DeviceDescription descDevice) {
        this.group = group;
        this.descDevice = descDevice;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
    	pipeline.addFirst(new LoggingHandler(LogLevel.INFO));
    	pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new ChunkedWriteHandler());
        //pipeline.addLast(new HttpRequestHandler(descDevice));
    }
}
