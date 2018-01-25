package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.annotation.core.JkTplTag;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkButtonType;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkTplTag
public @interface JkForm {
    /**
     * form表单请求地址
     * @return
     */
    String url() default "";

    /**
     * form表单标题
     * @return
     */
    String title() default "";

    /**
     * form表单CSS的class
     * @return
     */
    String[] cssClass() default {};

    /**
     * form表单属性
     * @return
     */
    String[] attrs() default {};
    String id() default "jk-form";

    /**
     * 按钮定义
     * @return
     */
    JkButton[] btns() default {
        @JkButton(value = "提交",type = JkButtonType.submit)
    };

}
