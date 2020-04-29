package com.panda.esportingplus.common.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author shusong.liang
 * @date 2020/03/25 11:12:56
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Spi {

    /**
     * SPI name
     *
     * @return name
     */
    String value() default "";

    /**
     * 排序顺序
     *
     * @return sortNo
     */
    int order() default 0;

}
