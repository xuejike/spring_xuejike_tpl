package com.bidanet.springmvc.demo.jkbuilder.type.impl;

import com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkUploadFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.AbsBaseFormField;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;
import lombok.Data;

import java.util.Map;
import java.util.Random;

@Data
public class UploadFormFieldImpl extends AbsBaseFormField {
    protected static Random random=new Random();
    protected JkUploadFormField jkUploadFormField;
    @Override
    protected String getTpl() {
        return "/form/UploadFormField.ftl";
    }

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {
        map.put("uuid","up_"+System.currentTimeMillis()+"_"+random.nextInt(100));
        map.put("upload",jkUploadFormField);
    }
}
