package com.panda.esportingplus.common.lock;



import com.panda.esportingplus.common.enums.RepeatOperationEventLockEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 结合 redis 实现防止用户重复操作
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreventRepeatOperation {

    /**
     * key 失效时间, 秒(s)
     */
    int expireTime() default 2;

    /**
     * 操作事件，如果有新事件需要防重，请添加新枚举内容
     */
    RepeatOperationEventLockEnum event();

    //锁格式内容: $.xxVO.uid/$.orderSequence/$xxVO.orderSequence
    String keyFormate() default "";

    /**
     * 是否忽略业务逻辑处理，true:忽略 false:抛异常
     *
     */
    boolean ignoreHandle() default false;

}
