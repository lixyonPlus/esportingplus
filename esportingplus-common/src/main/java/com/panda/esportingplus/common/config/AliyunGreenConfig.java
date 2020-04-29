package com.panda.esportingplus.common.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("!'${aliyun.green}'.isEmpty()")
@ConfigurationProperties(prefix = "aliyun.green")
public class AliyunGreenConfig {

    private String accesskeyId;

    private String secret;

    private String url;

    public String getAccesskeyId() {
        return accesskeyId;
    }

    public void setAccesskeyId(String accesskeyId) {
        this.accesskeyId = accesskeyId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
