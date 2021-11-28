package com.kdx.example.authen.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println(params.toString());
        res.put("kdx","cccccc");
        return params;
    }

}
