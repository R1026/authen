package com.kdx.example.authen.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Desc --oauth2.0授权机制
 * @Author kedongxing
 * @Version 1.0.0
 * @Date 2022/1/6
 */
@Controller
@RequestMapping("/oauth2")
public class Oauth2Controller {

    /**
     * 获取令牌方式：1.授权码，2.隐藏式，3.密码式，4.客户端凭证
     * @param request
     * @return
     */
    @GetMapping("/authorize")
    public Map authorize(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String response_type = request.getParameter("response_type");
        String clientid = request.getParameter("client_id");
        String redirect_uri = request.getParameter("redirect_uri");
        String scope = request.getParameter("scope");
        String code = "220106";
        if (redirect_uri.contains("?")){
            String code_ = "&code=" + code;
            redirect_uri += code_;
        }else {
            String code_ = "?code=" + code;
            redirect_uri += code_;
        }
        response.sendRedirect(redirect_uri);
        return null;
    }

}
