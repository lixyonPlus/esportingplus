package com.panda.esportingplus.common.sensors.service;

import com.alibaba.fastjson.JSONObject;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yangshidong
 * @Description: 神策服务实现类
 * @Title: SensorsAnalyticsServiceImpl
 */
@Component
public class SensorsAnalyticsServiceImpl implements SensorsAnalyticsService {

    private static final Logger logger = LoggerFactory.getLogger(SensorsAnalyticsServiceImpl.class);

    /*@Autowired
    private SensorsAnalytics sensorsAnalytics;*/

    @Value("${sensors.app-project-type}")
    private String app_project_type;

    private AtomicInteger count = new AtomicInteger(0);

    public SensorsAnalyticsServiceImpl() {
       /* ScheduledExecutorService flushExecutor = Executors.newScheduledThreadPool(1);
        flushExecutor.scheduleAtFixedRate(() -> {
            if (sensorsAnalytics != null) {
                if (count.get() > 0){
                    sensorsAnalytics.flush();
                }
            }
        }, 5L, 5L, TimeUnit.SECONDS);*/
    }

    public void clear() {
        count.set(0);
    }

    @Override
    public void trackSignUp(String registerId, String cookieId) {
        /*try {
            sensorsAnalytics.trackSignUp(registerId, cookieId);
            sensorsAnalytics.flush();
        } catch (Exception e) {
            logger.error("SA_ERROR: {},registerId={},eventName=trackSignUp,cookieId={}",
                    e.getMessage(), registerId, cookieId);
        }*/
    }

    @Override
    public void profileSet(String registerId, HashMap<String, Object> properties) {
       /* try {
            sensorsAnalytics.profileSet(registerId, true, properties);
            sensorsAnalytics.flush();
        } catch (Exception e) {
            logger.error("SA_ERROR: {},registerId={},eventName=profileSet,properties={}",
                    e.getMessage(), registerId, new JSONObject(properties).toJSONString());
        }*/
    }

    @Override
    public void profileSetOnce(String registerId, HashMap<String, Object> properties) {
       /* try {
            sensorsAnalytics.profileSetOnce(registerId, true, properties);
            sensorsAnalytics.flush();
        } catch (Exception e) {
            logger.error("SA_ERROR: {},registerId={},eventName=profileSetOnce,properties={}",
                    e.getMessage(), registerId, new JSONObject(properties).toJSONString());
        }*/
    }

    @Override
    public void track(String registerId, String eventName, HashMap<String, Object> properties) {
        /*try {
            //添加app_project_type
            Long start = System.currentTimeMillis();
            properties.put("app_project_type", app_project_type);
            sensorsAnalytics.track(registerId, true, eventName, properties);
            count.incrementAndGet();
            logger.debug("SA_track:start time={},registerId={},eventName={},properties={}", start, registerId, eventName, new JSONObject(properties).toJSONString());
        } catch (Exception e) {
            logger.error("SA_ERROR: {},registerId={},eventName={},properties={}", e.getMessage(),
                    registerId, eventName, new JSONObject(properties).toJSONString());
        }*/
    }

}
