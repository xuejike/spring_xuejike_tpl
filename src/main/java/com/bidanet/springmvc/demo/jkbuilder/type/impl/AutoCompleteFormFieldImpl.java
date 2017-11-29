package com.bidanet.springmvc.demo.jkbuilder.type.impl;

import com.bidanet.bdcms.core.common.SpringWebTool;
import com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkAutoCompleteFormField;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.AutoCompleteType;
import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueData;
import com.bidanet.springmvc.demo.jkbuilder.type.AbsBaseFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.AbsDataSourceFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;
import com.bidanet.springmvc.demo.jkbuilder.type.JkTypeDataSource;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AutoCompleteFormFieldImpl extends AbsDataSourceFormField {
    JkAutoCompleteFormField jkAutoCompleteFormField;
    @Override
    protected String getTpl() {
        return "/form/AutoCompleteFormField.ftl";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {
        super.addExpansionData(info, map);

        map.put("auto",jkAutoCompleteFormField);
        if (info.getVal()!=null){
            if (jkAutoCompleteFormField.type()== AutoCompleteType.remote){
                for (JkNameValueData data : dataSource) {
                    if (data.getValue().equals(info.getVal())){
                        return;
                    }
                }
                //加载当前选中值
                Class<? extends JkTypeDataSource> beanCls = jkDataSource.beanCls();
                String text = SpringWebTool.getBean(beanCls).getText(info.getVal().toString());
                dataSource.add(0, new JkNameValueData() {
                    @Override
                    public String getName() {
                        return text;
                    }

                    @Override
                    public String getValue() {
                        return info.getVal().toString();
                    }
                });
            }
        }

    }
}
