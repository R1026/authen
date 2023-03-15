package com.kdx.example.authen.web;

import com.kdx.example.authen.entity.AppInfo;
import com.kdx.example.authen.service.IAppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2021/10/30
 */
@RestController
@RequestMapping("/appinfo")
public class AppInfoController {

    @Autowired
    private IAppInfoService service;

    @PostMapping("/addApp")
    public AppInfo addApp(@RequestBody AppInfo appInfo){
        service.addApp(appInfo);
        return appInfo;
    }

    @GetMapping("/qeuryAppDetail")
    public AppInfo queryAppDetail(@RequestParam String id){
        return service.queryAppDetail(id);
    }

}
