package com.panda.esportingplus.common.tools;

import com.google.common.collect.Maps;
import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.exception.BusinessException;
import com.panda.esportingplus.common.lock.PreventRepeatOperation;
import com.panda.esportingplus.common.lock.RedisLock;
import com.panda.esportingplus.common.web.UserSessionContext;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 切面工具类
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 * @updatetor
 */
public class AspectUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectUtil.class);

    /**
     * 解析分布式锁key
     */
    public static String parseRedisLockKey(Map<String, Object> map, RedisLock redisLock) {
        map = new HashMap<>(map);
        String key = redisLock.keyFormate();
        String[] split = key.split(":");

        for (String s : split) {
            if (s.startsWith("$.")) {
                String placeHolerName = s.replaceAll("\\$\\.", "");
                //取的不是uid
                if (!placeHolerName.equals("uid")) {
                    Object obj = ObjectTools.getParamByPath(placeHolerName, map);
                    if (obj == null) {
                        key = null;
                    } else {
                        key = key.replaceFirst("\\$\\." + placeHolerName, obj.toString());
                    }
                } else {
                    String uid = null;
                    if (!redisLock.uid().trim().equals("")) {
                        Object obj = ObjectTools.getParamByPath(redisLock.uid(), map);
                        uid = (String) obj;
                    } else {
                        Object uidObj = ObjectTools.getParamByPath("uid", map);
                        if (uidObj == null) {
                            try {
                                uid = UserSessionContext.getUser().getUid();
                            } catch (Exception e) {
                                //ignored
                            }
                        } else {
                            uid = (String) uidObj;
                        }
                    }
                    if (uid == null) {
                        key = null;
                    } else {
                        key = key.replaceFirst("\\$\\." + placeHolerName, uid);
                    }
                }
            }
        }
        return key;
    }

    /**
     * 解析防重锁Ket
     */
    public static String parseRepeatLockKey(JoinPoint pjp,PreventRepeatOperation repeatOperation) {
        Map<String, Object> map = getParamMap(pjp);
        String key = repeatOperation.keyFormate();
        if (key.startsWith("$.")) {
            String placeHolerName = key.replaceAll("\\$\\.", "");
            key = key.replaceFirst("\\$\\." + placeHolerName,
                    String.valueOf(ObjectTools.getParamByPath(placeHolerName, map)));
        }else{
            LOGGER.error("防重锁参数有误:{},举个例子:{}",key,"$.vo.uid");
            throw new BusinessException(BizExceptionEnum.REDIS_LOCK_PARAM_ERR);
        }
        return key;
    }

    /**
     * 反射出入参
     */
    public static Map<String, Object> getParamMap(JoinPoint pjp) {
        //获取方法上的注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        HashMap<String, Object> map = Maps.newHashMap();

        String[] parameterNames = signature.getParameterNames();
        Object[] args = pjp.getArgs();
        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], args[i]);
        }

        return map;
    }
}
