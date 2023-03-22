package com.kdx.example.authen.common;

import com.kdx.example.authen.enums.ResultCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

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

    public static ResultInfo unauthorized(){
        ResultInfo resultInfo = new ResultInfo<>();
        resultInfo.setSuccess(Boolean.FALSE);
        resultInfo.setCode(HttpStatus.UNAUTHORIZED.value());
        resultInfo.setErrormsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        resultInfo.setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        return resultInfo;
    }

}
