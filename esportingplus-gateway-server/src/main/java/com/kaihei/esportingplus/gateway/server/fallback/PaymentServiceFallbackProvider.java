package com.kaihei.esportingplus.gateway.server.fallback;

import com.kaihei.esportingplus.common.enums.ServicesEnum;
import org.springframework.stereotype.Component;

/**
 * 支付服务失败回退处理
 *
 * @author haycco
 */
@Component
public class PaymentServiceFallbackProvider extends BaseFallbackProvider {

    public PaymentServiceFallbackProvider() {
        super.setServiceId(ServicesEnum.PAYMENT_SERVICE);
    }
}