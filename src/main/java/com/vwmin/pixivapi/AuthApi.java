package com.vwmin.pixivapi;

import com.vwmin.pixivapi.response.LoginResponse;
import com.vwmin.restproxy.annotations.Body;
import com.vwmin.restproxy.annotations.POST;
import org.springframework.util.MultiValueMap;


/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/16 19:39
 */
public interface AuthApi {
    @POST("/auth/token")
    LoginResponse login(@Body MultiValueMap<String, String> request);
}
