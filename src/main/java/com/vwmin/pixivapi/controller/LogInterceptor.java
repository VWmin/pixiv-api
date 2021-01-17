package com.vwmin.pixivapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/17 14:21
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Handling request >>> " + request.getRequestURI() + "?" + request.getQueryString());
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long d = System.currentTimeMillis() - (Long) request.getAttribute("startTime");
        log.info("request processed after {}s{}ms", d/1000, d%1000);
    }

}
