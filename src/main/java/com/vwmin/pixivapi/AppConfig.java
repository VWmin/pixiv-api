package com.vwmin.pixivapi;

import com.vwmin.pixivapi.controller.LogInterceptor;
import com.vwmin.pixivapi.service.AppApi;
import com.vwmin.pixivapi.service.AppService;
import com.vwmin.pixivapi.service.AuthApi;
import com.vwmin.pixivapi.service.LoginService;
import com.vwmin.restproxy.RestProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/16 19:41
 */
@Component
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public AuthApi authApi(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new LoginService.PixivLoginInterceptor());
        String url = "https://oauth.secure.pixiv.net";
        return new RestProxy<>(url, AuthApi.class, restTemplate).getApi();
    }

    @Bean
    public AppApi appApi(LoginService loginService){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new AppService.AppAccessRightInterceptor(loginService));
        String url = "https://app-api.pixiv.net";
        return new RestProxy<>(url, AppApi.class, restTemplate).getApi();
    }
}
