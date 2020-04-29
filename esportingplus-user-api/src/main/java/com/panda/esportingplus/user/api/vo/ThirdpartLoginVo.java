package com.panda.esportingplus.user.api.vo;

import java.io.Serializable;

/**
 * @Auther: chen.junyong
 * @Date: 2018-09-12 15:16
 * @Description: 第三方注册登录返回参数
 */
public class ThirdpartLoginVo implements Serializable {

    private static final long serialVersionUID = -8609527769011431227L;

    private String auth_token;

    private String rcloud_token;

    private String said;

    private boolean phoneBind;

    private String refreshToken;


    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isPhoneBind() {
        return phoneBind;
    }

    public void setPhoneBind(boolean phoneBind) {
        this.phoneBind = phoneBind;
    }

    public String getSaid() {
        return said;
    }

    public void setSaid(String said) {
        this.said = said;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    public String getRcloud_token() {
        return rcloud_token;
    }

    public void setRcloud_token(String rcloud_token) {
        this.rcloud_token = rcloud_token;
    }
}
