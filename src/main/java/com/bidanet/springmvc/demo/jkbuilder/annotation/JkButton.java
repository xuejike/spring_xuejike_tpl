package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkButtonType;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkButton {
    String value() default "按钮";
    String event() default "";

    String cssClass() default "";
    JkButtonType type() default JkButtonType.diy;
    String url() default "";
    String option() default "{}";

    //type url option

}
