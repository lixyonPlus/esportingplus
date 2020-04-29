package com.panda.esportingplus.user.api.enums;

/**
 * @author shusong.liang
 * @description: 服务商
 * @date: 2020/03/23 15:18
 */
public enum SupplierStatusEnum {
    NORMAL(1, "入驻"),
    FREEZE(2,"冻结");

    private Integer code;
    private String msg;

    SupplierStatusEnum(Integer code, String msg) {
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
