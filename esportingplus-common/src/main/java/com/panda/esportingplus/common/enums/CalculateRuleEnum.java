package com.panda.esportingplus.common.enums;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 计算规则枚举
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public enum CalculateRuleEnum {

    /**
     * 大于
     */
    GREATER_THAN(2, "大于", ">"),

    /**
     * 大于等于
     */
    GREATER_THAN_OR_EQUAL(1, "大于等于", ">="),

    /**
     * 等于
     */
    EQUAL(0, "等于", "=="),

    /**
     * 小于等于
     */
    LESS_THAN_OR_EQUAL(-1, "小于", "<="),

    /**
     * 小于
     */
    LESS_THAN(-2, "小于", "<"),

    UNKNOWN(-10, "未知", "");

    private int code;
    private String desc;
    private String operatorDesc;

    CalculateRuleEnum(int code, String desc, String operatorDesc) {
        this.code = code;
        this.desc = desc;
        this.operatorDesc = operatorDesc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getOperatorDesc() {
        return operatorDesc;
    }

    public static CalculateRuleEnum getByCode(int code) {
        CalculateRuleEnum[] values = CalculateRuleEnum.values();
        for (CalculateRuleEnum baojiLevelEnum : values) {
            if (baojiLevelEnum.getCode() == code) {
                return baojiLevelEnum;
            }
        }
        return UNKNOWN;
    }

    public static List<BaseEnum> getBase(){
        return Stream.of(GREATER_THAN, GREATER_THAN_OR_EQUAL, EQUAL, LESS_THAN_OR_EQUAL, LESS_THAN)
                .map(e -> BaseEnum.builder()
                        .code(e.getCode())
                        .desc(e.getDesc())
                        .build())
                .collect(Collectors.toList());
    }
}
