package com.kdx.example.authen.common;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/11/3
 */
public class MyHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final Logger log = LoggerFactory.getLogger(MyHttpHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext chc, FullHttpRequest fhr) throws Exception {
        log.info(fhr.getClass().getName());
        Channel channel = chc.channel();
        //System.out.println("fhr" + fhr.getClass().getName());
        log.info(channel.remoteAddress().toString());
    }
}
