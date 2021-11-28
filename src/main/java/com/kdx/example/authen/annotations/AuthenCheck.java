package com.kdx.example.authen.annotations;

import java.lang.annotation.*;

/**
 * @Desc --自定义权限校验注解
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/11/28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthenCheck {

    /**
     * 是否需要校验
     * @return
     */
    boolean required() default false;

}
