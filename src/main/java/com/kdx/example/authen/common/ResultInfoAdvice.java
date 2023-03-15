package com.kdx.example.authen.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一数据格式返回
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
@RestControllerAdvice
public class ResultInfoAdvice implements ResponseBodyAdvice {

    @Autowired
    private ObjectMapper oMapper;
    @Autowired
    private Environment environment;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResultInfo){
            return body;
        }
        String uriStr = request.getURI().toString();
        String ignoreResponse = environment.getProperty("com.dx.ignore.response");
        if (StringUtils.hasText(ignoreResponse)){
            String[] ignoreStrs = ignoreResponse.split(",");
            for (String item : ignoreStrs) {
                if (uriStr.contains(item)){
                    return body;
                }
            }
        }
        return ResultInfo.createSuccess(body);
    }
}
