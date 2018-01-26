package com.bidanet.springmvc.demo.jkbuilder.model;


import java.util.List;

public interface JkTplText<T extends JkInfo>  {
    String text(T tpl,List<String> subContent);
    default Class<? extends JkInfo> getInfoCls(){return JkInfo.class;}

}
