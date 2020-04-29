package com.panda.esportingplus.common.sensors.service;

import java.util.HashMap;

/**
 * @Description: 神策服务
 * @Author: yangshidong
 * @Date: 2018年9月12日 10:39
 */
public interface SensorsAnalyticsService {

    /**
     * 把匿名ID和注册ID贯通起来
     *
     * @param registerId 用户id
     * @param cookieId 匿名id
     */
    public void trackSignUp(String registerId, String cookieId);

    /**
     * 用户属性设置,适用于用户属性修改
     *
     * @param registerId 用户id
     * @param properties 用户属性信息
     */
    public void profileSet(String registerId, HashMap<String, Object> properties);

    /**
     * 用户属性设置,适用于注册
     * 该方法是对于只在首次设置时有效的属性，如果被设置的用户属性已存在，则这条记录会被忽略而不会覆盖已有数据，如果属性不存在则会自动创建
     *
     * @param registerId 用户id
     * @param properties 用户属性信息
     */
    public void profileSetOnce(String registerId, HashMap<String, Object> properties);

    /**
     * 记录操作日志
     *
     * @param registerId 用户id
     * @param eventName 事件名称
     * @param properties 事件属性信息
     */
    public void track(String registerId, String eventName, HashMap<String, Object> properties);

}
