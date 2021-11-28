package com.kdx.example.authen.service;

import com.kdx.example.authen.entity.AppInfo;

import java.util.List;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
public interface IAppInfoService {

    void addApp(AppInfo appInfo);

    AppInfo queryAppDetail(String id);
}
