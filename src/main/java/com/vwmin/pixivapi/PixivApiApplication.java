package com.vwmin.pixivapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class PixivApiApplication {

    public static void main(String[] args) {
        Properties prop = System.getProperties();
        prop.put("proxySet", true);
        prop.setProperty("socksProxyHost", "127.0.0.1");
        prop.setProperty("socksProxyPort", "9888");
        System.setProperty("http.proxyHost", "localhost");
        System.setProperty("http.proxyPort", "9888");
        System.setProperty("https.proxyHost", "localhost");
        System.setProperty("https.proxyPort", "9888");
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        SpringApplication.run(PixivApiApplication.class, args);
    }

}
