package com.kdx.example.authen.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kedongxing
 * @date 2022/6/28
 * @desc --
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "spring.redis",name = {"host","port"})
public class MyRedissonClient {

    @Autowired
    private RedissonConfig redissonConfig;

    @Bean
    public RedissonClient getRedissonClient(){
        log.info("redis://{}:{}",redissonConfig.getHost(),redissonConfig.getPort());
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redissonConfig.getHost() + ":" + redissonConfig.getPort());
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

}
