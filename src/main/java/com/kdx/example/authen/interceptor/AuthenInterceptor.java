package com.kdx.example.authen.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.kdx.example.authen.annotations.AuthenCheck;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/11/28
 */
@Component
public class AuthenInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(AuthenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        JSONObject resultInfo = new JSONObject();
        resultInfo.put("success",false);
        resultInfo.put("code",0);
        resultInfo.put("timestamp",System.currentTimeMillis());

        //1.是否忽略校验
        if (handleAuthenCheck(request,handler)){
            return true;
        }

        //2.校验请求头token。
        String access_token = request.getHeader("Authorization");
        log.info("请求方法：【{}】，请求头Token:【{}】,请求路径：【{}】",request.getMethod(),access_token,request.getRequestURI());


        resultInfo.put("errormsg","未授权访问!");
        response.reset();
        response.setCharacterEncoding(CharsetUtil.UTF_8.toString());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(resultInfo.toString());
        response.getWriter().flush();

        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

    private boolean handleAuthenCheck(HttpServletRequest request, Object handler) {
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            AuthenCheck authenCheck = handlerMethod.getMethod().getAnnotation(AuthenCheck.class);
            if (ObjectUtils.isEmpty(authenCheck)){
                return true;
            }
            boolean required = authenCheck.required();
            return required;
        }
        return true;

    }

}
