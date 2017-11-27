package com.bidanet.springmvc.demo.jkbuilder.annotation.ext;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormField;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.UploadFileType;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.HiddenFormFieldImpl;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.UploadFormFieldImpl;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkFormField(type = UploadFormFieldImpl.class)
public @interface JkUploadFormField {
    String url() default "";
    UploadFileType type() default UploadFileType.images;
    String exts() default "";
    String btn() default "图片上传";
    int max() default 1;
}
