package com.vwmin.pixivapi.service;


import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Data
public class PkceUtil {
    private String codeVerifier;
    private String codeChallenge;

    private PkceUtil(String codeVerifier, String codeChallenge) {
        this.codeVerifier = codeVerifier;
        this.codeChallenge = codeChallenge;
    }

    private static class SingletonHolder{
        private static final PkceUtil INSTANCE = init();
        private static PkceUtil init() {
//            PkceUtil pkceItem;
//            try {
//                final String verify = PkceUtil.generateCodeVerifier();
//                final String challenge = PkceUtil.generateCodeChallenge(verify);
//                pkceItem = new PkceUtil(verify, challenge);
//            } catch (Exception e) {
//                e.printStackTrace();
//                pkceItem = new PkceUtil(
//                        "-29P7XEuFCNdG-1aiYZ9tTeYrABWRHxS9ZVNr6yrdcI",
//                        "usItTkssolVsmIbxrf0o-O_FsdvZFANVPCf9jP4jP_0");
//            }
//            return pkceItem;

            return new PkceUtil(
                    "-29P7XEuFCNdG-1aiYZ9tTeYrABWRHxS9ZVNr6yrdcI",
                    "usItTkssolVsmIbxrf0o-O_FsdvZFANVPCf9jP4jP_0");
        }
    }

    public static PkceUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static String generateCodeVerifier() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] codeVerifier = new byte[32];
        secureRandom.nextBytes(codeVerifier);
        // fixme 这里应该存在问题
        return Base64.getUrlEncoder().encodeToString(codeVerifier);
    }

    private static String generateCodeChallenge(String codeVerifier) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytes = codeVerifier.getBytes("US-ASCII");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bytes, 0, bytes.length);
        byte[] digest = messageDigest.digest();
        return Base64.getUrlEncoder().encodeToString(digest);
    }
}
