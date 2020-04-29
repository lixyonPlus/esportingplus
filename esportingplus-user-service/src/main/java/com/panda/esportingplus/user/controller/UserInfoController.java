package com.panda.esportingplus.user.controller;

import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.kaihei.commons.cache.utils.JsonsUtils;

import com.panda.esportingplus.common.ResponsePacket;
import com.panda.esportingplus.common.constant.RedisKey;
import com.panda.esportingplus.common.tools.StringUtils;
import com.panda.esportingplus.common.tools.TokenParseUtils;
import com.panda.esportingplus.common.web.UserSessionContext;
import com.panda.esportingplus.user.api.feign.UserInfoServiceClient;
import com.panda.esportingplus.user.api.params.UserUpdateParams;


import com.panda.esportingplus.user.api.vo.UpdateUserAvatarResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;


/**
 * @Auther: shusong.liang
 * @Date: 2020-03-26 15:10
 * @Description:
 */
@RestController
@RequestMapping("/userinfo")
@Api(tags = {"用户基础服务接口"})
public class UserInfoController implements UserInfoServiceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    //@Autowired
    //private MembersUserService membersUserService;

   /* @Autowired
    private UserInfoESManager userInfoESManager;*/

    protected static CacheManager cacheManager = CacheManagerFactory.create();

    @ApiOperation(value = "修改头像")
    @Override
    public ResponsePacket<UpdateUserAvatarResultVo> updateAvatar(
            @RequestHeader("Authorization") String authorization,
            @RequestBody UserUpdateParams params) {
        UserSessionContext userSessionContext = UserSessionContext.getUser();
        int userId = userSessionContext.getId();
        String uid = userSessionContext.getUid();
        String avatar = params.getAvatar();
        UpdateUserAvatarResultVo result = null;//membersUserService.updateAvatar(uid, avatar);
        //更新上下文
        userSessionContext.setAvatar(avatar);
        setUserSessionContext(authorization, userSessionContext);
        return ResponsePacket.onSuccess(result);
    }

    @ApiOperation(value = "修改用户昵称")
    @Override
    public ResponsePacket updateUsername(@RequestHeader("Authorization") String authorization,
            @RequestBody UserUpdateParams params) {
        UserSessionContext userSessionContext = UserSessionContext.getUser();
        String uid = userSessionContext.getUid();
        String username = params.getUsername();
        int result = 0;//membersUserService.updateUsername(uid, username);
        //更新上下文
        userSessionContext.setUsername(username);
        setUserSessionContext(authorization, userSessionContext);
        return ResponsePacket.onSuccess();
    }

    private void setUserSessionContext(String authorization, UserSessionContext user) {
        if (StringUtils.isNoneBlank(authorization)) {
            String token = TokenParseUtils.parseToken(authorization);
            if (StringUtils.isNoneBlank(token)) {
                cacheManager
                        .set(RedisKey.UID_ACCESS_TOKEN + token, JsonsUtils.toJson(user));
            }
        }
    }
}
