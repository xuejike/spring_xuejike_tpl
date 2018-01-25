package com.bidanet.springmvc.demo.jkbuilder.annotation.core;

import java.lang.annotation.*;

/**
 * 模板注解，用于生成HTML代码
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkTag
public @interface JkTplTag {

}
