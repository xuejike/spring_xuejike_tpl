package com.bidanet.springmvc.demo.jkbuilder.annotation.core;

import java.lang.annotation.*;

/**
 * 基础描述注解
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkTag {
}
