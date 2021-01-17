package com.vwmin.pixivapi.service;

import com.vwmin.pixivapi.AppApi;
import com.vwmin.pixivapi.response.IllustResponse;
import com.vwmin.pixivapi.response.ListIllustResponse;
import com.vwmin.pixivapi.response.UserResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/17 13:59
 */
@Service
public class AppService {
    private final LoginService loginService;
    private final AppApi appApi;

    public AppService(LoginService loginService, AppApi appApi) {
        this.loginService = loginService;
        this.appApi = appApi;
    }


    public ListIllustResponse getRank(String mode, String date) {
        return appApi.rank(mode, date);
    }

    public IllustResponse getIllustById(Long illustId) {
        return appApi.getIllustById(illustId);
    }

    public UserResponse getUserById(Long userId) {
        return appApi.getUserById(userId);
    }

    public ListIllustResponse getIllustByWord(String word, String sort, String searchTarget) {
        return appApi.getIllustByWord(word, sort, searchTarget);
    }

    public ListIllustResponse getNext(String nextUrl) {
        //todo
        return null;
    }

    public ListIllustResponse getNewWorks(String restrict) {
        return appApi.getNewWorks(restrict);
    }

    public String bookmark(Long illustId, String restrict) {
        return appApi.bookmark(illustId, restrict);
    }

    public String trend() {
        return appApi.trend();
    }


    public static class AppAccessRightInterceptor implements ClientHttpRequestInterceptor{

        private final LoginService loginService;

        public AppAccessRightInterceptor(LoginService loginService){
            this.loginService = loginService;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            HttpHeaders headers = request.getHeaders();
            headers.add("Authorization", "Bearer "+ loginService.getAccessToken());
            return execution.execute(request, body);
        }
    }
}
