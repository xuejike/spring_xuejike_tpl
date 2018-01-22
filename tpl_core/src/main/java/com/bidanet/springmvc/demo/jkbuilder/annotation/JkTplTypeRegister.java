package com.bidanet.springmvc.demo.jkbuilder.annotation;

import java.lang.annotation.*;

/**
 * 类型注册注解-->解析器
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkTplTypeRegister {
    Class value();
}
