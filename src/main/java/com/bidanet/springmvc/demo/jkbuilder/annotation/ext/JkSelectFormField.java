package com.bidanet.springmvc.demo.jkbuilder.annotation.ext;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.impl.SelectFormFieldImpl;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@JkFormField(type = SelectFormFieldImpl.class)
public @interface JkSelectFormField {
    /**
     * select 数据，使用分隔符拆分，text是前面的值，value是后面的值
     * @return
     */
    String[] value() default {};
    String divider() default "-";

}
