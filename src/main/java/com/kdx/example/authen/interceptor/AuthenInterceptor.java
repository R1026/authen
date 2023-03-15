package com.kdx.example.authen.interceptor;

import com.kdx.example.authen.annotations.AuthenCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

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

        //是否映射到方法
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        //1.是否忽略校验
        if (!handleAuthenCheck(request,handler)){
            return true;
        }

        //2.校验请求头token。
        String access_token = request.getHeader("Authorization");
        log.info("======>>>请求方法：【{}】，Authorization:【{}】,请求路径：【{}】",request.getMethod(),access_token,request.getRequestURI());

        /*if (StringUtils.isEmpty(access_token)){
            JSONObject resultInfo = new JSONObject();
            resultInfo.put("timestamp",System.currentTimeMillis());
            resultInfo.put("success",false);
            resultInfo.put("code", ResultCode.INVALID_TOKEN.getCode());
            resultInfo.put("errormsg",ResultCode.INVALID_TOKEN.getMsg());

            response.reset();
            response.setStatus(401);
            response.setCharacterEncoding(CharsetUtil.UTF_8.toString());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(resultInfo.toString());
            response.getWriter().flush();
            return false;
        }*/



        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

    private boolean handleAuthenCheck(HttpServletRequest request, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(AuthenCheck.class)){
            AuthenCheck authenCheck = method.getAnnotation(AuthenCheck.class);
            boolean required = authenCheck.required();
            return required;
        }
        return true;
    }

}
