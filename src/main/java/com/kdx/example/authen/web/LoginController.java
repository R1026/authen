package com.kdx.example.authen.web;

import com.kdx.example.authen.common.RRExcection;
import com.kdx.example.authen.service.IUserInfoService;
import com.kdx.example.authen.task.AsyncTask;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
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
@Api(value = "LoginController",tags = "登录")
@Slf4j
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 登录
     * @param params {"username":"","password":""}
     * @return
     */
    @PostMapping("/addlogin")
    public Map addLogin(@RequestBody Map<String,String> params){
        //Map res = new HashMap();
        String type = params.get("type");
        String username = params.get("username");
        String password = params.get("password");
        String phone = params.get("phone");
        String vcode = params.get("vcode");
        if ("1".equals(type)){
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
                throw new RRExcection(200,"用户名或密码不为空","用户名或密码不为空");
            }
            //return userInfoService.loginByUsername(username,password);
        }
        if ("2".equals(type)){
            if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(vcode)){
                throw new RRExcection(200,"手机号或验证码不为空","手机号或验证码不为空");
            }
        }
        //return res;
        throw new RRExcection(300,"无效操作","无效操作");
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

    /*public static void main(String[] args) {
        System.out.println("主线程开始执行。");

        *//*CompletableFuture.runAsync(()->{
            for (int i = 1; i <= 500; i++) {
                System.out.println("子线程执行("+ Thread.currentThread().getName() +")：" + i);
            }
        });*//*

        for (int i = 1; i <= 500; i++) {
            final int ii = i;
            CompletableFuture.runAsync(()->{
                System.out.println("子线程执行("+ Thread.currentThread().getName() +")：" + ii);
            });
        }

        try {
            Thread.sleep(8000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }*/


    public static void main(String[] args) {
        /*AbstractChannel ac = new AbstractChannel();
        ac.setNextHandler(new Handler1()).setNextHandler(new Handler2());
        ac.excute("hello .my name is kedongxing");*/
    }





}
