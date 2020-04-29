package com.panda.esportingplus.common.lock;

import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.panda.esportingplus.common.constant.RedisKey;
import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.exception.BusinessException;
import com.panda.esportingplus.common.tools.AspectUtil;
import java.lang.reflect.Method;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;


/**
 * 防止用户多次点击重复提交请求
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
@Aspect
@Component
@Slf4j
public class PreventRepeatOperationAspect implements Ordered {

    private CacheManager cacheManager = CacheManagerFactory.create();

    /**
     * 切入点为加了 @PreventResubmit 注解
     */
    @Pointcut("@annotation(com.panda.esportingplus.common.lock.PreventRepeatOperation)")
    public void preventRepeatOperationCut() {

    }

    @Around("preventRepeatOperationCut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getPointMethod(joinPoint);
        PreventRepeatOperation pre = method.getAnnotation(PreventRepeatOperation.class);
        if (pre != null) {
            //有token说明是外部调用：使用uid+事件做Key标记
            String keySign = AspectUtil.parseRepeatLockKey(joinPoint, pre);
            //操作的事件枚举
            String eventCode = pre.event().getCode();
            String eventDesc = pre.event().getDesc();
            String methodName = method.getName();
            String proKey = String.format(RedisKey.PREVENT_REPEAT_OPERATION, keySign, eventCode);

            Long ttl = cacheManager.ttl(proKey);
            // 标记不存在, 或永不过期, 放置标记并放行
            if (ttl == -2 || ttl == -1) {
                cacheManager.set(proKey, keySign, pre.expireTime());
                return joinPoint.proceed();
            }

            //此操作不处理直接忽略，否则说明此操作需要阻断(抛出异常)
            if(pre.ignoreHandle()){
                log.warn(">> [{}]在[{}]秒内重复操作了[{}:{}]!，方法名:{}，忽略此次操作,TTL:{}"
                        , keySign, pre.expireTime(), eventCode,eventDesc,methodName,ttl);
            }else{
                log.warn(">> [{}]在[{}]秒内重复操作了[{}:{}]!，方法名:{},阻断此次操作,TTL:{}"
                        , keySign, pre.expireTime(), eventCode,eventDesc,methodName,ttl);
                throw new BusinessException(BizExceptionEnum.TEAM_OPERATE_TOO_FAST);
            }
        }
        return null;
    }

    //处理完后清理Lock
    @AfterReturning("preventRepeatOperationCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        Method method = getPointMethod(joinPoint);
        PreventRepeatOperation pre = method.getAnnotation(PreventRepeatOperation.class);
        if (pre != null) {
            //有token说明是外部调用：使用uid、orderSequence+事件做Key标记
            String keySign = AspectUtil.parseRepeatLockKey(joinPoint, pre);
            //操作的事件枚举
            String event = pre.event().getCode();
            String eventDesc = pre.event().getDesc();
            String proKey = String.format(RedisKey.PREVENT_REPEAT_OPERATION, keySign, event);
            log.debug("事件[{}:{}]处理完毕，清除Lock标记，方法:{}", proKey, eventDesc, method.getName());
            cacheManager.del(proKey);

        }
    }

    /**
     * 获取切入点的方法
     */
    private Method getPointMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }

    @Override
    public int getOrder() {
        return 1;
    }

}