package com.kaihei.esportingplus.gateway.server.fallback;

import com.kaihei.esportingplus.common.enums.ServicesEnum;
import org.springframework.stereotype.Component;

/**
 * 风控服务失败回退处理
 *
 * @author Orochi-Yzh
 * @dateTime 2018/6/21 16:28
 * @updatetor
 */
@Component
public class RiskRatingServiceFallbackProvider extends BaseFallbackProvider {

    public RiskRatingServiceFallbackProvider() {
        super.setServiceId(ServicesEnum.RISK_RATING_SERVICE);
    }
}