package com.bidanet.springmvc.demo.jkbuilder.annotation.ext;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormField;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.AutoCompleteType;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.AutoCompleteFormFieldImpl;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.HiddenFormFieldImpl;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkFormField(type = AutoCompleteFormFieldImpl.class)
public @interface JkAutoCompleteFormField {
    AutoCompleteType type() default AutoCompleteType.local;
}
