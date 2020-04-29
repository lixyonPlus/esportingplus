package com.panda.esportingplus.common.config;

import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@ConditionalOnProperty(prefix = "snowflake", name = {"dataCenter"})
public class SnowFlakeRedisConfig {

    private static final String FIND_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final CacheManager cacheManager = CacheManagerFactory.create();

    /**
     * 续约超时时间（秒）
     */
    private final int DEFAULT_EXPIRE_SECONDS = 5 * 60;
    /**
     * 续约超时时间（毫秒）
     */
    private final int DEFAULT_EXPIRE_MILLISECONDS = DEFAULT_EXPIRE_SECONDS * 1000;
    /**
     * 查询machine最大重试次数
     */
    private final int DEFAULT_MAX_SEARCHCODE_TIMES = 3;
    /**
     * machine的最大值
     */
    private final int DEFAULT_MAX_MACHINE_CODE = 31;
    /**
     * 应用ID
     */
    private final String APP_ID = UUID.randomUUID().toString();
    /**
     * 数据中心
     */
    @Value("${snowflake.dataCenter:0}")
    private long dataCenter;
    /**
     * 机器标识
     */
    private Long machine;
    /**
     * 下次续约时间
     */
    private long renewTime = System.currentTimeMillis();
    @Value("${spring.application.name}")
    private String appName;
    /**
     * Redis Key前缀
     */
    private String prefix = "snowflake";

    public long getDataCenter() {
        return dataCenter;
    }

    /**
     * 获取机器标识
     */
    public long getMachine() {
        //初始查询可用的machine
        if (machine == null) {
            findUnUseMachineCode();
        }
        //续约
        renew();
        return machine;
    }

    private synchronized void findUnUseMachineCode() {
        //其他线程已经完成赋值操作，直接返回
        if (machine != null) {
            return;
        }

        //最大重试次数内循环查找可用的 machine
        for (int i = 0; i < DEFAULT_MAX_SEARCHCODE_TIMES; i++) {

            for (int j = 0; j <= DEFAULT_MAX_MACHINE_CODE; j++) {

                String key = prefix + ":" + appName + ":" + j;
                String result = cacheManager
                        .set(key, APP_ID, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME,
                                DEFAULT_EXPIRE_MILLISECONDS);
                //找到可用的 machine
                if (FIND_SUCCESS.equals(result)) {
                    this.machine = (long) j;
                    this.renewTime =
                            System.currentTimeMillis() + DEFAULT_EXPIRE_MILLISECONDS / 2;
                    return;
                }

            }

        }

        if (this.machine != null) {
            return;
        }
        //重试查找依然找不到可用的 machine 则抛出异常
        throw new RuntimeException("Redis 雪花算法无法找到合适的machine");
    }

    /**
     * 判断是否需要续约
     */
    private boolean shouldRenew() {
        return System.currentTimeMillis() > renewTime;
    }

    /**
     * 续约
     */
    private void renew() {
        //未超过续约时间直接返回
        if (!shouldRenew()) {
            return;
        }
        //超过时间同步进行续约
        synchronized (this) {
            //其他线程已经续约过了、直接返回
            if (!shouldRenew()) {
                return;
            }


            String key = prefix + ":" + appName + ":" + machine;
            Long expire = cacheManager.expire(key, DEFAULT_EXPIRE_SECONDS);
            //续约超时、重新查询可用的 machine
            if (expire == 1) {
                //续约成功、确认是否还是持有者
                String appId = cacheManager.get(key, String.class);
                if (APP_ID.equals(appId)) {
                    //重置续约时间
                    this.renewTime =
                            System.currentTimeMillis() + DEFAULT_EXPIRE_MILLISECONDS / 2;
                    return;
                }
            }
            machine = null;
            findUnUseMachineCode();
        }
    }

}
