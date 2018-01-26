package com.bidanet.springmvc.demo.jkbuilder.model;

import java.lang.annotation.Annotation;
import java.util.Set;

public interface JkFilter<T extends JkInfo> {
    /**
     * 过滤器
     * @param info
     */
    void doFilter(T info);

    /**
     * 过滤支持的注解
     * @return
     */
    Set<Class> getSupportSet();
}
