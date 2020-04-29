package com.panda.esportingplus.user.api.params;

import java.io.Serializable;

/**
 * @Auther: shusong.liang
 * @Date: 2020/03/31 17:23
 * @Description: 注册登录基础类
 */
public class RegistLoginBaseParam implements Serializable {

    private static final long serialVersionUID = -106189322824823915L;

    /**
     * 设备id
     */
    private String device_id;

    /**
     * userAgent, 逗号分隔,iOS 10.3,devik,xxxxx
     */
    private String user_agent;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 地区
     */
    private String region;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别：男-1 女-2 其他人妖
     */
    private Integer sex;

    /**
     * 头像
     */
    private String thumbnail;

    /**
     * 平台：1-IOS，2-Android
     */
    private String platform;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 邀请人uid
     */
    private String invitingUid;

    public String getInvitingUid() {
        return invitingUid;
    }

    public void setInvitingUid(String invitingUid) {
        this.invitingUid = invitingUid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
