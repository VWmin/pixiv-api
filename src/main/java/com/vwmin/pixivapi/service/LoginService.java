package com.vwmin.pixivapi.service;

import com.vwmin.pixivapi.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/16 20:07
 */
@Slf4j
@Service
@EnableScheduling
public class LoginService {
    private final AuthApi authApi;
    private final Map<String, LoginResponse> loginSession = new ConcurrentHashMap<>();

    public LoginService(AuthApi authApi){
        this.authApi = authApi;
        login(USERNAME, PASSWORD); //登录默认账号
    }

    // bodies
    private static final String CLIENT_ID = "MOBrBDS8blbauoSck0ZfDbtuzpyT";
    private static final String CLIENT_SECRET = "lsACyCD94FhDUtGTXi3QzcFE2uU1hqtDaKeqrdwj";
    private static final String DEVICE_TOKEN = "pixiv";
    private static final String TYPE_PASSWORD = "password";
    private static final String TYPE_REFRESH = "refresh_token";
    private static final String USERNAME = "787236989";
    private static final String PASSWORD = "1903215898";

    // headers
    private static final String USER_AGENT = "PixivAndroidApp/5.0.175 (Android 9; ONEPLUS A6013)";
    private static final String ACCEPT_LANGUAGE = "zh_cn";
    private static final String APP_OS = "android";
    private static final String APP_OS_VERSION = "5.0.175";
    private static final String ACCEPT_ENCODING = "gzip";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.CHINA);
    private static final String SALT = "28c1fdd170a5204386cb1313c7077b34f83e4aaf4aa829ce78c231e05b0bae2c";



    public String getAccessToken(String username){
        return loginSession.get(username).getResponse().getAccess_token();
    }

    public String getAccessToken(){
        return getAccessToken(USERNAME);
    }

    public LoginResponse login(String username, String password){
        log.info("trying login with account: {}", username);

        MultiValueMap<String, String> body = basicLoginRequestBody();
        body.add("grant_type", TYPE_PASSWORD);
        body.add("username", username);
        body.add("password", password);

        LoginResponse response = authApi.login(body);
        loginSession.put(username, response);
        log.info("login success, get token >>> {}", response.getResponse().getAccess_token());
        return response;
    }


    public LoginResponse refreshToken(String username){
        log.info("trying refresh access token of user: {}.", username);

        MultiValueMap<String, String> body = basicLoginRequestBody();
        body.add("grant_type", TYPE_REFRESH);
        body.add("refresh_token", loginSession.get(username).getResponse().getRefresh_token());
        LoginResponse loginResponse = authApi.refreshToken(body);
        loginSession.put(username, loginResponse);

        log.info("got >> {}", loginResponse.getResponse().getAccess_token());

        return loginResponse;
    }

    public LoginResponse refreshTokenDefault(){
        return refreshToken(USERNAME);
    }


    @Scheduled(cron = "0 0 * * * ?")
    private void autoRefresh(){
        log.info("auto refresh start.");
        for (String username : loginSession.keySet()){
            refreshToken(username);
        }
        log.info("auto refresh finish.");
    }

    private static MultiValueMap<String, String> basicLoginRequestBody(){
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>(8);
        body.add("client_id", CLIENT_ID);
        body.add("client_secret", CLIENT_SECRET);
        body.add("device_token", DEVICE_TOKEN);
        body.add("get_secure_url", "true");
        body.add("include_policy", "true");
        return body;
    }


    public static class PixivLoginInterceptor implements ClientHttpRequestInterceptor{

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {
            HttpHeaders headers = request.getHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            String XClientTime = DATE_FORMAT.format(new Date());
            String XClientHash = DigestUtils.md5Hex(XClientTime + SALT);

            headers.add("x-client-time", XClientTime);
            headers.add("x-client-hash", XClientHash);
            headers.add("User-Agent", USER_AGENT);
            headers.add("Accept-Language", ACCEPT_LANGUAGE);

//            log.info("request body >>> " + new String(body, StandardCharsets.UTF_8));

            return execution.execute(request, body);
        }
    }
}
