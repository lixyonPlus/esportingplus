package com.panda.esportingplus.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "qiniu")
@ConditionalOnProperty(prefix = "qiniu",name = {"accessKey","secretKey","bucketName","bucketLink","bucketIsPub"})
public class QiNiuConfig {
    private  String accessKey;
    private  String secretKey;
    private  String bucketName;
    private  String bucketLink;
    private  Boolean bucketIsPub;



    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketLink() {
        return bucketLink;
    }

    public void setBucketLink(String bucketLink) {
        this.bucketLink = bucketLink;
    }

    public Boolean getBucketIsPub() {
        return bucketIsPub;
    }

    public void setBucketIsPub(Boolean bucketIsPub) {
        this.bucketIsPub = bucketIsPub;
    }

}
