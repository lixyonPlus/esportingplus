package com.panda.esportingplus.common.lock;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义分布式锁注解 配合 aop + Redisson 实现分布式锁
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RedisLocks.class)
@Documented
public @interface RedisLock {

    /**
     * Redis 格式
     */
    String keyFormate();

    /**
     * 锁失效时间, 毫秒(ms)
     */
    long expireTime() default 2000;

    String uid() default "";

}
