package com.bidanet.springmvc.demo.jkbuilder.annotation.ext;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.CheckBoxFormFieldImpl;

import java.lang.annotation.*;

/**
 * 复选框
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkFormField(type = CheckBoxFormFieldImpl.class)
public @interface JkCheckBoxFormField {
    /**
     * select 数据，使用分隔符拆分，text是前面的值，value是后面的值
     * @return
     */
    String[] value() default {};
    String divider() default "-";
}
