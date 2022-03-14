package com.kdx.example.authen.web;

import com.kdx.example.authen.task.AsyncTask;
import com.sun.deploy.net.HttpUtils;
import io.netty.handler.codec.http.HttpUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/11/28
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {



    /**
     * 登录
     * @param params {"username":"","password":""}
     * @return
     */
    @PostMapping("/addlogin")
    public Map addLogin(@RequestBody Map<String,String> params){
        Map res = new HashMap();

        return res;
    }

    @Autowired
    private Environment environment;
    @Autowired
    private AsyncTask task;

    @GetMapping("/codelogin")
    public Map codelogin(@RequestParam String code){
        Map<String,Object> map = new HashMap<>();
        try {
            System.out.println(code);
            String client_id = environment.getProperty("com.dx.git.client_id");
            String client_secret = environment.getProperty("com.dx.git.client_secret");
            String url = "https://github.com/login/oauth/access_token?client_id=" + client_id + "&client_secret=" + client_secret + "&code=" + code;
            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.postForObject(url, null, String.class);
            map.put("url",url);
            map.put("code",code);
            map.put("res",s);
            task.async_test(map);
            System.out.println("------>");
        }catch (Exception e){}
        return map;
    }





}
