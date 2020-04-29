package com.panda.esportingplus.common.tools;

import java.util.Optional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public class DeviceUtil {

    public static String getDeviceId() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(it -> ((ServletRequestAttributes) it))
                .map(ServletRequestAttributes::getRequest)
                .map(it -> it.getHeader("HTTP-M-DEVICEID"))
                .map(Object::toString)
                .orElse(null);
    }
}
