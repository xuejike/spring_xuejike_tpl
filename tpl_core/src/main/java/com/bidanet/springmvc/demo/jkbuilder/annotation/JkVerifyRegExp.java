package com.bidanet.springmvc.demo.jkbuilder.annotation;

public @interface JkVerifyRegExp {
    String message() default "";
    String regExp() default "";
}
