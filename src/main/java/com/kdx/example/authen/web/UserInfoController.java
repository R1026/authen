package com.kdx.example.authen.web;

import com.kdx.example.authen.annotations.ApiLimit;
import com.kdx.example.authen.annotations.AuthenCheck;
import com.kdx.example.authen.entity.UserInfo;
import com.kdx.example.authen.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc --用户信息
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/12/4
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Autowired
    private IUserInfoService service;

    /**
     * 注册
     * @param userInfo
     * @return
     */
    @AuthenCheck
    @PostMapping("/register")
    public UserInfo register(@RequestBody UserInfo userInfo){
        return service.register(userInfo);
    }

    /**
     * 校验账号是否被注册
     * @param username
     * @return
     */
    @ApiLimit(count = 3)
    @AuthenCheck(required = false)
    @GetMapping("/checkUsername")
    public Integer checkUsername(@RequestParam String username){
        //Map res = new HashMap();
        boolean flag = service.checkUsername(username);
        if (flag){
            //res.put("username",username);
            return 0;
        }else {
            //throw new RRExcection(200,"很遗憾,此账号已被注册。","很遗憾,此账号已被注册。");
            return 1;
        }
    }

}
