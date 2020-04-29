package com.panda.esportingplus.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author shusong.liang
 * @Description
 * @Date 2020/03/20 12:00
 **/
@Configuration
@ConfigurationProperties(prefix = "wx.oauth")
public class WxAuthConfig {

    private String appId;
    private String appsecret;
    private String site_domain;
    private String codeUrl;
    private String tokenUrl;
    private String refreshtokenUrl;
    private String infoUrl;
    private String state;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getSite_domain() {
        return site_domain;
    }

    public void setSite_domain(String site_domain) {
        this.site_domain = site_domain;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getRefreshtokenUrl() {
        return refreshtokenUrl;
    }

    public void setRefreshtokenUrl(String refreshtokenUrl) {
        this.refreshtokenUrl = refreshtokenUrl;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
