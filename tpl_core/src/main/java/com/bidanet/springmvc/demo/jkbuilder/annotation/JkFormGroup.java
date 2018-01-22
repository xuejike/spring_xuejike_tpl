package com.bidanet.springmvc.demo.jkbuilder.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkFormGroup {
    String value() default "";
    String[] cssClass() default {};
    String[] attrs() default {};
}
