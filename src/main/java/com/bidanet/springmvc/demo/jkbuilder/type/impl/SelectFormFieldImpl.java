package com.bidanet.springmvc.demo.jkbuilder.type.impl;


import com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkSelectFormField;
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
public class SelectFormFieldImpl extends AbsDataSourceFormField {
    JkSelectFormField jkSelectFormField;
    @Override
    protected String getTpl() {
        return "/form/SelectFormField.ftl";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {
        if (jkSelectFormField!=null&&jkSelectFormField.value().length>0){

            String[] value = jkSelectFormField.value();
            String divider = jkSelectFormField.divider();

            map.put("arrayData", JkNameValueDataImpl.parseList(value,divider));

        }
        super.addExpansionData(info, map);
    }
}
