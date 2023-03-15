package com.kdx.example.authen.common;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
@RestControllerAdvice
public class RRExcectionHandle {

    private final Logger log = LoggerFactory.getLogger(RRExcectionHandle.class);

    @ExceptionHandler(RRExcection.class)
    public ResultInfo excectionHandle(RRExcection e){
        //log.error("======>>>接口调用异常({})：",System.currentTimeMillis(),e);
        ResultInfo info = new ResultInfo();
        info.setMsg(e.getMsg());
        info.setCode(e.getCode());
        info.setErrormsg(e.getMessage());
        info.setSuccess(Boolean.FALSE);
        return info;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultInfo noFoundHandle(NoHandlerFoundException e){
        log.error("======>>>接口调用异常({})：",System.currentTimeMillis(),e);
        ResultInfo info = new ResultInfo();
        info.setMsg(HttpStatus.NOT_FOUND.getReasonPhrase());
        info.setCode(HttpStatus.NOT_FOUND.value());
        info.setErrormsg(e.getMessage());
        info.setSuccess(Boolean.FALSE);
        return info;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultInfo notSupporteHandle(HttpRequestMethodNotSupportedException e){
        log.error("======>>>接口调用异常({})：",System.currentTimeMillis(),e);
        ResultInfo info = new ResultInfo();
        info.setMsg(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        info.setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
        info.setErrormsg(e.getMessage());
        info.setSuccess(Boolean.FALSE);
        return info;
    }


    @ExceptionHandler(Exception.class)
    public ResultInfo notSupporteHandle(Exception e){
        //log.error("======>>>接口调用异常({})：",System.currentTimeMillis(),e);
        log.error("[{}] error:{}",
                DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"),
                e.getMessage(),
                e);
        ResultInfo info = new ResultInfo();
        info.setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        info.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        info.setErrormsg(e.getMessage());
        info.setSuccess(Boolean.FALSE);
        return info;
    }



}
