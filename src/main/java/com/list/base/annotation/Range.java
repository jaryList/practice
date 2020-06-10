package com.list.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 范围
 * @author lisongtao3
 * @className Range
 * @date 2020/6/4
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {

    /**
     * 最小值
     **/
    int min() default 1;

    /**
     * 最大值
     **/
    int max() default 100;

}
