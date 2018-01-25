package com.bidanet.springmvc.demo.jkbuilder.annotation.core;

import java.lang.annotation.*;

/**
 * 信息注解，用于定义 属性，配置等其他信息
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkTag
public @interface JkInfoTag {
}
