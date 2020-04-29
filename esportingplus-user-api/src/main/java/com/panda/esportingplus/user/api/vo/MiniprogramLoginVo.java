package com.panda.esportingplus.user.api.vo;

import java.io.Serializable;

/**
 * @Auther: shusong.liang
 * @Date: 2020-03-28 10:25
 * @Description:
 */
public class MiniprogramLoginVo implements Serializable {

    private static final long serialVersionUID = 6021349426272125521L;

    private String token;
    private String rcloudToken;
    private String openid;
    private String unionid;
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRcloudToken() {
        return rcloudToken;
    }

    public void setRcloudToken(String rcloudToken) {
        this.rcloudToken = rcloudToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
