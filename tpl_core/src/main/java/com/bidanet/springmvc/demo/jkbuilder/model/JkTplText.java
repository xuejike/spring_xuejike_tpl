package com.bidanet.springmvc.demo.jkbuilder.model;


public interface JkTplText<T extends JkInfo> {
    String text(T tpl);
    Class<T> getInfoCls();
}
