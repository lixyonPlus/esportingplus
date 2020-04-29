package com.panda.esportingplus.common.enums;

public enum ThumbnailVerifyResultType {

    NORMAL(0, "正常"),
    SEXY(1, "性感"),
    PORNOGRAPHY(2, "色情"),
    VIOLENCE(3, "暴恐");

    private Integer code;
    private String msg;

    ThumbnailVerifyResultType(Integer code, String msg) {
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
