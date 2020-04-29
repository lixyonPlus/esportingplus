package com.panda.esportingplus.common.enums;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <pre>
 *   客户端平台类型枚举
 *      客户端会在请求后加一个参数 x
 *      x=i 表示请求来自 iOS
 *      x=a 表示请求来自 Android
 *      x=mp 表示请求来自微信小程序
 * </pre>
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public enum PlatformEnum {

    /** iOS */
    iOS(1, "iOS", "i", "iOS"),

    /** Android */
    Android(2, "Android", "a", "Android"),

    /** 小程序 */
    MINI_PROGRAM(3, "miniprogram", "mp", "微信小程序"),

    ALL(0, "all", "", "所有平台"),

    OTHER(-1, "other", "", "其他平台类型"),
    ;



    private int platformCode;
    private String code;
    private String param;
    private String desc;

    PlatformEnum(int platformCode, String code, String param, String desc) {
        this.platformCode = platformCode;
        this.code = code;
        this.param = param;
        this.desc = desc;
    }

    public int getPlatformCode() {
        return platformCode;
    }

    public String getCode() {
        return code;
    }

    public String getParam() {
        return param;
    }

    public String getDesc() {
        return desc;
    }

    public static PlatformEnum getByPlatformCode(int platformCode) {
        PlatformEnum[] values = PlatformEnum.values();
        for (PlatformEnum platformEnum : values) {
            if (platformEnum.getPlatformCode() == platformCode) {
                return platformEnum;
            }
        }
        return OTHER;
    }

    public static PlatformEnum getByCode(String code) {
        PlatformEnum[] values = PlatformEnum.values();
        for (PlatformEnum platformEnum : values) {
            if (platformEnum.getCode().equalsIgnoreCase(code)) {
                return platformEnum;
            }
        }
        return OTHER;
    }

    public static PlatformEnum getByParam(String param) {
        PlatformEnum[] values = PlatformEnum.values();
        for (PlatformEnum platformEnum : values) {
            if (platformEnum.getParam().equalsIgnoreCase(param)) {
                return platformEnum;
            }
        }
        return OTHER;
    }

    public static List<BaseEnum> getBase(){
        return Stream.of(PlatformEnum.values())
                .map(e -> BaseEnum.builder()
                        .code(e.getPlatformCode())
                        .desc(e.getDesc())
                        .build())
                .collect(Collectors.toList());
    }
}
