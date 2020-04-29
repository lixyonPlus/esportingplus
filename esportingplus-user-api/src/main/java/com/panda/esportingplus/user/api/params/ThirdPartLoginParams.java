package com.panda.esportingplus.user.api.params;

import java.io.Serializable;

/**
 * @Auther: shusong.liang
 * @Date: 2020-03-26 15:04
 * @Description: 第三方注册登录请求参数
 */
public class ThirdPartLoginParams extends RegistLoginBaseParam implements Serializable {

    private static final long serialVersionUID = -7630991410454441154L;

    /**
     * 微信access_token
     */
    private String credential;

    /**
     * 平台：目前支持 WX QQ 两个
     */
    private String platform;

    /**
     * 手机平台：1-IOS , 2-ANDROID
     */
    private String phonePlatform;

    /**
     * 微信open_id
     */
    private String identifier;


    /**
     * 腾讯unionid
     */
    private String unionid;




    public String getPhonePlatform() {
        return phonePlatform;
    }

    public void setPhonePlatform(String phonePlatform) {
        this.phonePlatform = phonePlatform;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    @Override
    public String getPlatform() {
        return platform;
    }

    @Override
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


}
