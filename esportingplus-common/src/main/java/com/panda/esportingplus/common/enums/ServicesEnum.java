package com.panda.esportingplus.common.enums;

/**
 * 服务名枚举
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 * @updatetor
 */
public enum ServicesEnum {

    MGT_SERVICE("esportingplus-mgt-service", "运营服务"),
    TRADE_SERVICE("esportingplus-trade-service", "订单服务"),
    ROUTE_SERVICE("esportingplus-route-service", "线路服务"),
    USER_SERVICE("esportingplus-user-service", "用户服务"),
    PAYMENT_SERVICE("esportingplus-payment-service", "支付服务"),
    TICKET_SERVICE("esportingplus-ticket-service", "车票服务"),
    CORE_SERVICE("esportingplus-core-service", "公共服务"),
    CUSTOMER_CENTER_SERVICE("esportingplus-core-service", "消息服务"),
    UNKNOWN("UNKNOWN", "未知服务");

    private String code;
    private String desc;

    ServicesEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ServicesEnum fromCode(String code) {
        for (ServicesEnum c : ServicesEnum.values()) {
            if (c.code.equals(code)) {
                return c;
            }
        }
        return UNKNOWN;
    }

    public static ServicesEnum fromDesc(String desc) {
        for (ServicesEnum c : ServicesEnum.values()) {
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