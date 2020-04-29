package com.kaihei.esportingplus.gateway.server.fallback;

import com.kaihei.esportingplus.common.ResponsePacket;
import com.kaihei.esportingplus.common.enums.BizExceptionEnum;
import com.kaihei.esportingplus.common.enums.ServicesEnum;
import com.kaihei.esportingplus.common.tools.FastJsonUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

/**
 * 服务失败回退处理
 *
 * @author Orochi-Yzh
 * @dateTime 2018/6/21 16:28
 * @updatetor
 */
public class BaseFallbackProvider implements FallbackProvider {

    private Logger LOGGER = LoggerFactory.getLogger(BaseFallbackProvider.class);

    private ServicesEnum serviceId;

    @Override
    public String getRoute() {

        // 注意: 这里是route的名称，不是服务的名称，
        // 如果这里写成大写USER-SERVICE将无法起到回退作用
        //如果需要所有调用都支持回退，则return "*"或return null
        return serviceId.getCode();
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        LOGGER.error(cause.getMessage(),cause);
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {

                ResponsePacket responsePacket =
                        ResponsePacket.onError(BizExceptionEnum.GATEWAY_FALLBACK);
                LOGGER.error(">> [{}][{}]暂不可用, 请稍后再试!",
                        serviceId.getDesc(), serviceId.getCode());
                return new ByteArrayInputStream(FastJsonUtils.toJson(responsePacket).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }

    void setServiceId(ServicesEnum serviceId) {
        this.serviceId = serviceId;
    }
}