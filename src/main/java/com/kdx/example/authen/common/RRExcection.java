package com.kdx.example.authen.common;

import lombok.Data;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/12/12
 */
@Data
public class RRExcection extends RuntimeException {

    private int code = 500;

    private String msg;

    private String errormsg;


    public RRExcection(int code, String msg, String errormsg) {
        super(errormsg);
        this.code = code;
        this.msg = msg;
        this.errormsg = errormsg;
    }

    public RRExcection(String message, int code, String msg, String errormsg) {
        super(errormsg);
        this.code = code;
        this.msg = msg;
        this.errormsg = errormsg;
    }

    public RRExcection(String message, Throwable cause, int code, String msg, String errormsg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
        this.errormsg = errormsg;
    }

    public RRExcection(Throwable cause, int code, String msg, String errormsg) {
        super(cause);
        this.code = code;
        this.msg = msg;
        this.errormsg = errormsg;
    }

    public RRExcection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg, String errormsg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
        this.errormsg = errormsg;
    }
}
