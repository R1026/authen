package com.kdx.example.authen.web;

import com.kdx.example.authen.common.MyHttpHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/11/3
 */
@Component
public class NettyServer {

    private final Logger log = LoggerFactory.getLogger(NettyServer.class);

    /**
     * 初始化netty服务
     */
    @PostConstruct
    public void initNettyServer() throws Exception{
        //netty服务启动类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //主线程组
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        //工作线程组
        EventLoopGroup workGroup = new NioEventLoopGroup();

        serverBootstrap.group(mainGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        //System.out.println(sc.remoteAddress() + "hhhhhheeeeeee");
                        log.info("连接成功，remote_address:[{}]",sc.remoteAddress() );
                        sc.pipeline()
                                .addLast("decoder",new HttpRequestDecoder())
                                .addLast("encoder",new HttpResponseDecoder())
                                .addLast("handler",new MyHttpHandler());

                    }
                });
        serverBootstrap.bind(9000).sync();
        //System.out.println("初始化netty server ----");
        log.info("init netty server success...");
    }

}
