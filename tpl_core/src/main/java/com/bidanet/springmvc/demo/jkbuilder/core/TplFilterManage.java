package com.bidanet.springmvc.demo.jkbuilder.core;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * 过滤器管理器
 */
public class TplFilterManage {
    @EventListener(ContextRefreshedEvent.class)
    public void register(ContextRefreshedEvent contextRefreshedEvent){

    }
}
