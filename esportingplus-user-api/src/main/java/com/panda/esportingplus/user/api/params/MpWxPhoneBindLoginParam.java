package com.panda.esportingplus.user.api.params;

import java.io.Serializable;

/**
 * @Auther: shusong.liang
 * @Date: 2020/03/31 17:23
 * @Description:
 */
public class MpWxPhoneBindLoginParam implements Serializable {

    private static final long serialVersionUID = 9020252867287173171L;

    private MiniprogramUserInfo userInfo;
    private MiniprogramPhoneInfo phoneInfo;
    private String openid;
    private String channel;


    public MiniprogramUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(MiniprogramUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public MiniprogramPhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(MiniprogramPhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
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
