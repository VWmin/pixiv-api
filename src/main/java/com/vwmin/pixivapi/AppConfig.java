package com.vwmin.pixivapi;

import com.vwmin.pixivapi.service.LoginService;
import com.vwmin.restproxy.RestProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/16 19:41
 */
@Component
public class AppConfig {

    @Bean
    public AuthApi authApi(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new LoginService.PixivLoginInterceptor());
        String url = "https://oauth.secure.pixiv.net";
        return new RestProxy<>(url, AuthApi.class, restTemplate).getApi();
    }

    @Bean
    public AppApi appApi(){
        String url = "https://app-api.pixiv.net";
        return new RestProxy<>(url, AppApi.class, new RestTemplate()).getApi();
    }
}
