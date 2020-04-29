package com.panda.esportingplus.common.enums;

/**
 * 状态枚举
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public enum StatusEnum {

    /** 禁用(无效、下架等...) */
    DISABLE(0, "禁用"),

    /** 启用(有效、上架等...) */
    ENABLE(1, "启用"),

    UNKNOWN(-1, "未知类型, 不存在这种情况");

    private int code;
    private String desc;

    StatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static StatusEnum getByCode(int code) {
        StatusEnum statusEnum = UNKNOWN;
        StatusEnum[] values = StatusEnum.values();
        for (StatusEnum anEnum : values) {
            if (anEnum.getCode() == code) {
                statusEnum =  anEnum;
                break;
            }
        }
        return statusEnum;
    }
}
