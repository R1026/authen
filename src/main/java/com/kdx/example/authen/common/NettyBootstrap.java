package com.kdx.example.authen.common;

import com.kdx.example.authen.web.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/3/17
 */
@Component
public class NettyBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NettyServer nettyServer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (null == event.getApplicationContext().getParent()){
            try {
                nettyServer.start(8899);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
