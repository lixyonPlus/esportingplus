package com.panda.esportingplus.user.api.vo;


import com.panda.esportingplus.user.api.params.UserPhoneLoginParams;

/**
 * @Auther: shusong.liang
 * @Date: 2020-3-31 21:28
 * @Description:
 */
public class PhoneLoginContext {


    private UserPhoneLoginParams params;

    private String version;
    private String uid;
    private String mDeviceId;

    public PhoneLoginContext() {
    }

    public PhoneLoginContext(UserPhoneLoginParams params) {
        this.params = params;
    }

    public String getmDeviceId() {
        return mDeviceId;
    }

    public void setmDeviceId(String mDeviceId) {
        this.mDeviceId = mDeviceId;
    }

    public UserPhoneLoginParams getParams() {
        return params;
    }

    public void setParams(UserPhoneLoginParams params) {
        this.params = params;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
