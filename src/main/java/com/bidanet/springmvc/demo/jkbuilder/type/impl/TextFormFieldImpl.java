package com.bidanet.springmvc.demo.jkbuilder.type.impl;

import com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkTextFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.AbsBaseFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;

import java.util.Map;

public class TextFormFieldImpl extends AbsBaseFormField {
    JkTextFormField jkTextFormField;
    @Override
    protected String getTpl() {
        return "/form/TextFormField.ftl";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {
        if (jkTextFormField==null){
            map.put("type","text");
        }else{
            map.put("type",jkTextFormField.value());
        }
    }
}
