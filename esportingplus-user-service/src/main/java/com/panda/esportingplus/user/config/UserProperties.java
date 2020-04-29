package com.panda.esportingplus.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProperties {

    @Value("${user.info.domain}")
    private String userInfoDomain;
    @Value("${user.info.batch.query.url}")
    private String userInfoBatchQueryUrl;

    @Value("${user.info.single.query.url}")
    private String userInfoSingleQueryUrl;

    @Value("${user.info.update.avatar.url}")
    private String updateAvatarUrl;
    @Value("${avatar.url.exclude.rule}")
    private String excludeAvatarRule;

    @Value("${user.system.switch:0}")
    private Integer userSystemSwitch;

    @Value("${user.max.regist.perdevice:3}")
    private Integer userMaxRegistPerDevice;

    @Value("${user.max.login.perdevice:3}")
    private Integer userMaxLoginPerDevice;

    @Value("${user.app.token.expire:3600}")
    private Integer userAppTokenExpire;

    @Value("${user.mp.token.expire:3600}")
    private Integer userMpTokenExpire;

    @Value("${user.pc.token.expire:3600}")
    private Integer userPcTokenExpire;

    @Value("${user.regist.login.greeting.message:欢迎来到暴暴~}")
    private String userRegistLoginGreetingMessage;

    @Value("${bank.init.bonus:400}")
    private Integer bankInitBonus;

    @Value("${user.apple.test_phone}")
    private String userAppleTestPhone;

    @Value("${user.totp_interval}")
    private Long userTotpInterval;

    @Value("${wx.mp.authcode.url}")
    private String wxMpAuthCodeUrl;

    @Value("${wx.mp.app.id}")
    private String wxMpAppId;

    @Value("${wx.mp.app.secret}")
    private String wxMpAppSecret;

    @Value("${user.apple.test_phone}")
    private String testPhone;

    @Value("${user.sessioncontext.default.thumbnail}")
    private String defaultThumbnail;

    public String getTestPhone() {
        return testPhone;
    }

    public void setTestPhone(String testPhone) {
        this.testPhone = testPhone;
    }

    public String getWxMpAppId() {
        return wxMpAppId;
    }

    public void setWxMpAppId(String wxMpAppId) {
        this.wxMpAppId = wxMpAppId;
    }

    public String getWxMpAppSecret() {
        return wxMpAppSecret;
    }

    public void setWxMpAppSecret(String wxMpAppSecret) {
        this.wxMpAppSecret = wxMpAppSecret;
    }

    public String getWxMpAuthCodeUrl() {
        return wxMpAuthCodeUrl;
    }

    public void setWxMpAuthCodeUrl(String wxMpAuthCodeUrl) {
        this.wxMpAuthCodeUrl = wxMpAuthCodeUrl;
    }




    public Integer getBankInitBonus() {
        return bankInitBonus;
    }

    public void setBankInitBonus(Integer bankInitBonus) {
        this.bankInitBonus = bankInitBonus;
    }

    public String getUserRegistLoginGreetingMessage() {
        return userRegistLoginGreetingMessage;
    }

    public void setUserRegistLoginGreetingMessage(String userRegistLoginGreetingMessage) {
        this.userRegistLoginGreetingMessage = userRegistLoginGreetingMessage;
    }

    public Integer getUserMaxLoginPerDevice() {
        return userMaxLoginPerDevice;
    }

    public void setUserMaxLoginPerDevice(Integer userMaxLoginPerDevice) {
        this.userMaxLoginPerDevice = userMaxLoginPerDevice;
    }

    public Integer getUserAppTokenExpire() {
        return userAppTokenExpire;
    }

    public void setUserAppTokenExpire(Integer userAppTokenExpire) {
        this.userAppTokenExpire = userAppTokenExpire;
    }

    public Integer getUserMpTokenExpire() {
        return userMpTokenExpire;
    }

    public void setUserMpTokenExpire(Integer userMpTokenExpire) {
        this.userMpTokenExpire = userMpTokenExpire;
    }

    public Integer getUserPcTokenExpire() {
        return userPcTokenExpire;
    }

    public void setUserPcTokenExpire(Integer userPcTokenExpire) {
        this.userPcTokenExpire = userPcTokenExpire;
    }

    public Integer getUserMaxRegistPerDevice() {
        return userMaxRegistPerDevice;
    }

    public void setUserMaxRegistPerDevice(Integer userMaxRegistPerDevice) {
        this.userMaxRegistPerDevice = userMaxRegistPerDevice;
    }

    public Integer getUserSystemSwitch() {
        return userSystemSwitch;
    }

    public void setUserSystemSwitch(Integer userSystemSwitch) {
        this.userSystemSwitch = userSystemSwitch;
    }

    public String getUserInfoBatchQueryUrl() {
        return userInfoBatchQueryUrl;
    }

    public void setUserInfoBatchQueryUrl(String userInfoBatchQueryUrl) {
        this.userInfoBatchQueryUrl = userInfoBatchQueryUrl;
    }

    public String getUserInfoDomain() {
        return userInfoDomain;
    }

    public void setUserInfoDomain(String userInfoDomain) {
        this.userInfoDomain = userInfoDomain;
    }

    public String getUserInfoSingleQueryUrl() {
        return userInfoSingleQueryUrl;
    }

    public void setUserInfoSingleQueryUrl(String userInfoSingleQueryUrl) {
        this.userInfoSingleQueryUrl = userInfoSingleQueryUrl;
    }

    public String getUpdateAvatarUrl() {
        return updateAvatarUrl;
    }

    public void setUpdateAvatarUrl(String updateAvatarUrl) {
        this.updateAvatarUrl = updateAvatarUrl;
    }

    public String getExcludeAvatarRule() {
        return excludeAvatarRule;
    }

    public void setExcludeAvatarRule(String excludeAvatarRule) {
        this.excludeAvatarRule = excludeAvatarRule;
    }

    public String getUserAppleTestPhone() {
        return userAppleTestPhone;
    }

    public void setUserAppleTestPhone(String userAppleTestPhone) {
        this.userAppleTestPhone = userAppleTestPhone;
    }

    public Long getUserTotpInterval() {
        return userTotpInterval;
    }

    public void setUserTotpInterval(Long userTotpInterval) {
        this.userTotpInterval = userTotpInterval;
    }



    public String getDefaultThumbnail() {
        return defaultThumbnail;
    }

    public void setDefaultThumbnail(String defaultThumbnail) {
        this.defaultThumbnail = defaultThumbnail;
    }
}
