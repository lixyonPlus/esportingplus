package com.panda.esportingplus.common.sensors.aop;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@Description: 神策上报切面注解
 *
 *@author  Orochi-Yzh
 *@dateTime  2018/12/4 15:48
*/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SensorsAspect {

    /**
     *@Description: 事件类型: SensorsEventEnum
     *
     *@author  Orochi-Yzh
     *@dateTime  2018/12/4 16:21
    */
    String event() ;
}
