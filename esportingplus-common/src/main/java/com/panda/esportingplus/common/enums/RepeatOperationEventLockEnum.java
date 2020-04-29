package com.panda.esportingplus.common.enums;

/**
 * 防重事件枚举
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 * @updatetor
 */
public enum RepeatOperationEventLockEnum {
    MATCHING_TEAM_EVENT("MATCHING_TEAM_EVENT", "用户点击匹配操作事件"),
    PAID_NOTIFY_AND_CONFIRM_EVENT("PAID_NOTIFY_AND_CONFIRM_EVENT", "支付回调和确认支付互斥事件"),
    UNKNOWN("UNKNOWN", "未知事件");

    private String code;
    private String desc;

    RepeatOperationEventLockEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RepeatOperationEventLockEnum fromCode(String code) {
        for (RepeatOperationEventLockEnum c : RepeatOperationEventLockEnum.values()) {
            if (c.code.equals(code)) {
                return c;
            }
        }
        return UNKNOWN;
    }

    public static RepeatOperationEventLockEnum fromDesc(String desc) {
        for (RepeatOperationEventLockEnum c : RepeatOperationEventLockEnum.values()) {
            if (c.desc.equals(desc)) {
                return c;
            }
        }
        return UNKNOWN;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}