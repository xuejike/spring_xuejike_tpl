package com.bidanet.springmvc.demo.jkbuilder.annotation.ext;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.HiddenFormFieldImpl;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkFormField(type = HiddenFormFieldImpl.class)
public @interface JkPwdFormField {
    String title() default "";
    String[] cssClass() default {};
    String placeholder() default "";
    String[] attrs() default {};
}
