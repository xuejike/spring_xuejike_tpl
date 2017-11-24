package com.bidanet.springmvc.demo.jkbuilder.type.impl;

import com.bidanet.springmvc.demo.jkbuilder.type.AbsBaseFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;

import java.util.Map;

public class TextFormFieldImpl extends AbsBaseFormField {
    @Override
    protected String getTpl() {
        return "/form/TextFormField.ftl";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {

    }
}
