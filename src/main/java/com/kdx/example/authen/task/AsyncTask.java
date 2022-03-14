package com.kdx.example.authen.task;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/1/8
 */
@Component
public class AsyncTask {


    @SneakyThrows
    @Async
    public void async_test(Map map) throws Exception{
        long l = System.currentTimeMillis();
        System.out.println(map.toString());
        Thread.sleep(5000);
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l);

    }

}
