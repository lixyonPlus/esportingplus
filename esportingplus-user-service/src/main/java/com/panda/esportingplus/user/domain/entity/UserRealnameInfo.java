package com.panda.esportingplus.user.domain.entity;

import com.panda.esportingplus.user.api.enums.UserRealNameVerifyResultEnum;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user_realname_info")
public class UserRealnameInfo {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * uid
     */
    private String uid;

    /**
     * 鸡牌号
     */
    @Column(name = "chicken_id")
    private String chickenId;

    /**
     * 证件类型(1:身份证)
     */
    @Column(name = "idcard_type")
    private Short idcardType;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 性别(1:男 2:女)
     */
    private Short gender;

    /**
     * 证件号码
     */
    @Column(name = "idcard_number")
    private String idcardNumber;

    /**
     * 证件正面照
     */
    @Column(name = "idcard_front_picture")
    private String idcardFrontPicture;

    /**
     * 人脸证件验证照
     */
    @Column(name = "face_idcard_verify_picture")
    private String faceIdcardVerifyPicture;

    /**
     * 实名认证类型(1：后台认证；2：faceid认证)
     */
    @Column(name = "verify_type")
    private Short verifyType;

    /**
     * {@link UserRealNameVerifyResultEnum}
     * 审核状态(0:未审核 1：审核通过； 2：审核不通过)
     */
    @Column(name = "verify_status")
    private Short verifyStatus;

    /**
     * 审核备注
     */
    @Column(name = "verify_remark")
    private String verifyRemark;

    /**
     * 是否黑名单
     */
    @Column(name = "is_blacklist")
    private Boolean isBlacklist;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 后台管理系统更新人员id
     */
    @Column(name = "update_user_id")
    private Integer updateUserId;

    /**
     * 后台管理系统更新人员名称
     */
    @Column(name = "update_user_name")
    private String updateUserName;

    /**
     * 后台管理系统审核人员id
     */
    @Column(name = "verify_user_id")
    private Integer verifyUserId;

    /**
     * 后台管理系统审核人员名称
     */
    @Column(name = "verify_user_name")
    private String verifyUserName;

    /**
     * 审核时间
     */
    @Column(name = "verify_datetime")
    private Date verifyDatetime;

    /**
     * 删除时间
     */
    @Column(name = "delete_datetime")
    private Date deleteDatetime;

    /**
     * 创建时间
     */
    @Column(name = "create_datetime")
    private Date createDatetime;

    /**
     * 更新时间
     */
    @Column(name = "update_datetime")
    private Date updateDatetime;

    public UserRealnameInfo(Integer id, String uid, String chickenId, Short idcardType, String realname, Short gender, String idcardNumber, String idcardFrontPicture, String faceIdcardVerifyPicture, Short verifyType, Short verifyStatus, String verifyRemark, Boolean isBlacklist, Boolean isDeleted, Integer updateUserId, String updateUserName, Integer verifyUserId, String verifyUserName, Date verifyDatetime, Date deleteDatetime, Date createDatetime, Date updateDatetime) {
        this.id = id;
        this.uid = uid;
        this.chickenId = chickenId;
        this.idcardType = idcardType;
        this.realname = realname;
        this.gender = gender;
        this.idcardNumber = idcardNumber;
        this.idcardFrontPicture = idcardFrontPicture;
        this.faceIdcardVerifyPicture = faceIdcardVerifyPicture;
        this.verifyType = verifyType;
        this.verifyStatus = verifyStatus;
        this.verifyRemark = verifyRemark;
        this.isBlacklist = isBlacklist;
        this.isDeleted = isDeleted;
        this.updateUserId = updateUserId;
        this.updateUserName = updateUserName;
        this.verifyUserId = verifyUserId;
        this.verifyUserName = verifyUserName;
        this.verifyDatetime = verifyDatetime;
        this.deleteDatetime = deleteDatetime;
        this.createDatetime = createDatetime;
        this.updateDatetime = updateDatetime;
    }

    public UserRealnameInfo() {
        super();
    }

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
     * 获取uid
     *
     * @return uid - uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置uid
     *
     * @param uid uid
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
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
     * 获取证件类型(1:身份证)
     *
     * @return idcard_type - 证件类型(1:身份证)
     */
    public Short getIdcardType() {
        return idcardType;
    }

    /**
     * 设置证件类型(1:身份证)
     *
     * @param idcardType 证件类型(1:身份证)
     */
    public void setIdcardType(Short idcardType) {
        this.idcardType = idcardType;
    }

    /**
     * 获取真实姓名
     *
     * @return realname - 真实姓名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置真实姓名
     *
     * @param realname 真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * 获取性别(1:男 2:女)
     *
     * @return gender - 性别(1:男 2:女)
     */
    public Short getGender() {
        return gender;
    }

    /**
     * 设置性别(1:男 2:女)
     *
     * @param gender 性别(1:男 2:女)
     */
    public void setGender(Short gender) {
        this.gender = gender;
    }

    /**
     * 获取证件号码
     *
     * @return idcard_number - 证件号码
     */
    public String getIdcardNumber() {
        return idcardNumber;
    }

