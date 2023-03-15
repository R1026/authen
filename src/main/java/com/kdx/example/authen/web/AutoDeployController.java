package com.kdx.example.authen.web;

import com.kdx.example.authen.common.ResultInfo;
import com.kdx.example.authen.utils.JschUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "自动部署",tags = "自动部署")
@RestController
@RequestMapping("/api/auto/deploy")
public class AutoDeployController {

    private static final Logger log = LoggerFactory.getLogger(AutoDeployController.class);

    @ApiOperation(value = "部署",notes = "部署")
    @GetMapping("/run")
    public ResultInfo run(){
        try {
            JschUtil jschUtil = new JschUtil("101.35.206.74",22,"root","ZN3K!UP)+[5e*Fc");
            jschUtil.connect();
            jschUtil.isDirExists("/apps/project");
            jschUtil.createDir("/apps/project/auto_deploy");
            jschUtil.uploadFile("","/apps/project/auto_deploy/application.properties");
        }catch (Exception e){
            log.error("运行异常",e);
        }
        return ResultInfo.createSuccess("");
    }

}
