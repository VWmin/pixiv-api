package com.vwmin.pixivapi.service;

import com.vwmin.pixivapi.response.IllustResponse;
import com.vwmin.pixivapi.response.ListIllustResponse;
import com.vwmin.pixivapi.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/17 13:59
 */
@Service
public class AppService {
    private final AppApi appApi;
    private final AppAccessRightInterceptor interceptor;

    public AppService(AppApi appApi, AppAccessRightInterceptor interceptor) {
        this.appApi = appApi;
        this.interceptor = interceptor;
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

    public ListIllustResponse getNewWorks(String restrict, String username) {
        interceptor.setUser(username);
        return appApi.getNewWorks(restrict);
    }

    public String bookmark(Long illustId, String restrict) {
        return appApi.bookmark(illustId, restrict);
    }

    public String trend() {
        return appApi.trend();
    }


}
