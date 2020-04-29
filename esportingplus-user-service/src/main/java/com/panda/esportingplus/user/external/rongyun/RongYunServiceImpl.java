package com.panda.esportingplus.user.external.rongyun;

import com.panda.esportingplus.common.ResponsePacket;
/*import com.panda.esportingplus.core.api.enums.MessageTypeEnum;
import com.panda.esportingplus.core.api.feign.MsgSendServiceClient;
import com.panda.esportingplus.core.api.feign.MsgUserGroupServiceClient;
import com.panda.esportingplus.core.api.feign.MsgUserServiceClient;
import com.panda.esportingplus.core.api.params.*;*/

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shusogn.liang
 * @Description: 融云服务实现类
 * @Title: RongYunServiceImpl
 */
@Service
public class RongYunServiceImpl implements RongYunService {

    private static final Logger logger = LoggerFactory.getLogger(RongYunServiceImpl.class);

   /* @Autowired
    private MsgUserServiceClient userServiceClient;

    @Autowired
    private MsgUserGroupServiceClient groupServiceClient;*/

    @Override
    public String getToken(String uid, String userName, String thumbnail) {
      /*  MsgUserParam param = new MsgUserParam();
        param.setName(userName);
        param.setUserId(uid);
        param.setPortraitUri(thumbnail);
        ResponsePacket<String> response = userServiceClient.getToken(param);
        if (response.responseSuccess()) {
            return response.getData();
        }*/

        return null;
    }

    @Override
    public boolean updateUser(String uid, String userName, String thumbnail) {
       /* MsgUserParam param = new MsgUserParam();
        param.setName(userName);
        param.setUserId(uid);
        param.setPortraitUri(thumbnail);
        ResponsePacket<Boolean> response = userServiceClient.updateUser(param);
        if (response.responseSuccess()) {
            return response.getData().booleanValue();
        }*/

        return false;
    }

    @Override
    public boolean blockUser(String uid, int minute) {
        /*UserBlockParam blockUserParam = new UserBlockParam();
        blockUserParam.setMinute(minute);
        blockUserParam.setUserIds(Arrays.asList(uid));
        ResponsePacket<Boolean> response = userServiceClient.blockUser(blockUserParam);
        if (response.responseSuccess()) {
            return response.getData().booleanValue();
        }*/

        return false;
    }

    @Override
    public boolean unBlockUser(String uid) {
       /* ResponsePacket<Boolean> response = userServiceClient.unBlockUser(Arrays.asList(uid));
        if (response.responseSuccess()) {
            return response.getData().booleanValue();
        }*/

        return false;
    }

    @Override
    public boolean addUserToBlacklist(String uid, String blackUserId) {
       /* UserBlackParam blackUserParam = new UserBlackParam();
        blackUserParam.setBlackUserIds(Arrays.asList(blackUserId));
        blackUserParam.setUserId(uid);
        ResponsePacket<Boolean> response = userServiceClient.addUserToBlacklist(blackUserParam);
        if (response.responseSuccess()) {
            return response.getData().booleanValue();
        }*/

        return false;
    }

    @Override
    public boolean removeUserFromBlacklist(String uid, String blackUserId) {
        /*UserBlackParam blackUserParam = new UserBlackParam();
        blackUserParam.setUserId(uid);
        blackUserParam.setBlackUserIds(Arrays.asList(blackUserId));
        ResponsePacket<Boolean> resp = userServiceClient.removeUserFromBlacklist(blackUserParam);
        if (resp.responseSuccess()) {
            return resp.getData().booleanValue();
        }*/
        return false;
    }

    @Override
    public String[] queryUsersBlacklist(String uid) {
        /*ResponsePacket<String[]> resp = userServiceClient.queryUsersBlacklist(uid);
        if (resp.responseSuccess()) {
            return resp.getData();
        }*/

        return null;
    }

    @Override
    public boolean createGroup(String uid, String groupId, String groupName) {
       /* GroupJoinParam gp = new GroupJoinParam();
        gp.setGroupId(groupId);
        gp.setGroupName(groupName);
        gp.setMembers(Arrays.asList(uid));
        ResponsePacket<Boolean> rep = groupServiceClient.createGroup(gp);
        if (rep.responseSuccess()) {
            return rep.getData();
        }*/

        return false;
    }

    @Override
    public boolean joinGroup(String uid, String groupId, String groupName) {
       /* GroupJoinParam gp = new GroupJoinParam();
        gp.setGroupId(groupId);
        gp.setGroupName(groupName);
        gp.setMembers(Arrays.asList(uid));
        ResponsePacket<Boolean> rep = groupServiceClient.joinGroup(gp);
        if (rep.responseSuccess()) {
            return rep.getData();
        }*/

        return false;
    }

    @Override
    public boolean leaveGroup(String uid, String groupId) {
        /*GroupQuitParam gp = new GroupQuitParam();
        gp.setGroupId(groupId);
        gp.setMemebers(Arrays.asList(uid));
        ResponsePacket<Boolean> resp = groupServiceClient.leaveGroup(gp);
        if (resp.responseSuccess()) {
            return resp.getData();
        }*/
        return false;
    }

}
