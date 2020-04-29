package com.panda.esportingplus.user.api.vo;

import java.io.Serializable;

/**
 * @Auther: shusong.liang
 * @Date: 2018-10-24 17:39
 * @Description:
 */
public class Watermark implements Serializable {

    private static final long serialVersionUID = -4538266452940371291L;

    private String appid;

    private String timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
