package com.panda.esportingplus.user.api.enums;

/**
 * @author shusong.liang
 * @description:
 * @date 2020/03/25 17:55
 */
public enum UserPictureVerifyEnum {

    USER_PICTURE_NEED_VERIFY_ENUM(0, "待人工审核的"),
    USER_PICTURE_PASS_VERIFY_ENUM(1, "审核通过的"),
    USER_PICTURE_REFUSE_VERIFY_ENUM(2, "审核不通过的");

    private Integer code;
    private String desc;

    UserPictureVerifyEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UserPictureVerifyEnum fromCode(Integer code) {
        for (UserPictureVerifyEnum c : UserPictureVerifyEnum.values()) {
            if (c.code.intValue() == code.intValue()) {
                return c;
            }
        }
        return USER_PICTURE_NEED_VERIFY_ENUM;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
