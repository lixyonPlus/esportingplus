package com.panda.esportingplus.user.data.repository;

import com.panda.esportingplus.common.data.mapper.CommonRepository;
import com.panda.esportingplus.user.domain.entity.UserRealnameInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRealnameInfoRepository extends CommonRepository<UserRealnameInfo> {
    /**
     * 根据uid查询用户实名信息
     *
     * @param uid
     * @return {@link UserRealnameInfo}
     */
    UserRealnameInfo getUserRealnameInfoByUid(@Param("uid") String uid);

    /**
     * 根据uid查询用户实名信息最新一条信息，给审核进度条调用
     *
     * @param uid
     * @return {@link UserRealnameInfo}
     */
    UserRealnameInfo getLastUserRealnameInfoByUid(@Param("uid") String uid);

    /**
     * 根据uid查询用户实名认证信息列表
     */
    List<UserRealnameInfo> getUserRealnameInfoRecordByUid(@Param("uid") String uid);

    /**
     * 根据证件号码查询用户实名信息
     *
     * @param idcardNumber
     * @return {@link UserRealnameInfo}
     */
    UserRealnameInfo getUserRealnameInfoByIdcardNumber(@Param("idcardNumber") String idcardNumber);

    /**
     * 查询审核列表以及审核管理列表
     *
     * @param queryParameterType  参数类型
     * @param queryParameterValue 参数值
     * @param verifyStatus        审核状态
     * @param isBlacklist         是否黑名单
     * @param verifyType          审核类型
     * @return
     */
    List<UserRealnameInfo> getUserRealnameList(@Param("queryParameterType") Short queryParameterType,
                                               @Param("queryParameterValue") String queryParameterValue,
                                               @Param("verifyStatus") Short verifyStatus,
                                               @Param("isBlacklist") Short isBlacklist,
                                               @Param("verifyType") Short verifyType);

    /**
     * 更新审核状态
     *
     * @param id             id
     * @param verifyStatus   审核状态
     * @param verifyRemark   审核备注
     * @param verifyUserId   审核人id
     * @param verifyUserName 审核人名称
     * @return
     */
    int updateVerifyStatusById(@Param("id") Integer id,
                               @Param("verifyStatus") Short verifyStatus,
                               @Param("verifyRemark") String verifyRemark,
                               @Param("verifyUserId") Integer verifyUserId,
                               @Param("verifyUserName") String verifyUserName);

    /**
     * 更新是否黑名单状态
     *
     * @param id             id
     * @param isBlackList    是否拉黑
     * @param updateUserId   更新人id
     * @param updateUserName 更新人名称
     * @return
     */
    int updateIsBlackListById(@Param("id") Integer id,
                              @Param("isBlackList") Boolean isBlackList,
                              @Param("updateUserId") Integer updateUserId,
                              @Param("updateUserName") String updateUserName);

    /**
     * 解绑实名信息
     *
     * @param id             id
     * @param updateUserId   更新人id
     * @param updateUserName 更新人名称
     * @return
     */
    int unBindRealNameInfoById(@Param("id") Integer id,
                               @Param("updateUserId") Integer updateUserId,
                               @Param("updateUserName") String updateUserName);
}