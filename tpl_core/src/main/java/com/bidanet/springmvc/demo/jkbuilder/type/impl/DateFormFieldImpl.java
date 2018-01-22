package com.bidanet.springmvc.demo.jkbuilder.type.impl;

import com.bidanet.springmvc.demo.jkbuilder.type.AbsBaseFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;

import java.util.Map;

public class DateFormFieldImpl extends AbsBaseFormField {
    @Override
    protected String getTpl() {
        return "/form/DateFormField.ftl";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {

    }
}
