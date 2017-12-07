package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkSourceType;
import com.bidanet.springmvc.demo.jkbuilder.type.JkTypeDataSource;

import java.lang.annotation.*;

/**
 * 数据源
 */
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkDataSource {
    /**
     * 数据源类型
     * @return
     */
    JkSourceType type() default JkSourceType.stringArray;
    /**
     * 数组数据
     * @return
     */
    String[] arrayData() default {};

    /**
     * 分割线
     * @return
     */
    String arrayDivider() default "-";
    String url() default "";
    Class<? extends JkTypeDataSource> beanCls() default JkTypeDataSource.class;

}