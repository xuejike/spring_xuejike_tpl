package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.annotation.core.JkInfoTag;

import java.lang.annotation.*;
import java.util.HashMap;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkInfoTag
public @interface JkCssClass {
    String[] value() default {};

}
