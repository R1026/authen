package com.kdx.example.authen.aspect;

import com.kdx.example.authen.annotations.ApiLimit;
import com.kdx.example.authen.common.RRExcection;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kedongxing
 * @date 2022/6/27
 * @desc --接口限流切面
 */
@Aspect
@Component
public class LimiterAspect {

    private static final Logger log = LoggerFactory.getLogger(LimiterAspect.class);

    private Integer counts = 0;

    @Autowired(required = false)
    private RedissonClient client;

    @Before("@annotation(com.kdx.example.authen.annotations.ApiLimit)")
    public void doBefore(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        System.out.println(methodSignature.getMethod().getName());
        ApiLimit limit = methodSignature.getMethod().getAnnotation(ApiLimit.class);
        int count = limit.count();
        if (counts>count){
            throw new RRExcection(201,"请求过于频繁，请稍后再试。","请求过于频繁，请稍后再试。");
        }
        counts++;

    }



}
