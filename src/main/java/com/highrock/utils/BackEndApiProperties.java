package com.highrock.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/9/24.
 */
@Component
@ConfigurationProperties(prefix = "backEndApi")
public class BackEndApiProperties {
    private Map<String, String> url = new HashMap<String,String>();
    private Map<String, String> auth = new HashMap<String,String>();

    public Map<String, String> getUrl() {
        return url;
    }

    public void setUrl(Map<String, String> url) {
        this.url = url;
    }

    public Map<String, String> getAuth() {
        return auth;
    }

    public void setAuth(Map<String, String> auth) {
        this.auth = auth;
    }
}

