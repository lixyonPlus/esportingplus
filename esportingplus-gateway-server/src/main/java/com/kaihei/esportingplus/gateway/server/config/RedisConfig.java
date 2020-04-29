package com.kaihei.esportingplus.gateway.server.config;

import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Redis缓存配置类
 *
 *@author  Orochi-Yzh
 *@dateTime  2018/3/16 17:54
 *@updatetor
 */
@Configuration
public class RedisConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${redis.nodes:[\"localhost:6379\"]}")
    private String redisNodes;

    @Value("${redis.cluster-model:single}")
    private String clusterModel;

    @Value("${redis.password:}")
    private String password;

    @Value("${redis.database:0}")
    private int database;

    @Value("${redis.config.maxTotal:2000}")
    private int maxTotal;

    @Value("${redis.config.maxIdle:10}")
    private int maxIdle;

    @Value("${redis.config.minIdle:1}")
    private int minIdle;

    @Value("${redis.config.maxWaitMillis:20000}")
    private int maxWaitMillis;

    @Value("${redis.config.testOnBorrow:true}")
    private String testOnBorrow;


    @PostConstruct
    private void initCacheManager() {
        Properties prop = new Properties();
        prop.setProperty("redis.cluster-model", clusterModel);
        prop.setProperty("redis.nodes", redisNodes);
        prop.setProperty("redis.password", password);
        prop.setProperty("redis.database", String.valueOf(database));
        prop.setProperty("redis.maxTotal", String.valueOf(maxTotal));
        prop.setProperty("redis.minIdle", String.valueOf(minIdle));
        prop.setProperty("redis.maxIdle", String.valueOf(maxIdle));
        prop.setProperty("redis.maxWaitMillis", String.valueOf(maxWaitMillis));
        prop.setProperty("redis.evictorShutdownTimeoutMillis", "60000");
        prop.setProperty("redis.testOnBorrow", testOnBorrow);
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("redis properties: {}",prop);
        }
        CacheManagerFactory.create().init(prop);
    }
}