    /**
     * 设置证件号码
     *
     * @param idcardNumber 证件号码
     */
    public void setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber == null ? null : idcardNumber.trim();
    }

    /**
     * 获取证件正面照
     *
     * @return idcard_front_picture - 证件正面照
     */
    public String getIdcardFrontPicture() {
        return idcardFrontPicture;
    }

    /**
     * 设置证件正面照
     *
     * @param idcardFrontPicture 证件正面照
     */
    public void setIdcardFrontPicture(String idcardFrontPicture) {
        this.idcardFrontPicture = idcardFrontPicture == null ? null : idcardFrontPicture.trim();
    }

    /**
     * 获取人脸证件验证照
     *
     * @return face_idcard_verify_picture - 人脸证件验证照
     */
    public String getFaceIdcardVerifyPicture() {
        return faceIdcardVerifyPicture;
    }

    /**
     * 设置人脸证件验证照
     *
     * @param faceIdcardVerifyPicture 人脸证件验证照
     */
    public void setFaceIdcardVerifyPicture(String faceIdcardVerifyPicture) {
        this.faceIdcardVerifyPicture = faceIdcardVerifyPicture == null ? null : faceIdcardVerifyPicture.trim();
    }

    /**
     * 获取实名认证类型(1：后台认证；2：faceid认证)
     *
     * @return verify_type - 实名认证类型(1：后台认证；2：faceid认证)
     */
    public Short getVerifyType() {
        return verifyType;
    }

    /**
     * 设置实名认证类型(1：后台认证；2：faceid认证)
     *
     * @param verifyType 实名认证类型(1：后台认证；2：faceid认证)
     */
    public void setVerifyType(Short verifyType) {
        this.verifyType = verifyType;
    }

    /**
     * 获取审核状态(0:未审核 1：审核通过； 2：审核不通过)
     *
     * @return verify_status - 审核状态(0:未审核 1：审核通过； 2：审核不通过)
     */
    public Short getVerifyStatus() {
        return verifyStatus;
    }

    /**
     * 设置审核状态(0:未审核 1：审核通过； 2：审核不通过)
     *
     * @param verifyStatus 审核状态(0:未审核 1：审核通过； 2：审核不通过)
     */
    public void setVerifyStatus(Short verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    /**
     * 获取审核备注
     *
     * @return verify_remark - 审核备注
     */
    public String getVerifyRemark() {
        return verifyRemark;
    }

    /**
     * 设置审核备注
     *
     * @param verifyRemark 审核备注
     */
    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark == null ? null : verifyRemark.trim();
    }

    /**
     * 获取是否黑名单
     *
     * @return is_blacklist - 是否黑名单
     */
    public Boolean getIsBlacklist() {
        return isBlacklist;
    }

    /**
     * 设置是否黑名单
     *
     * @param isBlacklist 是否黑名单
     */
    public void setIsBlacklist(Boolean isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    /**
     * 获取是否删除
     *
     * @return is_deleted - 是否删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted 是否删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取后台管理系统更新人员id
     *
     * @return update_user_id - 后台管理系统更新人员id
     */
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置后台管理系统更新人员id
     *
     * @param updateUserId 后台管理系统更新人员id
     */
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取后台管理系统更新人员名称
     *
     * @return update_user_name - 后台管理系统更新人员名称
     */
    public String getUpdateUserName() {
        return updateUserName;
    }

    /**
     * 设置后台管理系统更新人员名称
     *
     * @param updateUserName 后台管理系统更新人员名称
     */
    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }

    /**
     * 获取后台管理系统审核人员id
     *
     * @return verify_user_id - 后台管理系统审核人员id
     */
    public Integer getVerifyUserId() {
        return verifyUserId;
    }

    /**
     * 设置后台管理系统审核人员id
     *
     * @param verifyUserId 后台管理系统审核人员id
     */
    public void setVerifyUserId(Integer verifyUserId) {
        this.verifyUserId = verifyUserId;
    }

    /**
     * 获取后台管理系统审核人员名称
     *
     * @return verify_user_name - 后台管理系统审核人员名称
     */
    public String getVerifyUserName() {
        return verifyUserName;
    }

    /**
     * 设置后台管理系统审核人员名称
     *
     * @param verifyUserName 后台管理系统审核人员名称
     */
    public void setVerifyUserName(String verifyUserName) {
        this.verifyUserName = verifyUserName == null ? null : verifyUserName.trim();
    }

    /**
     * 获取审核时间
     *
     * @return verify_datetime - 审核时间
     */
    public Date getVerifyDatetime() {
        return verifyDatetime;
    }

    /**
     * 设置审核时间
     *
     * @param verifyDatetime 审核时间
     */
    public void setVerifyDatetime(Date verifyDatetime) {
        this.verifyDatetime = verifyDatetime;
    }

    /**
     * 获取删除时间
     *
     * @return delete_datetime - 删除时间
     */
    public Date getDeleteDatetime() {
        return deleteDatetime;
    }

    /**
     * 设置删除时间
     *
     * @param deleteDatetime 删除时间
     */
    public void setDeleteDatetime(Date deleteDatetime) {
        this.deleteDatetime = deleteDatetime;
    }

    /**
     * 获取创建时间
     *
     * @return create_datetime - 创建时间
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * 设置创建时间
     *
     * @param createDatetime 创建时间
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * 获取更新时间
     *
     * @return update_datetime - 更新时间
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updateDatetime 更新时间
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}