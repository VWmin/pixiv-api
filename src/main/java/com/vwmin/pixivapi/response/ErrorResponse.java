package com.vwmin.pixivapi.response;

import lombok.Data;

@Data
public class ErrorResponse {

    /**
     * error : {"user_message":"","message":"Error occurred at the OAuth process. Please check your Access Token to fix this. Error Message: invalid_grant","reason":"","user_message_details":{}}
     */

    private ErrorBean error;



    @Data
    public static class ErrorBean {
        /**
         * user_message :
         * message : Error occurred at the OAuth process. Please check your Access Token to fix this. Error Message: invalid_grant
         * reason :
         * user_message_details : {}
         */

        private String user_message;
        private String message;
        private String reason;
    }
}
