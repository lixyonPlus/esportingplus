package com.kaihei.esportingplus.gateway.server.service.impl;

import static com.kaihei.esportingplus.common.constant.RedisKey.NEED_AUTH_URLS;

import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.kaihei.esportingplus.common.ResponsePacket;
import com.kaihei.esportingplus.common.enums.BizExceptionEnum;
import com.kaihei.esportingplus.common.tools.StringUtils;
import com.kaihei.esportingplus.gateway.server.service.PermissionService;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

/**
 * 描述
 *
 * @author Orochi-Yzh
 * @dateTime 2018/6/22 17:04
 * @updatetor
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${base.url}")
    private String baseUrl;

    private CacheManager cacheManager = CacheManagerFactory.create();
    /**
     * 可以做URLs匹配，规则如下
     *
     * ？匹配一个字符 *匹配0个或多个字符 **匹配0个或多个目录
     * 用例如下 <p>https://www.cnblogs.com/zhangxiaoguang/p/5855113.html</p>
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public ResponsePacket hasPermission(String token, String resUrl) {

//        AuthRedisVO authRedisVO = cacheManager.get(RedisKey.AUTH + token,
//                AuthRedisVO.class);
        //TODO 默认开放全部权限，直接load redis,减少反序列化加快响应时间
        Set<String> auth_urls = cacheManager.sMembers(NEED_AUTH_URLS);
        //TOKEN 不存在
        if (CollectionUtils.isEmpty(auth_urls)) {
            LOGGER.warn(BizExceptionEnum.PERMISSIONS_ACCESS_DENIED.getErrMsg());
            return ResponsePacket.onError(BizExceptionEnum.PERMISSIONS_ACCESS_DENIED);
        }

//        if (!"anonymousUser".equals(principal.toString())) {
//            OAuth2AccessToken readAccessToken = deserializeAccessToken(cacheManager.get(serializeKey(RedisKey.ACCESS + token)));
//            if (null == readAccessToken || readAccessToken.isExpired()) {
//                LOGGER.warn(BizExceptionEnum.TOKEN_EXPIRED.getErrMsg());
//                return ResponsePacket.onError(BizExceptionEnum.TOKEN_EXPIRED);
//            }
//        }

        boolean hasPermission = false;
        //去除网关url前缀
        resUrl = resUrl.replace(baseUrl,"");
        String tempUrl = "/api/" + resUrl.split("/")[2];
        resUrl = resUrl.replace(tempUrl, "");

        for (String authority : auth_urls) {
            if (StringUtils.isNoneBlank(authority)
                    && antPathMatcher.match(authority, resUrl)) {
                hasPermission = true;
                break;
            }
        }

        if (!hasPermission) {
            LOGGER.warn(BizExceptionEnum.PERMISSIONS_ACCESS_DENIED.getErrMsg());
            return ResponsePacket.onError(BizExceptionEnum.PERMISSIONS_ACCESS_DENIED);
        }
        return ResponsePacket.onSuccess(null);

    }

}
