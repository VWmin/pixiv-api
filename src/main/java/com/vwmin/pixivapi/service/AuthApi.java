package com.vwmin.pixivapi.service;

import com.vwmin.pixivapi.response.LoginResponse;
import com.vwmin.restproxy.annotations.Body;
import com.vwmin.restproxy.annotations.Field;
import com.vwmin.restproxy.annotations.POST;
import org.springframework.util.MultiValueMap;


/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/16 19:39
 */
public interface AuthApi {
    @POST("/auth/token")
    @Deprecated
    LoginResponse login(@Body MultiValueMap<String, String> request);


    @POST("/auth/token")
    LoginResponse code2Token(@Field("code") String code,
                             @Field("redirect_uri") String redirect,
                             @Field("grant_type") String grantType,
                             @Field("include_policy") Boolean includePolicy,
                             @Field("client_id") String clientId,
                             @Field("code_verifier") String codeVerifier,
                             @Field("client_secret") String clientSecret);

    @POST("/auth/token")
    LoginResponse refreshToken(@Body MultiValueMap<String, String> request);
}
