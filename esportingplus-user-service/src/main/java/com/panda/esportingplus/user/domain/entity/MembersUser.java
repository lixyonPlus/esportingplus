package com.panda.esportingplus.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "members_user")
public class MembersUser {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 密码
     */
    private String password;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLogin;

    /**
     * 是否超级管理员
     */
    @Column(name = "is_superuser")
    private Integer isSuperuser;

    /**
     * 用户名(中英文字符以及 @/./+/-/_)
     */
    private String username;

    /**
     * 姓
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * 名
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否员工：是-1，否-0
     */
    @Column(name = "is_staff")
    private Integer isStaff;

    /**
     * 账号情况：正常-1，已删除-2
     */
    @Column(name = "is_active")
    private Integer isActive;

    /**
     * 注册时间
     */
    @Column(name = "date_joined")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateJoined;

    /**
     * 用户唯一标识符
     */
    private String uid;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 头像
     */
    private String thumbnail;

    /**
     * 性别：男-1，女-2，未知-3
     */
    private Integer sex;

    /**
     * 个性描述
     */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 地区
     */
    private String region;

    /**
     * 注册方式: 未统计-， 手机-phone， 微信-WX， QQ-QQ，微博-WB，支付宝-ZFB，机器人-ROBOT，H5微信-H5WX，车队小程序-MP，礼物小程序-GIFTMP，H5QQ-H5QQ
     */
    @Column(name = "register_way")
    private String registerWay;

    /**
     * 鸡牌号
     */
    @Column(name = "chicken_id")
    private String chickenId;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    /**
     * 是否展示圈子
     */
    @Column(name = "show_group")
    private Integer showGroup;

    /**
     * 来源，暴鸡电竞：BJ, 暴暴：BB
     */
    private String source;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login - 最后登录时间
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLogin 最后登录时间
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * 获取是否超级管理员
     *
     * @return is_superuser - 是否超级管理员
     */
    public Integer getIsSuperuser() {
        return isSuperuser;
    }

    /**
     * 设置是否超级管理员
     *
     * @param isSuperuser 是否超级管理员
     */
    public void setIsSuperuser(Integer isSuperuser) {
        this.isSuperuser = isSuperuser;
    }

    /**
     * 获取用户名(中英文字符以及 @/./+/-/_)
     *
     * @return username - 用户名(中英文字符以及 @/./+/-/_)
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名(中英文字符以及 @/./+/-/_)
     *
     * @param username 用户名(中英文字符以及 @/./+/-/_)
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取姓
     *
     * @return first_name - 姓
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 设置姓
     *
     * @param firstName 姓
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /**
     * 获取名
     *
     * @return last_name - 名
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 设置名
     *
     * @param lastName 名
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取是否员工：是-1，否-0
     *
     * @return is_staff - 是否员工：是-1，否-0
     */
    public Integer getIsStaff() {
        return isStaff;
    }

    /**
     * 设置是否员工：是-1，否-0
     *
     * @param isStaff 是否员工：是-1，否-0
     */
    public void setIsStaff(Integer isStaff) {
        this.isStaff = isStaff;
    }

    /**
     * 获取账号情况：正常-1，已删除-2
     *
     * @return is_active - 账号情况：正常-1，已删除-2
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * 设置账号情况：正常-1，已删除-2
     *
     * @param isActive 账号情况：正常-1，已删除-2
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取注册时间
     *
     * @return date_joined - 注册时间
     */
    public Date getDateJoined() {
        return dateJoined;
    }

    /**
     * 设置注册时间
     *
     * @param dateJoined 注册时间
     */
    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    /**
     * 获取用户唯一标识符
     *
     * @return uid - 用户唯一标识符
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置用户唯一标识符
     *
     * @param uid 用户唯一标识符
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取头像
     *
     * @return thumbnail - 头像
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置头像
     *
     * @param thumbnail 头像
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    /**
     * 获取性别：男-1，女-2，未知-3
     *
     * @return sex - 性别：男-1，女-2，未知-3
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别：男-1，女-2，未知-3
     *
     * @param sex 性别：男-1，女-2，未知-3
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取个性描述
     *
     * @return desc - 个性描述
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置个性描述
     *
     * @param desc 个性描述
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * 获取地区
     *
     * @return region - 地区
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置地区
     *
     * @param region 地区
     */
    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    /**
     * 获取注册方式: 未统计-， 手机-phone， 微信-WX， QQ-QQ，微博-WB，支付宝-ZFB，机器人-ROBOT，H5微信-H5WX，车队小程序-MP，礼物小程序-GIFTMP，H5QQ-H5QQ
     *
     * @return register_way - 注册方式: 未统计-， 手机-phone， 微信-WX， QQ-QQ，微博-WB，支付宝-ZFB，机器人-ROBOT，H5微信-H5WX，车队小程序-MP，礼物小程序-GIFTMP，H5QQ-H5QQ
     */
    public String getRegisterWay() {
        return registerWay;
    }

    /**
     * 设置注册方式: 未统计-， 手机-phone， 微信-WX， QQ-QQ，微博-WB，支付宝-ZFB，机器人-ROBOT，H5微信-H5WX，车队小程序-MP，礼物小程序-GIFTMP，H5QQ-H5QQ
     *
     * @param registerWay 注册方式: 未统计-， 手机-phone， 微信-WX， QQ-QQ，微博-WB，支付宝-ZFB，机器人-ROBOT，H5微信-H5WX，车队小程序-MP，礼物小程序-GIFTMP，H5QQ-H5QQ
     */
    public void setRegisterWay(String registerWay) {
        this.registerWay = registerWay == null ? null : registerWay.trim();
    }

    /**
     * 获取鸡牌号
     *
     * @return chicken_id - 鸡牌号
     */
    public String getChickenId() {
        return chickenId;
    }

    /**
     * 设置鸡牌号
     *
     * @param chickenId 鸡牌号
     */
    public void setChickenId(String chickenId) {
        this.chickenId = chickenId == null ? null : chickenId.trim();
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取星座
     *
     * @return constellation - 星座
     */
    public String getConstellation() {
        return constellation;
    }

    /**
     * 设置星座
     *
     * @param constellation 星座
     */
    public void setConstellation(String constellation) {
        this.constellation = constellation == null ? null : constellation.trim();
    }

    /**
     * 获取生日
     *
     * @return birthday - 生日
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取是否展示圈子
     *
     * @return show_group - 是否展示圈子
     */
    public Integer getShowGroup() {
        return showGroup;
    }

    /**
     * 设置是否展示圈子
     *
     * @param showGroup 是否展示圈子
     */
    public void setShowGroup(Integer showGroup) {
        this.showGroup = showGroup;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}