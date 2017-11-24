package com.bidanet.springmvc.demo.jkbuilder.annotation.type;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

public enum JkEvent {
    ;
    private String tag;
    private String url;
    private HashMap<String,Object> option;

    JkEvent(String tag, String url) {
        this.tag = tag;
        this.url = url;
        this.option = new HashMap<>();
    }
    public JkEvent addOption(String key, Object val){
        option.put(key, val);
        return this;
    }

    @Override
    public String toString() {

        return tag+"@"+url+"@"+JSON.toJSONString(option);
    }
}
