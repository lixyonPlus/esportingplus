package com.kaihei.esportingplus.gateway.server.limiter;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.kaihei.esportingplus.common.constant.SecurityConstants;
import com.kaihei.esportingplus.common.enums.BizExceptionEnum;
import com.kaihei.esportingplus.common.exception.BusinessException;
import com.kaihei.esportingplus.common.tools.IpAddressUtil;
import com.kaihei.esportingplus.gateway.server.config.RateLimitPropertiesConfig;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimiter;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties.Policy;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.repository.RateLimiterErrorHandler;
import com.netflix.zuul.context.RequestContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 * @author Orochi-Yzh
 * @Description: 优化源码: 重写calcRemainingLimit和calcRemainingQuota，原因这两个方法在每次限流请求来
 * 的时候都会检查key是否过期，然后自增。优化为：只有第一次限流请求到来设置过期
 * @dateTime 2018/8/22 11:53
 */
@Component
@ConditionalOnProperty(value = "zuul.ratelimit.enabled", havingValue = "true")
public class CustomRedisRateLimiter implements RateLimiter {

    @Autowired
    private RateLimitPropertiesConfig rateLimitPropertiesConfig;

    //搞一个map存储 url对应的过期时间
    private ConcurrentHashMap<String, Long> urlTtlMap = new ConcurrentHashMap<>();
    //码农们请注意逼格，不然要被leader打，初始化脚本到内存，防止并发读取本地IO造成性能瓶颈
    private static String limitLua;

    static {
        try {
            limitLua = Files
                    .toString(ResourceUtils.getFile("classpath:rateLimit.lua"),
                            StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new BusinessException(BizExceptionEnum.READ_LIMIT_LUA_ERROR, e);
        }
    }

    @Value("${zuul.ratelimit.default-policy.limit}")
    private long qpsLimit;

    @Autowired
    private RateLimiterErrorHandler rateLimiterErrorHandler;

    private CacheManager cacheManager = CacheManagerFactory.create();

    @Override
    public RatePro consume(Policy policy, String urlRateKey, Long requestTime) {
        RatePro rate = new RatePro(urlRateKey);
        //随机分配限流key过期时间，防止并发造成海量key集中过期导致redis阻塞风险
        long rateTtl = 0;
        if (urlTtlMap.get(urlRateKey) == null || urlTtlMap.isEmpty()) {
            int randomTtl = new Random().nextInt(policy.getRefreshInterval().intValue());
            urlTtlMap.put(urlRateKey, (long) randomTtl);
            rateTtl = randomTtl;
        } else {
            rateTtl = urlTtlMap.get(urlRateKey);
        }

        long limit = policy.getLimit();

        //执行lua限流脚本
        long currentRequest = 0;
        ArrayList<String> keys = Lists.newArrayList(urlRateKey);
        //如果针对用户开启限流
        if (rateLimitPropertiesConfig.isEnabledUser()) {
            //获取用户Token
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            String token = request.getHeader(SecurityConstants.AUTHORIZATION);
            //生成用户限流key
            String userRateKey =
                    urlRateKey + ":" + token + ":" + IpAddressUtil.getIpAdrress(request);
            keys.add(userRateKey);
            rate.setUserRateKey(userRateKey);
        }
        try {
            //总请求数=平均qps*过期秒数
            long qpsTotal = limit * rateTtl;
            currentRequest =
                    cacheManager.eval(limitLua, keys,
                            Lists.newArrayList(qpsTotal + "", rateTtl + ""));
        } catch (RuntimeException var11) {
            String msg = "执行限流Lua脚本异常，Keys: " + keys;
            rateLimiterErrorHandler.handleError(msg, var11);
        }

        rate.setUrlRateKey(urlRateKey);
        //取消自带的设置剩余次数，默认前置过滤器根据剩余次数来判断是否超出限流大小
        rate.setCurrentRequest(currentRequest);
        //重置随机后的refreshInterval
        rate.setRateTtl(rateTtl);
        return rate;
    }

}
