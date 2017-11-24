package com.bidanet.springmvc.demo.jkbuilder.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkTitle {
    String value() default "";
}
