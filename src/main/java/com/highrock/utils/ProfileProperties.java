package com.highrock.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/9/24.
 */
@Component
@ConfigurationProperties(prefix = "spring")
public class ProfileProperties {
    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
    }

    public Map<String, String> getEarthlingAuth() {
        return earthlingAuth;
    }

    public void setEarthlingAuth(Map<String, String> earthlingAuth) {
        this.earthlingAuth = earthlingAuth;
    }

    public Map<String, String> getEarthlingApiUrl() {
        return earthlingApiUrl;
    }

    public void setEarthlingApiUrl(Map<String, String> earthlingApiUrl) {
        this.earthlingApiUrl = earthlingApiUrl;
    }

    public String getBackEndApiUrl() {
        return backEndApiUrl;
    }

    public void setBackEndApiUrl(String backEndApiUrl) {
        this.backEndApiUrl = backEndApiUrl;
    }
    public String getS3LogBucket() {
        return s3LogBucket;
    }

    public void setS3LogBucket(String s3LogBucket) {
        this.s3LogBucket = s3LogBucket;
    }
    private String profiles;
    private Map<String, String> earthlingAuth = new HashMap<String,String>();
    private Map<String, String> earthlingApiUrl = new HashMap<String,String>();
    private String backEndApiUrl;
    private String s3LogBucket;

}

