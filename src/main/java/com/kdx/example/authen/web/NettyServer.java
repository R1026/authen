package com.kdx.example.authen.web;

import com.kdx.example.authen.common.MyHttpHandler;
import com.kdx.example.authen.common.MyWebsocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
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

    //netty服务启动类
    private ServerBootstrap serverBootstrap;
    //主线程组
    private EventLoopGroup mainGroup;
    //工作线程组
    private EventLoopGroup workGroup;

    /**
     * 初始化netty服务
     */
    //@PostConstruct
    public void initNettyServer() throws Exception{
        //netty服务启动类
        serverBootstrap = new ServerBootstrap();
        //主线程组
        mainGroup = new NioEventLoopGroup();
        //工作线程组
        workGroup = new NioEventLoopGroup();

        serverBootstrap.group(mainGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        //System.out.println(sc.remoteAddress() + "hhhhhheeeeeee");
                        log.info("hascode:[{}]",sc.hashCode());
                        log.info("连接成功，remote_address:[{}]",sc.remoteAddress() );
                        sc.pipeline()
                                .addLast("decoder",new HttpRequestDecoder())
                                .addLast("encoder",new HttpResponseDecoder())
                                .addLast("handler",new MyHttpHandler());

                    }
                });
        //serverBootstrap.bind(9000).sync();
        //System.out.println("初始化netty server ----");
        //log.info("init netty server success...");
    }

    public NettyServer(){
        //netty服务启动类
        serverBootstrap = new ServerBootstrap();
        //主线程组
        mainGroup = new NioEventLoopGroup();
        //工作线程组
        workGroup = new NioEventLoopGroup();

        serverBootstrap.group(mainGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        //System.out.println(sc.remoteAddress() + "hhhhhheeeeeee");
                        log.info("连接成功，remote_address:[{}]",sc.remoteAddress() );
                        log.info("hascode:[{}]",sc.hashCode());
                        sc.pipeline()
                                //.addLast("decoder",new HttpRequestDecoder())//HTTP解码器
                                .addLast("decoder",new HttpServerCodec())//适用于websocket
                                //.addLast("encoder",new HttpResponseDecoder())//HTTP编码器
                                .addLast(new ChunkedWriteHandler())//支持写大数据流(websocket通信支持)
                                .addLast(new HttpObjectAggregator(1024*62))//http聚合器
                                .addLast(new WebSocketServerProtocolHandler("/ws"))//websocket支持，设置路由
                                //.addLast("handler",new MyHttpHandler());//自定义处理器，处理核心业务
                                .addLast("websockerhandler",new MyWebsocketHandler());

                    }
                });
    }


    public void start(Integer port) throws InterruptedException {
        serverBootstrap.bind(port).sync();
        log.info("NettyServer start success...[监听端口:{}]",port);
    }

}
