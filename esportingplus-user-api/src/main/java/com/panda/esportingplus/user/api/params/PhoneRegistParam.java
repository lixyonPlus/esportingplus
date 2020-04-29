package com.panda.esportingplus.user.api.params;

import java.io.Serializable;

/**
 * @Auther: shusong.liang
 * @Date: 2020-03-31 14:48
 * @Description: 手机注册参数类
 */
public class PhoneRegistParam extends RegistLoginBaseParam implements Serializable {

    private static final long serialVersionUID = -5948519802778187861L;

    /**
     * 手机
     */
    private String phone;

    /**
     * 验证码
     */
    private String code;

    private String shareId;

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
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
}
