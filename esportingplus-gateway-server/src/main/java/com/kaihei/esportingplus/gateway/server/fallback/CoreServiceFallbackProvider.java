package com.kaihei.esportingplus.gateway.server.fallback;

import com.kaihei.esportingplus.common.enums.ServicesEnum;
import org.springframework.stereotype.Component;

/**
 * core 公用服务失败回退处理
 *
 * @author xiekeqing
 * @version 1.0
 * @date 2018/11/26 21:56
 */
@Component
public class CoreServiceFallbackProvider extends BaseFallbackProvider {

    public CoreServiceFallbackProvider() {
        super.setServiceId(ServicesEnum.CORE_SERVICE);
    }

}
