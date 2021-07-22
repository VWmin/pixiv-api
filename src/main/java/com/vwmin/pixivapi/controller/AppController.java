package com.vwmin.pixivapi.controller;

import com.vwmin.pixivapi.response.IllustResponse;
import com.vwmin.pixivapi.response.ListIllustResponse;
import com.vwmin.pixivapi.response.LoginResponse;
import com.vwmin.pixivapi.response.UserResponse;
import com.vwmin.pixivapi.service.AppService;
import com.vwmin.pixivapi.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/16 20:06
 */
@Slf4j
@RestController
public class AppController {

    private final LoginService loginService;
    private final AppService appService;

    public AppController(LoginService loginService, AppService appService){
        this.loginService = loginService;
        this.appService = appService;
    }

    @GetMapping("/illust/ranking")
    public ListIllustResponse getRank(@RequestParam String mode, @RequestParam(required = false) String date){
        return appService.getRank(mode, date);
    }

    @GetMapping("/illust/detail")
    public IllustResponse getIllustById(@RequestParam Long illustId){
        return appService.getIllustById(illustId);
    }

    @GetMapping("/user/detail")
    public UserResponse getUserById(@RequestParam Long userId){
        return appService.getUserById(userId);
    }

    @GetMapping("/search/illust")
    public ListIllustResponse getIllustByWord(@RequestParam String word,
                                              @RequestParam(required = false) String sort,
                                              @RequestParam(required = false) String searchTarget){
        return appService.getIllustByWord(word, sort, searchTarget);
    }

    @GetMapping("/next")
    public ListIllustResponse getNext(@RequestParam String nextUrl){
        return appService.getNext(nextUrl);
    }


    @GetMapping("/illust/new")
    public ListIllustResponse getNewWorks(@RequestParam String restrict,
                                          @RequestParam String username){
        return appService.getNewWorks(restrict, username);
    }

    @GetMapping("/illust/bookmark")
    public String bookmark(@RequestParam String restrict,
                           @RequestParam Long illustId){
        return appService.bookmark(illustId, restrict);
    }


    @GetMapping("/tag/trend")
    public String trend(){
        return appService.trend();
    }

//    @GetMapping("/login")
    @Deprecated
    public String login(@RequestParam String username,
                        @RequestParam String password){
        LoginResponse login = loginService.login(username, password);
        return login.getResponse().getAccess_token();
    }




    @GetMapping("/refresh")
    public String refresh(){
        LoginResponse loginResponse = loginService.refreshTokenDefault();
        return loginResponse.getResponse().getAccess_token();
    }
}
