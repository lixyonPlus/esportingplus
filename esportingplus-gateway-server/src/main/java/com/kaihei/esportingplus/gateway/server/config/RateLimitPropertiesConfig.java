/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.kaihei.esportingplus.gateway.server.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author lengleng
 * @date 2018/1/9
 */
@Configuration
@ConditionalOnExpression("!'${zuul.ratelimit.custom}'.isEmpty()")
@ConfigurationProperties(prefix = "zuul.ratelimit.custom")
@RefreshScope
public class RateLimitPropertiesConfig {

    private boolean enabledMetrics = false;
    private boolean enabledUser = false;
    private long metricsFreeMemory = 0;
    private List<String> backlist = new ArrayList<>();

    public boolean isEnabledMetrics() {
        return enabledMetrics;
    }

    public void setEnabledMetrics(boolean enabledMetrics) {
        this.enabledMetrics = enabledMetrics;
    }

    public boolean isEnabledUser() {
        return enabledUser;
    }

    public void setEnabledUser(boolean enabledUser) {
        this.enabledUser = enabledUser;
    }

    public long getMetricsFreeMemory() {
        return metricsFreeMemory;
    }

    public void setMetricsFreeMemory(long metricsFreeMemory) {
        this.metricsFreeMemory = metricsFreeMemory;
    }

    public List<String> getBacklist() {
        return backlist;
    }

    public void setBacklist(List<String> backlist) {
        this.backlist = backlist;
    }
}
