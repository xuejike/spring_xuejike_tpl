package com.bidanet.springmvc.demo.jkbuilder.annotation;

import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkColumnAlign;
import com.bidanet.springmvc.demo.jkbuilder.type.TableColumnType;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkColumn {
    TableColumnType value() default TableColumnType.string;

    /**
     * 标题
     * @return
     */
    String title() default "";
    /**
     * 排序
     * @return
     */
    boolean sort() default false;
    /**
     * 是否可以编辑
     * @return
     */
    boolean edit() default false;

    /**
     * 事件名称
     * @return
     */
    String event() default "";

    /**
     * 单元格样式
     * @return
     */
    String style() default "";

    /**
     * 排列方式
     * @return
     */
    JkColumnAlign align() default JkColumnAlign.center;

    /**
     * 多级表头-列
     * @return
     */
    int colspan() default 1;

    /**
     * 多级表头-行
     * @return
     */
    int rowspan() default 1;

    /**
     * 处理模板
     * @return
     */
    String templet() default "";
    int width() default 0;
    JkColumnAlign fixed() default JkColumnAlign.none;

}

