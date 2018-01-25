package com.bidanet.springmvc.demo.jkbuilder.model;

import java.util.Set;

/**
 * 值过滤器
 * @param <T>
 */
public interface JkValFilter<T extends JkInfo> {
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
    Set<Class> supportSet(Class cls);
}
