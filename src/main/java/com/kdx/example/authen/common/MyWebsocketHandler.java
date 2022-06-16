package com.kdx.example.authen.common;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc --TextWebSocketFrame:netty用于处理websocekt发来的文本对象
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/3/20
 */
public class MyWebsocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final Logger log = LoggerFactory.getLogger(MyWebsocketHandler.class);

    /**
     * 存放所有正在连接的channel,也可间接代表在线的客户端
     */
    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 在线人数
     */
    private int online;

    /**
     * 接收到客户端发送的消息
     * @param cc
     * @param twf
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext cc, TextWebSocketFrame twf) throws Exception {
        log.info("接收到客户端消息：{}",twf.text());
        sendMsg(cc,twf.text());
    }

    /**
     * 客户端建立连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelGroup.add(ctx.channel());
        online = channelGroup.size();
        log.info("客户端：[{}]建立连接;当前在线用户数量：{}",ctx.channel().remoteAddress(),online);
    }

    /**
     * 关闭连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelGroup.remove(ctx.channel());
        online = channelGroup.size();
        log.info("客户端：[{}]关闭连接;当前在线用户数量：{}",ctx.channel().remoteAddress(),online);
    }

    /**
     * 出现异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    public static void sendMsg(ChannelHandlerContext ctx,String msg){
        Channel channel = ctx.channel();
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }
}
