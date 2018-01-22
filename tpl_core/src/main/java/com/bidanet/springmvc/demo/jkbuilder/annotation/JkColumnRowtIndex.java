package com.bidanet.springmvc.demo.jkbuilder.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkColumnRowtIndex {
    int value() default 1;
}
