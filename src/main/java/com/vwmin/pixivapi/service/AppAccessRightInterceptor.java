package com.vwmin.pixivapi.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/31 15:47
 */
@Component
public class AppAccessRightInterceptor implements ClientHttpRequestInterceptor {
    //fixme: 该如何将动态Header应用到一个指定请求上？？？

    private final LoginService loginService;

    private boolean useOnce = false;
    private String username = "";

    public AppAccessRightInterceptor(LoginService loginService){
        this.loginService = loginService;
    }

    public void setUser(String username){
        this.username = username;
        useOnce = true;
    }


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        String token;
        if (useOnce){
            token = loginService.getAccessToken(username);
            useOnce = false;
        }else{
            token = loginService.getAccessToken();
        }
        headers.add("Authorization", "Bearer " + token);
        return execution.execute(request, body);
    }
}
