package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkButtonType;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkForm {
    String url() default "";
    String title() default "";
    String[] cssClass() default {};
    String[] attrs() default {};
    JkButton[] btns() default {
        @JkButton(value = "提交",type = JkButtonType.submit)
    };

}
