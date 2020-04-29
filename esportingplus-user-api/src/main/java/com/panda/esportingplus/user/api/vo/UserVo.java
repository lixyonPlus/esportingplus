package com.panda.esportingplus.user.api.vo;

import java.util.Date;
/**
 * @Auther: shusong.liang
 * @Date: 2020-03-27 15:28
 * @Description:
 */
public class UserVo {
    /**
     * 用户uid
     */
    protected String uid;

    /**
     * 用户userId
     */
    private int userId;

    /**
     * 地区
     */
    protected String region;

    /**
     * 星座
     */
    protected String constellation;

    /**
     * 年龄
     */
    protected String age;

    /**
     * 个人简介
     */
    protected String desc;

    /**
     * 生日
     */
    protected String birthday;

    /**
     * 性别
     */
    protected int sex;

    /**
     * 用户名
     */
    protected String name;

    /**
     * 头像
     */
    protected String thumbnail;

    /**
     * 用户牌号
     */
    protected String chicken_id;

    /**
     * 融云的IMID
     */
    protected String rcUserid;

    /**
     * 加入时间
     */
    private Date dateJoined;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getChicken_id() {
        return chicken_id;
    }

    public void setChicken_id(String chicken_id) {
        this.chicken_id = chicken_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRcUserid() {
        return rcUserid;
    }

    public void setRcUserid(String rcUserid) {
        this.rcUserid = rcUserid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }
}
