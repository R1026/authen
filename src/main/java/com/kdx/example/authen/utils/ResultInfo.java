package com.kdx.example.authen.utils;

import com.kdx.example.authen.enums.ResultCode;
import lombok.Data;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
@Data
public class ResultInfo<T> {

    private Integer code;
    private String msg;
    private T data;
    private String errormsg;
    private Boolean success;
    private long timestamp;

    public ResultInfo(){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultInfo<T> createSuccess(T data){
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.setData(data);
        resultInfo.setSuccess(Boolean.TRUE);
        resultInfo.setCode(ResultCode.OPERATION_SUCCEEDED.getCode());
        resultInfo.setMsg(ResultCode.OPERATION_SUCCEEDED.getMsg());
        return resultInfo;
    }

}
