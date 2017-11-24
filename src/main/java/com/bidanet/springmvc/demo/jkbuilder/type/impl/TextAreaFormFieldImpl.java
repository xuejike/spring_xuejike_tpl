package com.bidanet.springmvc.demo.jkbuilder.type.impl;

import com.bidanet.springmvc.demo.jkbuilder.type.AbsBaseFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;

import java.util.Map;

public class TextAreaFormFieldImpl extends AbsBaseFormField {
    @Override
    protected String getTpl() {
        return "/form/TextAreaFormField.ftl";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {

    }
}
