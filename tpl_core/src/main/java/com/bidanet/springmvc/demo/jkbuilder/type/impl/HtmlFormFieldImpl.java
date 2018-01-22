package com.bidanet.springmvc.demo.jkbuilder.type.impl;

import com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkTplFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.AbsBaseFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.Map;
import java.util.Random;

public class HtmlFormFieldImpl extends AbsBaseFormField {

    @Override
    protected String getTpl() {
        return "";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {

    }

    @Override
    public String html(FormFieldInfo info, String layout) {

        return String.valueOf(info.getVal());
    }
}
