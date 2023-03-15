package com.kdx.example.authen.enums;

/**
 * @author kedongxing
 * @date 2022/6/27
 * @desc --限流策略
 */
public enum LimitType {

    /**
     * 默认策略全局限流
     */
    DEFAULT,
    /**
     * 根据请求者IP限流
     */
    IP;
}
