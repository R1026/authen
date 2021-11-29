package com.kdx.example.authen.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/11/29
 */
public class RedisCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String redis_host = environment.getProperty("spring.redis.host", "");
        String redis_port = environment.getProperty("spring.redis.port", "");
        if (StringUtils.isEmpty(redis_host) || StringUtils.isEmpty(redis_port)){
            return false;
        }
        return true;
    }
}
