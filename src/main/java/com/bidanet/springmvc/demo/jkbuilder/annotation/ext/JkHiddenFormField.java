package com.bidanet.springmvc.demo.jkbuilder.annotation.ext;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.HiddenFormFieldImpl;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.TextAreaFormFieldImpl;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkFormField(type = HiddenFormFieldImpl.class)
public @interface JkHiddenFormField {
}
