package com.panda.esportingplus.user.api.params;

import java.io.Serializable;

/**
 * 修改用户信息参数
 *
 * @author shusong.liang
 * @date 2020/03/31 17:23
 */
public class UserUpdateParams implements Serializable {

    private static final long serialVersionUID = 5902683012521464619L;

    /**
     * 头像OSS地址
     */
    private String avatar;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 地区
     */
    private String region;

    /**
     * 个人说明
     */
    private String desc;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 圈子展示状态
     */
    private Boolean showgroup;

    /**
     * 用户uid
     */
    private String uid;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Boolean isShowgroup() {
        return showgroup;
    }

    public void setShowgroup(Boolean showgroup) {
        this.showgroup = showgroup;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
