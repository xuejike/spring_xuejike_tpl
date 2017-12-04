package com.bidanet.springmvc.demo.jkbuilder.annotation.ext;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.TplFormFieldImpl;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.UploadFormFieldImpl;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkFormField(type = TplFormFieldImpl.class)
public @interface JkTplFormField {
    String value();
}
