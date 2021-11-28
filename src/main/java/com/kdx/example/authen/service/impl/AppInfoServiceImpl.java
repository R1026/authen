package com.kdx.example.authen.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.kdx.example.authen.dao.AppInfoMapper;
import com.kdx.example.authen.entity.AppInfo;
import com.kdx.example.authen.service.IAppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
@Service
public class AppInfoServiceImpl implements IAppInfoService {

    @Autowired(required = false)
    private AppInfoMapper mapper;

    @Override
    public void addApp(AppInfo appInfo) {
        appInfo.setId("202110301900001");
        appInfo.setItime(new Date());
        mapper.insert(appInfo);
    }

    @Override
    public AppInfo queryAppDetail(String id) {
        AppInfo appinfo = mapper.selectById(id);
        return appinfo;
    }
}
