package com.bidanet.springmvc.demo.jkbuilder.type.impl;

import com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkTplFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.AbsBaseFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;
import org.apache.commons.lang.StringEscapeUtils;

import java.util.Map;
import java.util.Random;

public class TplFormFieldImpl extends AbsBaseFormField {
    private JkTplFormField jkTplFormField;
    private Random random=new Random();
    @Override
    protected String getTpl() {
        return "/form/TplFormField.ftl";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {
        map.put("divId","tpl_"+System.currentTimeMillis()+"_"+random.nextInt(1000));
        if(jkTplFormField==null){
            map.put("tpl","");
        }else{
            map.put("tpl",jkTplFormField.value());
        }
        info.setVal(StringEscapeUtils.escapeJavaScript(String.valueOf(info.getVal())));

    }

    public JkTplFormField getJkTplFormField() {
        return jkTplFormField;
    }

    public void setJkTplFormField(JkTplFormField jkTplFormField) {
        this.jkTplFormField = jkTplFormField;
    }
}
