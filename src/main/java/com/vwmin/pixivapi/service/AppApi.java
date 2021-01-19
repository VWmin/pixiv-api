package com.vwmin.pixivapi.service;

import com.vwmin.pixivapi.response.IllustResponse;
import com.vwmin.pixivapi.response.ListIllustResponse;
import com.vwmin.pixivapi.response.UserResponse;
import com.vwmin.restproxy.annotations.GET;
import com.vwmin.restproxy.annotations.Query;

/**
 * @author vwmin
 * @version 1.0
 * @date 2021/1/16 19:48
 */
public interface AppApi {
    @GET("/v1/illust/ranking?filter=for_android")
    ListIllustResponse rank(
            @Query("mode") String mode,
            @Query(value = "date" , required = false) String date
    );

    @GET("/v1/search/illust?filter=for_android&include_translated_tag_results=true")
    ListIllustResponse getIllustByWord(
            @Query("word") String word,
            @Query("sort") String sort,
            @Query("search_target") String searchTarget
    );

    @GET("/v2/illust/follow")
    ListIllustResponse getNewWorks(@Query("restrict") String restrict);

    @GET("/v2/illust/bookmark/add")
    String bookmark(@Query("illust_id") Long illustId, @Query("restrict") String restrict);

    @GET("/v1/illust/detail?filter=for_android")
    IllustResponse getIllustById(@Query("illust_id") Long illustId);

    @GET("/v1/user/detail?filter=for_android")
    UserResponse getUserById(@Query("user_id") Long userId);

    @GET("/v1/trending-tags/illust?filter=for_ios")
    String trend(); //fixme: unwrapped json




}
