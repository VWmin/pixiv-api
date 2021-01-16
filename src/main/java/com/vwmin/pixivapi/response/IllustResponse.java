package com.vwmin.pixivapi.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class IllustResponse implements Serializable {
    private ListIllustResponse.IllustsBean illust;
}
