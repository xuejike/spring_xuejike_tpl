package com.bidanet.springmvc.demo.jkbuilder.type.impl;


import com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkCheckBoxFormField;
import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueDataImpl;
import com.bidanet.springmvc.demo.jkbuilder.type.AbsDataSourceFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * CheckBox 表单
 * @author xuejike
 */
@Data
public class CheckBoxFormFieldImpl extends AbsDataSourceFormField {
    JkCheckBoxFormField jkCheckBoxFormField;
    @Override
    protected String getTpl() {
        return "/form/CheckBoxFormField.ftl";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {
        //处理值数组或者集合
        Object val = info.getVal();
        if (jkCheckBoxFormField!=null&&jkCheckBoxFormField.value().length>0){

            String[] value = jkCheckBoxFormField.value();
            String divider = jkCheckBoxFormField.divider();

            map.put("arrayData", JkNameValueDataImpl.parseList(value,divider));

        }
        if (val!=null){
            if (val.getClass().isArray()){

            }else if (val instanceof Iterable){

            }else if (val instanceof  String){
                String[] split = ((String) val).split(",");
                info.setVal(split);
            }
        }
        super.addExpansionData(info, map);
    }
}
