package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldHtml;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkFormField {
    String title() default "";
    String[] cssClass() default {};
    String placeholder() default "";
    String[] attrs() default {};

    /**
     * form表单实现类
     * @return
     */
    Class<? extends FormFieldHtml> type();
}
