package com.sg.zhsd.uav.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义敏感词校验注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSensitiveWords {

    Class  object() ;//需要校验的入参对象

    String[] field() default "";//需要校验的字段名称
}
