package com.vwmin.pixivapi.controller;

import com.vwmin.pixivapi.response.LoginResponse;
import com.vwmin.pixivapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/16 20:06
 */
@RestController
public class AppController {

    private final LoginService loginService;

    public AppController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(){
        LoginResponse login = loginService.login();
        return login.getResponse().getAccess_token();
    }
}
