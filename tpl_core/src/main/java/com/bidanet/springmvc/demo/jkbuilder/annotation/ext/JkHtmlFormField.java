package com.bidanet.springmvc.demo.jkbuilder.annotation.ext;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.HtmlFormFieldImpl;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.TplFormFieldImpl;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkFormField(type = HtmlFormFieldImpl.class)
public @interface JkHtmlFormField {
}
