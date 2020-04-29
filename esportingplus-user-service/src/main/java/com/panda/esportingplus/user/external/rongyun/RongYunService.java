package com.panda.esportingplus.user.external.rongyun;

/**
 * @Description: 融云服务
 * @Author: yangshidong
 * @Date: 2018年9月11日 15:20
 */
public interface RongYunService {

    /**
     * 获取融云token
     *
     * @param uid uid
     * @param userName userName
     * @param thumbnail 头像地址
     */
    public String getToken(String uid, String userName, String thumbnail);

    /**
     * 刷新融云用户信息
     *
     * @param uid uid
     * @param userName userName
     * @param thumbnail 头像地址
     */
    public boolean updateUser(String uid, String userName, String thumbnail);

    /**
     * 封禁用户
     *
     * @param uid uid
     * @param minute 封禁时长，单位为分钟，最大值为43200分钟
     */
    public boolean blockUser(String uid, int minute);

    /**
     * 解除用户封禁
     *
     * @param uid uid
     */
    public boolean unBlockUser(String uid);

    /**
     * 添加用户到黑名单
     *
     * @param uid uid
     * @param blackUserId 被加黑的用户Id
     */
    public boolean addUserToBlacklist(String uid, String blackUserId);

    /**
     * 移除黑名单中用户
     *
     * @param uid uid
     * @param blackUserId 被加黑的用户Id
     */
    public boolean removeUserFromBlacklist(String uid, String blackUserId);

    /**
     * 获取某用户黑名单列表
     *
     * @param uid uid
     */
    public String[] queryUsersBlacklist(String uid);

    /**
     * 创建群组
     *
     * @param uid 创建群的用户uid
     * @param groupId 群组ID
     * @param groupName 群组ID对应的名称
     */
    public boolean createGroup(String uid, String groupId, String groupName);

    /**
     * 加入群组
     *
     * @param uid 要加入群的用户uid
     * @param groupId 群组ID
     * @param groupName 群组ID对应的名称
     */
    public boolean joinGroup(String uid, String groupId, String groupName);

    /**
     * 退出群组
     *
     * @param uid 要退出群的用户uid
     * @param groupId 要退出的群组ID
     */
    public boolean leaveGroup(String uid, String groupId);

}
