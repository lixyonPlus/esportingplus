package com.panda.esportingplus.user.api.enums;

/**
 * @author shusong.liang
 * @description:  司机认证
 * @date: 2019/1/14 14:44
 */
public enum RealCertEnum {
    REAL_NAME_NOT(0, "未认证"),
    REAL_NAME_EXIT(1, "已认证"),
    REAL_NAME_BLACK(2, "黑名单");

    private Integer code;
    private String msg;

    RealCertEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
