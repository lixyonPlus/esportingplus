package com.panda.esportingplus.common.tools;

import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.exception.BusinessException;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * token 解析工具类
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 * @version 1.0
 */
public class TokenParseUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(TokenParseUtils.class);

    private final static String PYTHON_TOKEN_SEPARATOR = ".";
    private final static String PYTHON_TOKEN_REGEX = "\\.";
    protected static CacheManager cacheManager = CacheManagerFactory.create();

    public static String parseToken(String pythonToken) {
        String[] split = pythonToken.split("\\s+");
        if (split.length != 2){
            LOGGER.error("token 格式不正确 token = {}", pythonToken);
            throw new BusinessException(BizExceptionEnum.INVALID_PY_TOKEN);
        }

        String token = split[1];
        if (StringUtils.isNotBlank(token) && token.contains(PYTHON_TOKEN_SEPARATOR)) {
            String[] tokenInfos = token.split(PYTHON_TOKEN_REGEX);
            return tokenInfos[1];
        }
        return token;
    }

    public static String parseUid(String pythonToken) {
        String[] split = pythonToken.split("\\s+");
        if (split.length != 2){
            LOGGER.error("token 格式不正确 token = {}", pythonToken);
            throw new BusinessException(BizExceptionEnum.INVALID_PY_TOKEN);
        }

        String token = split[1];
        if (StringUtils.isNotBlank(token) && token.contains(PYTHON_TOKEN_SEPARATOR)) {
            String[] tokenInfos = token.split(PYTHON_TOKEN_REGEX);
            return tokenInfos[0];
        }

        String userVo = cacheManager.get("user:" + token ,  String.class);
        if (userVo != null){
            HashMap hashMap = JacksonUtils.toBean(userVo, HashMap.class);
            return hashMap.get("uid").toString();
        }

        return null;
    }

    private static Pair<String, String> parsePythonToken(String pythonToken) {
        try {
            if (StringUtils.isNotBlank(pythonToken) && pythonToken
                    .contains(PYTHON_TOKEN_SEPARATOR)) {
                String[] tokenInfos = pythonToken.split(PYTHON_TOKEN_REGEX);
                return MutablePair.of(tokenInfos[0], tokenInfos[1]);
            }
        } catch (Exception e) {
            LOGGER.error("token 解析失败，此token非法:{}", pythonToken);
        }

        return null;
    }

}
