package com.panda.esportingplus.common.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 分布式 id 生成配置
 * @author shusong.liang
 */
@Configuration
@ConfigurationProperties(prefix = "snowflake")
@ConditionalOnProperty(prefix = "snowflake", name = {"dataCenter", "machine"})
public class SnowFlakeConfig {

    /** 数据中心 */
    private long dataCenter;

    /** 机器标识 */
    private long machine;

    public void setDataCenter(long dataCenter) {
        this.dataCenter = dataCenter;
    }

    public void setMachine(long machine) {
        this.machine = machine;
    }

    public long getDataCenter() {
        return dataCenter;
    }

    public long getMachine() {
        return machine;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
