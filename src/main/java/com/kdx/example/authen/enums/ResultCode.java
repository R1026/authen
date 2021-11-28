package com.kdx.example.authen.enums;

import lombok.Data;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
public enum ResultCode {

    OPERATION_SUCCEEDED(200,"操作成功！"),
    OPERATION_FAILED(0,"操作失败！");


    private Integer code;
    private String msg;

    private ResultCode(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
