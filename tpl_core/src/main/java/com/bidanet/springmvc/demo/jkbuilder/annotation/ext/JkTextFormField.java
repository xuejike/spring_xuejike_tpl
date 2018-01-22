package com.bidanet.springmvc.demo.jkbuilder.annotation.ext;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.HiddenFormFieldImpl;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.TextFormFieldImpl;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkFormField(type = TextFormFieldImpl.class)
public @interface JkTextFormField {
    String value() default "text";
}
