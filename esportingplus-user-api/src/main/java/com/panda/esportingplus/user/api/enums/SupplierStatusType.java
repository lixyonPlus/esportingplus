package com.panda.esportingplus.user.api.enums;

/**
 * @author shusong.liang
 * @description: 服务商审核状态类型
 * @date: 2020/03/23 15:18
 */
public enum SupplierStatusType {

    BAOJI_WATING(1,"待审核"),
    BAOJI_NORMAL(2,"正常"),
    BAOJI_FREEZE(3,"冻结"),
    //封禁暂时不需要考虑
    BAOJI_BANNED(4,"封禁");

    private Integer code;
    private String msg;

    SupplierStatusType(Integer code, String msg) {
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
