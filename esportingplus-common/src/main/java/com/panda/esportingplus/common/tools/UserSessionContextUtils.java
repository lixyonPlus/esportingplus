package com.panda.esportingplus.common.tools;

import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.kaihei.commons.cache.utils.JsonsUtils;
import com.panda.esportingplus.common.ValidateAssert;
import com.panda.esportingplus.common.constant.RedisKey;
import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.web.UserSessionContext;


/**
 * 用户信息工具类
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
public final class UserSessionContextUtils {

    private static CacheManager cacheManager = CacheManagerFactory.create();

    /**
     * 修改 redis 中的用户头像
     * @param token Authentication token
     * @param avatar 用户头像链接
     */
    public static void updateUserAvatar(String token, String avatar) {
        ValidateAssert.allNotNull(BizExceptionEnum.INVALID_TOKEN, token);
        ValidateAssert.allNotNull(BizExceptionEnum.USER_AVATAR_NOT_EXIST, avatar);
        UserSessionContext user = UserSessionContext.getUser();
        user.setAvatar(avatar);
        cacheManager.set(RedisKey.UID_ACCESS_TOKEN + token,JsonsUtils.toJson(user));
    }

}

