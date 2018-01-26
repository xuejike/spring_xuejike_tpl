package com.bidanet.springmvc.demo.jkbuilder.model;

import com.bidanet.springmvc.demo.jkbuilder.FreeMarkerUtils;

import java.util.Map;

public abstract class AbsFreeMarkerTplText<T extends JkInfo> implements JkTplText<T>{
    public abstract String getTpl();
    protected String buildTpl(Map<String,Object> data){
        return FreeMarkerUtils.build(getTpl(),data);
    }
}
