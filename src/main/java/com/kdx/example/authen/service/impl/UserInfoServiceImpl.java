package com.kdx.example.authen.service.impl;

import com.kdx.example.authen.entity.UserInfo;
import com.kdx.example.authen.service.IUserInfoService;
import com.kdx.example.authen.utils.SnowflakeIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Desc --
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/12/4
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    private final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Override
    public UserInfo register(UserInfo userInfo) {
        userInfo.setId(SnowflakeIdUtil.generateId());
        
        return null;
    }

    @Override
    public boolean checkUsername(String username) {
        for (int i = 0; i < 10; i++) {
            log.info("======>>>id[{}]:{}",i,SnowflakeIdUtil.generateId());
        }
        return false;
    }
}
