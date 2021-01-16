package com.vwmin.pixivapi.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponse implements Serializable {
    private UserBean user;

    @Data
    public static class UserBean implements Serializable{
        private int id;
        private String name;
        private String account;
        private String comment;
        private boolean is_followed;
    }
}
