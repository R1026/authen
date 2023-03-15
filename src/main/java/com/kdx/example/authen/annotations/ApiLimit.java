package com.kdx.example.authen.annotations;

import com.kdx.example.authen.enums.LimitType;

import java.lang.annotation.*;

/**
 * @author kedongxing
 * @date 2022/6/27
 * @desc --接口限流注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiLimit {

    /**
     * 限流key,用于redis存放数据所指定key前缀
     * @return
     */
    String key() default "api-limit";

    /**
     * 限流时间，单位：秒
     * @return
     */
    int time() default 60;

    /**
     * 限流次数，指定单位时间内接口可访问次数
     * @return
     */
    int count() default 120;

    /**
     * 限流策略
     * @return
     */
    LimitType limitType() default LimitType.DEFAULT;



}
