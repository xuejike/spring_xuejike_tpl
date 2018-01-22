package com.bidanet.springmvc.demo.jkbuilder.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkTable {
    String id() default "jk-table";
}
