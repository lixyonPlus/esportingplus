package com.panda.esportingplus.common.lock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 梁树松
 *
 * Redis多重锁 -> 只有一把锁是关不住我的
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLocks {

    RedisLock[] value();
}
