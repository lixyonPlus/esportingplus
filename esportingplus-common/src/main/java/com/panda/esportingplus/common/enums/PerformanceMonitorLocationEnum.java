package com.panda.esportingplus.common.enums;

/**
 * 性能耗时监控起始：开始，中间层，结束
 * 注意：
 * 1.中间层的监控不能嵌套，否则出错，忽略出错的耗时统计
 * 2.START和END，配合使用，会统计期间的所有注解了TimeElapsed的方法耗时；
 *   如果只有START，无END，则只统计START处的总耗时
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public enum PerformanceMonitorLocationEnum {
    START,MID,END;
}
