package com.kdx.example.authen.service.impl;

import com.kdx.example.authen.dao.UserInfoMapper;
import com.kdx.example.authen.entity.UserInfo;
import com.kdx.example.authen.service.IUserInfoService;
import com.kdx.example.authen.utils.SnowflakeIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/12/4
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    private final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoMapper mapper;

    @Override
    public UserInfo register(UserInfo userInfo) {
        LocalDateTime itime = LocalDateTime.now();
        userInfo.setId(SnowflakeIdUtil.generateId());
        userInfo.setItime(itime);
        userInfo.setUtime(itime);
        mapper.insert(userInfo);
        return userInfo;
    }

    @Override
    public boolean checkUsername(String username) {
        List<UserInfo> list = mapper.selectByUsername(username);
        if (null != list && list.size()>0){
            return false;
        }
        return true;
    }

    /*@Override
    public Map loginByUsername(String username, String password) {
        Map<String,Object> map = new HashMap<>();
        List<UserInfo> list = mapper.selectByUsername(username);
        if (null != list && list.size()>0){
            UserInfo userInfo = list.get(0);
            if (password.equals(userInfo.getPassword())){
                map.put("_userinfo",userInfo);
                map.put("access_token",SnowflakeIdUtil.generateId());
                map.put("refush_token",SnowflakeIdUtil.generateId());
                return map;
            }else {
                throw new RRExcection(200,"账号或密码错误","账号或密码错误");
            }
        }else {
            throw new RRExcection(200,"账号不存在","账号不存在");
        }

    }*/
}
