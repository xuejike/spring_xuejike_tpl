package com.bidanet.springmvc.demo.jkbuilder.annotation;


import com.bidanet.springmvc.demo.jkbuilder.annotation.core.JkInfoTag;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkInfoTag
public @interface JkFormGroup {
    String value() default "";
    String[] cssClass() default {};
    String[] attrs() default {};
}
