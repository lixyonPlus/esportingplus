package com.panda.esportingplus.common.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import com.panda.esportingplus.common.tools.IpAddressUtil;
/**
 *@Description: 在logback日志中加入 ip地址:第一块网卡的IP地址
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
*/
public class IPLogConfig extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return IpAddressUtil.getInetAddress(true);
    }
}