package com.kaihei.esportingplus.gateway.server.filter.limit;

import com.kaihei.esportingplus.common.ResponsePacket;
import com.kaihei.esportingplus.common.constant.RedisKey;
import com.kaihei.esportingplus.common.constant.SecurityConstants;
import com.kaihei.esportingplus.common.enums.BizExceptionEnum;
import com.kaihei.esportingplus.common.tools.IpAddressUtil;
import com.kaihei.esportingplus.common.tools.TokenParseUtils;
import com.kaihei.esportingplus.gateway.server.config.FilterIgnorePropertiesConfig;
import com.kaihei.esportingplus.gateway.server.config.RateLimitPropertiesConfig;
import com.kaihei.esportingplus.gateway.server.limiter.CustomRedisRateLimiter;
import com.kaihei.esportingplus.gateway.server.limiter.RatePro;
import com.kaihei.esportingplus.gateway.server.utils.ZuulFilterUtils;
import com.kaihei.esportingplus.gateway.server.utils.ZuulResponseUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties.Policy;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.filters.RateLimitPreFilter;
import com.netflix.zuul.context.RequestContext;
import java.util.Collection;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.SystemPublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UrlPathHelper;

/**
 *@Description: 重写限流前置过虑器
 *
 *@author  Orochi-Yzh
 *@dateTime  2018/8/22 14:13
*/
@Component
@RefreshScope
@ConditionalOnProperty(value = "zuul.ratelimit.enabled", havingValue = "true")
public class CustomRateLimitPreFilter {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;
    @Autowired
    private RateLimitProperties properties;
    @Autowired
    private RouteLocator routeLocator;
    @Autowired
    private UrlPathHelper urlPathHelper;
    @Autowired
    private CustomRedisRateLimiter rateLimiter;
    @Autowired
    private RateLimitKeyGenerator rateLimitKeyGenerator;
    @Autowired
    private RateLimitUtils rateLimitUtils;
    @Autowired
    private SystemPublicMetrics systemPublicMetrics;
    @Autowired
    private RateLimitPropertiesConfig rateLimitPropertiesConfig;

    @Bean
    @Primary
    public RateLimitPreFilter rateLimitPreFilter() {
        return new RateLimitPreFilter(properties, routeLocator, urlPathHelper, rateLimiter, rateLimitKeyGenerator, rateLimitUtils) {
            @Override
            public int filterOrder() {
                return 20;
            }

            @Override
            public boolean shouldFilter() {
                RequestContext ctx = RequestContext.getCurrentContext();
                HttpServletRequest request = ctx.getRequest();
                String token = TokenParseUtils
                        .parseToken(request.getHeader(SecurityConstants.AUTHORIZATION));

                //1.IP黑名单校验,配置化，减少redis访问次数
                String clientIp = IpAddressUtil.getIpAdrress(request);
                if(rateLimitPropertiesConfig.getBacklist().contains(clientIp)){
                    ZuulResponseUtils.failJson(ctx,ResponsePacket.onError(BizExceptionEnum.BLACK_LIST),
                            HttpStatus.LOCKED.value());
                    ctx.set("blackList:" + token);
                    return false;
                }
                //2.检查忽略的接口
                boolean anyMatch = ZuulFilterUtils
                        .checkIgnore(request.getRequestURI(), filterIgnorePropertiesConfig);

                //3.内存压力监控，实时开启限流
                if(rateLimitPropertiesConfig.isEnabledMetrics()){
                    Collection<Metric<?>> metrics = systemPublicMetrics.metrics();
                    Optional<Metric<?>> freeMemoryMetric = metrics.stream()
                            .filter(t -> "mem.free".equals(t.getName()))
                            .findFirst();
                    // 如果不存在这个指标，稳妥起见，返回true，开启限流
                    if (!freeMemoryMetric.isPresent()) {
                        return true;
                    }
                    //当前剩余空闲内存
                    long currentFreeMemory = freeMemoryMetric.get()
                            .getValue()
                            .longValue();
                    // 如果可用内存小于指定值，开启流控
                    boolean lowMemory = currentFreeMemory < rateLimitPropertiesConfig.getMetricsFreeMemory();
                    if(lowMemory){
                        LOGGER.error("官人!网关内存压力过大(低于:{})，再不停下来我就要穿贞操带了：{}",
                                rateLimitPropertiesConfig.getMetricsFreeMemory(),currentFreeMemory);
                        return true;
                    }
                }

                return !anyMatch;
            }

            @Override
            public Object run() {
                RequestContext ctx = RequestContext.getCurrentContext();
                HttpServletRequest request = ctx.getRequest();
                String token = request.getHeader(SecurityConstants.AUTHORIZATION);

                String requestURI = urlPathHelper.getPathWithinApplication(request);
                //限流策略
                Policy defaultPolicy = properties.getDefaultPolicy();
                //限流校验
                RatePro rate = rateLimiter.consume(defaultPolicy,
                        RedisKey.RATELIMITE + requestURI, null);

                //lua返回结果
                Long currentRequest = rate.getCurrentRequest();
                //配置的qps
                Long avgQPSLimit = defaultPolicy.getLimit();
                //配置的限流key过期时间
                Long rateTtl = rate.getRateTtl();

                //单用户限流防刷生效了
                if(rate.getCurrentRequest() == -1){
                    LOGGER.debug("用户[{}]超出限流阈值: 接口=[{}],系统怀疑你是刷子，一秒钟搞了3次。",
                            token,requestURI);

                    ZuulResponseUtils.overRateLimit(ctx,token);
                }else{
                    //接口超出限流
                    //lua返回0，说明已经超出限流大小，直接异常
                    //lua返回结果/ttl > qps，也说明已经超出限流大小，直接异常
                    if (currentRequest == 0 || (currentRequest / rateTtl >= avgQPSLimit)) {
                        LOGGER.debug("用户[{}]超出限流阈值: 接口=[{}],平均qps[{}]=当前请求数[{}]/ttl[{}]",
                                token,requestURI, avgQPSLimit,currentRequest, rateTtl);
                        ZuulResponseUtils.overRateLimit(ctx,token);
                    }
                }

                return null;
            }
        };
    }

}
