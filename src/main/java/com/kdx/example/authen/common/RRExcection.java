package com.kdx.example.authen.common;

import com.kdx.example.authen.enums.ResultCode;
import com.kdx.example.authen.utils.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
@RestControllerAdvice
public class RRExcection {

    private final Logger log = LoggerFactory.getLogger(RRExcection.class);

    @ExceptionHandler
    public ResultInfo excectionHandle(Exception e){
        log.error("接口调用异常({})：【{}】",System.currentTimeMillis(),e.getMessage());
        ResultInfo info = new ResultInfo();
        info.setMsg(ResultCode.OPERATION_FAILED.getMsg());
        info.setCode(ResultCode.OPERATION_FAILED.getCode());
        info.setErrormsg(e.getMessage());
        info.setSuccess(Boolean.FALSE);
        return info;
    }

}
