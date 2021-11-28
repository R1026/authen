package com.kdx.example.authen.config;

import com.kdx.example.authen.interceptor.AuthenInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/11/28
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

    private final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);

    @Autowired
    private AuthenInterceptor authenInterceptor;
    @Autowired
    private Environment environment;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String ignorelogin = environment.getProperty("ignorelogin", "");
        List<String> ignorelogins = Arrays.asList(ignorelogin.split(","));
        log.info("加载拦截器配置-忽略拦截路径：【{}】",ignorelogin);
        registry.addInterceptor(authenInterceptor)
                .addPathPatterns("/**").excludePathPatterns(ignorelogins);
    }
}
