package com.bidanet.springmvc.demo.jkbuilder.annotation.core;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 类型注册注解-->解析器
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface JkTplTypeRegister {
    Class<? extends Annotation> value();
}
