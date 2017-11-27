package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkButtonType;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkButton {
    String value() default "按钮";

    /**
     * 按钮事件
     * @return
     */
    String event() default "";

    String cssClass() default "";

    /**
     * 按钮类型
     * @return
     */
    JkButtonType type() default JkButtonType.diy;

    /**
     * 请求地址
     * @return
     */
    String url() default "";

    /**
     * 选项
     * @return
     */
    String option() default "{}";

}
