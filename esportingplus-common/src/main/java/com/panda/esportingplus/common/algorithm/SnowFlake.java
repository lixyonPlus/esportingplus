package com.panda.esportingplus.common.algorithm;

import com.panda.esportingplus.common.config.SnowFlakeConfig;
import com.panda.esportingplus.common.config.SnowFlakeRedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 描述: Twitter的分布式自增 ID 雪花算法 snowflake
 *
 * @author shusong.liang
 *
 *
 * 配置文件上配置snowflake.dataCenter
 *
 * 如果存在Redis配置、则会自动使用Redis分布式雪花算法 否则则使用单节点式雪花算法
 * 使用方法
 * 在需要的地方注入 调用nextId {@link #nextId()}
 **/
@Component
@ConditionalOnProperty(prefix = "snowflake", name = {"dataCenter"})
public class SnowFlake {

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;

    /**
     * 序列号占用的位数
     */
    private final static long SEQUENCE_BIT = 12;
    /**
     * 机器标识占用的位数
     */
    private final static long MACHINE_BIT = 5;
    /**
     * 数据中心占用的位数
     */
    private final static long DATACENTER_BIT = 5;

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
    private final SnowFlakeConfig snowFlakeConfig;
    private final SnowFlakeRedisConfig snowFlakeRedisConfig;
    /**
     * 序列号
     */
    private long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private long lastStmp = -1L;


    public SnowFlake(@Autowired(required = false) SnowFlakeConfig snowFlakeConfig,
            @Autowired(required = false) SnowFlakeRedisConfig snowFlakeRedisConfig) {
        this.snowFlakeConfig = snowFlakeConfig;
        this.snowFlakeRedisConfig = snowFlakeRedisConfig;
    }

    public long nextId() {
        if (snowFlakeRedisConfig != null) {
            return nextId(snowFlakeRedisConfig.getDataCenter(), snowFlakeRedisConfig.getMachine());
        }
        return nextId(snowFlakeConfig.getDataCenter(), snowFlakeConfig.getMachine());
    }

    /**
     * 产生下一个ID
     */
    private synchronized long nextId(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException(
                    "datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException(
                    "machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }

        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;
        // 时间戳部分 | 数据中心部分 | 机器标识部分 | 序列号部分
        return (currStmp - START_STMP) << TIMESTMP_LEFT
                | datacenterId << DATACENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }
}