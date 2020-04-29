package com.kaihei.esportingplus.gateway.server.fallback;

import com.kaihei.esportingplus.common.enums.ServicesEnum;
import org.springframework.stereotype.Component;

/**
 * 营销服务失败回退处理
 *
 * @author haycco
 */
@Component
public class MarketingServiceFallbackProvider extends BaseFallbackProvider {

    public MarketingServiceFallbackProvider() {
        super.setServiceId(ServicesEnum.MARKETING_SERVICE);
    }
}