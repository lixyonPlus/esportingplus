package com.panda.esportingplus.common.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用户性别枚举
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public enum UserSexEnum {

    /** 男 */
    MALE(1, "男"),

    /** 女 */
    FEMALE(2, "女"),

    /** 未知 */
    UNKNOWN(3, "未知");

    private Integer code;
    private String desc;



    UserSexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;

    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }



    public static UserSexEnum of(int code) {
        UserSexEnum[] values = UserSexEnum.values();
        for (UserSexEnum userIdentityEnum : values) {
            if (userIdentityEnum.getCode() == code) {
                return userIdentityEnum;
            }
        }
        return UNKNOWN;
    }

    public static List<BaseEnum> getBase(){
        return Stream.of(MALE, FEMALE)
                .map(e -> BaseEnum.builder()
                        .code(e.getCode())
                        .desc(e.getDesc())
                        .build())
                .collect(Collectors.toList());
    }
}
