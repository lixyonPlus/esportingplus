package com.panda.esportingplus.common.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @Description: 文件日志输出拦截器
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class LogstashLogFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        String message = event.getMessage();
        if (message.contains("com.panda")
                || message.contains("ERROR")
                || message.contains("WARN")) {
            return FilterReply.DENY; //不允许输出
        } else {
            return FilterReply.ACCEPT; //允许输入串
        }
    }
}