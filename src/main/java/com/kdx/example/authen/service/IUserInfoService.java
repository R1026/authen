package com.kdx.example.authen.service;

import com.kdx.example.authen.entity.UserInfo;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/12/4
 */
public interface IUserInfoService {

    UserInfo register(UserInfo userInfo);

    boolean checkUsername(String username);

    //Map loginByUsername(String username, String password);
}
