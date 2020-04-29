package com.panda.esportingplus.user.api.vo;

/**
 * @author shusong.liang
 * @Title: UserPhoneLoginVo
 * @Description: TODO
 * @date 2020/3/31 19:51
 */
public class UserPhoneLoginVo {

    /**
     * 融云token
     */
    private String rcloud_token;

    /**
     * true 验证码有效 \ false 验证码无效	是
     */
    private boolean is_valid;

    /**
     * 用户token，空字符串时为新用户，不空时为老用户
     */
    private String token;

    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRcloud_token() {
        return rcloud_token;
    }

    public void setRcloud_token(String rcloud_token) {
        this.rcloud_token = rcloud_token;
    }

    public boolean isIs_valid() {
        return is_valid;
    }

    public void setIs_valid(boolean is_valid) {
        this.is_valid = is_valid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
