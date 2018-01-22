package com.bidanet.springmvc.demo.jkbuilder.type.impl;


import com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkRadioFormField;
import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueDataImpl;
import com.bidanet.springmvc.demo.jkbuilder.type.AbsDataSourceFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;
import lombok.Data;

import java.util.Map;

/**
 * Select 表单
 * @author xuejike
 */
@Data
public class RadioFormFieldImpl extends AbsDataSourceFormField {
    JkRadioFormField jkRadioFormField;
    @Override
    protected String getTpl() {
        return "/form/RadioFormField.ftl";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {
        if (jkRadioFormField!=null||jkRadioFormField.value().length>0){

            String[] value = jkRadioFormField.value();
            String divider = jkRadioFormField.divider();
            map.put("arrayData", JkNameValueDataImpl.parseList(value,divider));

        }

        super.addExpansionData(info, map);
    }
}
