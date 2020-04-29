package com.panda.esportingplus.common.lock;

import com.google.common.collect.Maps;
import com.kaihei.commons.cache.api.spi.common.CacheManager;
import com.kaihei.commons.cache.api.spi.common.CacheManagerFactory;
import com.panda.esportingplus.common.enums.BizExceptionEnum;
import com.panda.esportingplus.common.exception.BusinessException;
import com.panda.esportingplus.common.tools.AspectUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class RedisLockInterceptor implements Ordered {

    private CacheManager cacheManager = CacheManagerFactory.create();

    @Around("@annotation(redisLock)")
    public Object around(ProceedingJoinPoint pjp, RedisLock redisLock) throws Throwable {
        return around(pjp, new RedisLocks() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return RedisLocks.class;
            }

            @Override
            public RedisLock[] value() {
                return new RedisLock[]{redisLock};
            }
        });
    }

    @Around("@annotation(redisLocks)")
    public Object around(ProceedingJoinPoint pjp, RedisLocks redisLocks) throws Throwable {
        //获取方法上的注解
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        Map<String, Object> map = AspectUtil.getParamMap(pjp);

        RedisLock[] locks = redisLocks.value();
        Map<String, RedisLock> tm = Maps.newTreeMap();
        Arrays.stream(locks).forEach(lock -> {
            String key = AspectUtil.parseRedisLockKey(map, lock);
            if (key == null) {
                return;
            }
            tm.put(key, lock);
        });

        ArrayList<RLock> rLocks = new ArrayList<>();
        RedissonClient redissonClient = cacheManager.redissonClient();
        long startLock = System.currentTimeMillis();
        long tid = Thread.currentThread().getId();

        tm.forEach((k, l) -> {
            long start = System.currentTimeMillis();
            RLock lock = redissonClient.getLock(k);

            long expireTime = l.expireTime();
            boolean lockSuccess;
            try {
                lockSuccess = lock.tryLock(expireTime, expireTime, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                log.error("try lock interrupted tid=[{}] key=[{}] cost=[{}] e={}", tid, k, System.currentTimeMillis() - start, e);
                releaseLock(rLocks, tid);
                throw new BusinessException(BizExceptionEnum.GATEWAY_FALLBACK);
            }

            if (!lockSuccess) {
                log.warn("try lock timeout tid=[{}] key=[{}] waitTime=[{}] cost=[{}]", tid, k, expireTime, System.currentTimeMillis() - start);
                releaseLock(rLocks, tid);
                throw new BusinessException(BizExceptionEnum.GATEWAY_FALLBACK);
            }
            log.debug("try lock success tid=[{}] key=[{}] expireTime=[{}] cost=[{}]", tid, k, expireTime, System.currentTimeMillis() - start);
            rLocks.add(lock);
        });

        log.debug("all lock success tid=[{}] keys={} cost=[{}]", tid, tm.keySet(), System.currentTimeMillis() - startLock);

        long startProceed = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            log.debug("proceed tid=[{}] method=[{}] cost=[{}]", tid, method.getName(), System.currentTimeMillis() - startProceed);
            releaseLock(rLocks, tid);
        }
    }

    private void releaseLock(ArrayList<RLock> rLocks, long tid) {
        rLocks.parallelStream()
                .forEach(l -> {
                    if (l == null) {
                        log.warn("release lock is null tid=[{}]", tid);
                        return;
                    }
                    if (!l.isLocked()) {
                        log.warn("release lock is unlocked tid=[{}]", tid);
                        return;
                    }
                    l.unlockAsync(tid);
                });
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
