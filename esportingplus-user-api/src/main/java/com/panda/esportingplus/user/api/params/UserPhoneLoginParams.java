package com.panda.esportingplus.user.api.params;

import java.io.Serializable;


/**
 * @author shusong.liang
 * @Title: UserPhoneLoginParams
 * @Description: 手机登录参数
 * @date 2020/3/31 17:23
 */
public class UserPhoneLoginParams implements Serializable {

    private static final long serialVersionUID = -8751662703075392598L;

    /**
     *  用户信息
     */
    private String userAgent;

    /**
     *  渠道号
     */
    private String channel;

    /**
     *  验证码
     */
    private String code;

    /**
     *  设备号
     */
    private String deviceId;

    /**
     *  手机号
     */
    private String phone;

    /**
     *  格林尼时间戳
     */
    private String timestamp;

    /**
     *  hmacSHA1加密验签
     */
    private String sign;

    /**
     * h5请求标识
     * */
    private String h5Naughty;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getH5Naughty() {
        return h5Naughty;
    }

    public void setH5Naughty(String h5_naughty) {
        this.h5Naughty = h5Naughty;
    }
}
