package com.panda.esportingplus.user.config;

import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Redis缓存配置类
 *
 *@author  shusong.liang
 *@dateTime  2020/03/25 17:54
 *@updatetor
 */
@Configuration
public class RedisConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${redis.nodes:[\"redis:6379\"]}")
    private String redisNodes;

    @Value("${redis.cluster-model:single}")
    private String clusterModel;

    @Value("${redis.password:}")
    private String password;

    @Value("${redis.database:0}")
    private int database;

    @Value("${redis.config.maxTotal:500}")
    private int maxTotal;

    @Value("${redis.config.maxIdle:10}")
    private int maxIdle;

    @Value("${redis.config.minIdle:1}")
    private int minIdle;

    @Value("${redis.config.maxWaitMillis:5000}")
    private int maxWaitMillis;

    @Value("${redis.config.testOnBorrow:false}")
    private String testOnBorrow;


    @PostConstruct
    public void initCacheManager() {
        Properties prop = new Properties();
        prop.setProperty("redis.cluster-model", clusterModel);
        prop.setProperty("redis.nodes", redisNodes);
        prop.setProperty("redis.password", password);
        prop.setProperty("redis.maxTotal", String.valueOf(maxTotal));
        prop.setProperty("redis.minIdle", String.valueOf(minIdle));
        prop.setProperty("redis.maxIdle", String.valueOf(maxIdle));
        prop.setProperty("redis.maxWaitMillis", String.valueOf(maxWaitMillis));
        prop.setProperty("redis.evictorShutdownTimeoutMillis", "60000");
        prop.setProperty("redis.testOnBorrow", testOnBorrow);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("redis properties: {}", prop);
        }
        CacheManagerFactory.create().init(prop);
    }
}