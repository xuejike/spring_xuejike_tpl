package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkVerifyType;
import com.bidanet.springmvc.demo.jkbuilder.type.JkVerifyRemote;

import java.lang.annotation.*;

/**
 * 表单校验
 */
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkVerify {
    JkVerifyType[] rules() default {};
    String[] rulesTag() default {};
    JkVerifyRegExp[] regExps() default {};
    String message() default "";
    Class<? extends JkVerifyRemote> ajaxCls() default JkVerifyRemote.class;
}
