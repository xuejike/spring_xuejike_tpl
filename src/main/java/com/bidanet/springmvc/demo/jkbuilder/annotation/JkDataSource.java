package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkSourceType;

import java.lang.annotation.*;

/**
 * 数据源
 */
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkDataSource {
    JkSourceType type() default JkSourceType.stringArray;
    String[] arrayData() default {};
    String arrayDivider() default "-";
    String url() default "";
}