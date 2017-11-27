package com.bidanet.springmvc.demo.jkbuilder.annotation.ext;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.RadioFormFieldImpl;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkFormField(type = RadioFormFieldImpl.class)
public @interface JkRadioFormField {
    /**
     * select 数据，使用分隔符拆分，text是前面的值，value是后面的值
     * @return
     */
    String[] value() default {};

    /**
     * 分隔符
     * @return
     */
    String divider() default "-";
}
