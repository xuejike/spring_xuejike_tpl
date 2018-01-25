package com.bidanet.springmvc.demo.jkbuilder.model;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * 类型注解过滤器
 * @param <T>
 */
public interface JkAnnotationFilter<T extends JkInfo> {
    /**
     * 过滤器
     * @param info
     */
    void doFilter(T info);

    /**
     * 执行的方法
     * @param cls
     * @return
     */
    Set<Annotation> supportSet(Class cls);
}
