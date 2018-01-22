package com.bidanet.springmvc.demo.jkbuilder.annotation;

import java.lang.annotation.*;
import java.util.HashMap;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkCssClass {
    String[] value() default {};

}
