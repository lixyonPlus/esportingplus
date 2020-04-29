package com.panda.esportingplus.common.enums;

/**
 * 产品业务类型枚举
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public enum ProductBizTypeEnum {

    /** 通勤车 */
    GAMING_COMMUTER(1, "通勤车"),

    /** 周边游 */
    GAMING_PLAY(2, "周边游");

    private int code;
    private String desc;

    ProductBizTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ProductBizTypeEnum getByCode(int code) {
        ProductBizTypeEnum[] values = ProductBizTypeEnum.values();
        for (ProductBizTypeEnum productBizTypeEnum : values) {
            if (productBizTypeEnum.getCode() == code) {
                return productBizTypeEnum;
            }
        }
        return null;
    }
}
