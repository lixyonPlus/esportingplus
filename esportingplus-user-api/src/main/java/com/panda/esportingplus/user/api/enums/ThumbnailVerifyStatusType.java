package com.panda.esportingplus.user.api.enums;
/**
 * @author shusong.liang
 * @description: 图片、文字审核
 * @date: 2020/03/23 15:18
 */
public enum ThumbnailVerifyStatusType {

    MACHINE_VERIFY(0,"待机器审核"),
    VERIFY(1, "待人工审核"),
    VERIFY_FAIL(2, "审核不通过"),
    NORMAL(3, "正常");


    private Integer code;
    private String msg;

    ThumbnailVerifyStatusType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
