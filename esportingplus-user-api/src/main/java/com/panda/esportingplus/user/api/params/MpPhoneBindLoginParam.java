package com.panda.esportingplus.user.api.params;

import java.io.Serializable;

/**
 * @Auther: shusong.liang
 * @Date: 2020/03/31 17:23
 * @Description:
 */
public class MpPhoneBindLoginParam implements Serializable {

    private static final long serialVersionUID = -2790435457089441467L;

    private MiniprogramUserInfo userInfo;
    private String phone;
    private String code;
    private String openid;
    private String channel;
    

    public MiniprogramUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(MiniprogramUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
