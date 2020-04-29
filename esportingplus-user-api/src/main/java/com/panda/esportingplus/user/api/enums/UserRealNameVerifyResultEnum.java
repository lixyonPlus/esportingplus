package com.panda.esportingplus.user.api.enums;

/**
 * @author shusong.liang
 * @description:实名认证审核结果枚举类
 * @date 2020/03/23 15:18
 */
public enum UserRealNameVerifyResultEnum {
    REAL_NAME_VERIFY_RESULT_PEND(0, "待审核"),
    REAL_NAME_VERIFY_RESULT_PASS(1, "审核通过"),
    REAL_NAME_VERIFY_RESULT_FAILED(2, "审核不通过");

    private Integer code;
    private String msg;

    UserRealNameVerifyResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static UserRealNameVerifyResultEnum fromCode(Integer code) {
        for (UserRealNameVerifyResultEnum c : UserRealNameVerifyResultEnum.values()) {
            if (c.code.intValue() == code.intValue()) {
                return c;
            }
        }
        return REAL_NAME_VERIFY_RESULT_PEND;
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
