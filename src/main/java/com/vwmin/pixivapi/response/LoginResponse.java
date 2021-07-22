package com.vwmin.pixivapi.response;

import lombok.Data;


@Data
public class LoginResponse {

    /**
     * response : {"access_token":"85gNg-K5V8a--iKUYjGenjsRysGMSUnEaEx5A87pImk","expires_in":3600,"token_type":"bearer","scope":"","refresh_token":"ahzpbJtuessOAJkceDVFWneaOGGRK9D7kahX_ZR7tgU","user":{"profile_image_urls":{"px_16x16":"https://s.pximg.net/common/images/no_profile_ss.png","px_50x50":"https://s.pximg.net/common/images/no_profile_s.png","px_170x170":"https://s.pximg.net/common/images/no_profile.png"},"id":"36911115","name":"废怯","account":"787236989","mail_address":"","is_premium":false,"x_restrict":0,"is_mail_authorized":false,"require_policy_agreement":false},"device_token":"c3c19c0056079d882bca19b504a6052e"}
     */

    private ResponseBean response;


    @Data
    public static class ResponseBean {
        /**
         * access_token : 85gNg-K5V8a--iKUYjGenjsRysGMSUnEaEx5A87pImk
         * expires_in : 3600
         * token_type : bearer
         * scope :
         * refresh_token : ahzpbJtuessOAJkceDVFWneaOGGRK9D7kahX_ZR7tgU
         * user : {"profile_image_urls":{"px_16x16":"https://s.pximg.net/common/images/no_profile_ss.png","px_50x50":"https://s.pximg.net/common/images/no_profile_s.png","px_170x170":"https://s.pximg.net/common/images/no_profile.png"},"id":"36911115","name":"废怯","account":"787236989","mail_address":"","is_premium":false,"x_restrict":0,"is_mail_authorized":false,"require_policy_agreement":false}
         * device_token : c3c19c0056079d882bca19b504a6052e
         */

        private String access_token;
        private int expires_in;
        private String token_type;
        private String scope;
        private String refresh_token;
        private UserBean user;
        private String device_token;


        @Data
        public static class UserBean {
            /**
             * profile_image_urls : {"px_16x16":"https://s.pximg.net/common/images/no_profile_ss.png","px_50x50":"https://s.pximg.net/common/images/no_profile_s.png","px_170x170":"https://s.pximg.net/common/images/no_profile.png"}
             * id : 36911115
             * name : 废怯
             * account : 787236989
             * mail_address :
             * is_premium : false
             * x_restrict : 0
             * is_mail_authorized : false
             * require_policy_agreement : false
             */

            private ProfileImageUrlsBean profile_image_urls;
            private String id;
            private String name;
            private String account;
            private String mail_address;
            private boolean is_premium;
            private int x_restrict;
            private boolean is_mail_authorized;
            private boolean require_policy_agreement;


            @Data
            public static class ProfileImageUrlsBean {
                /**
                 * px_16x16 : https://s.pximg.net/common/images/no_profile_ss.png
                 * px_50x50 : https://s.pximg.net/common/images/no_profile_s.png
                 * px_170x170 : https://s.pximg.net/common/images/no_profile.png
                 */

                private String px_16x16;
                private String px_50x50;
                private String px_170x170;

            }
        }
    }
}
