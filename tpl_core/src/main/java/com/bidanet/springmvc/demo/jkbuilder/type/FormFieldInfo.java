package com.bidanet.springmvc.demo.jkbuilder.type;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkFormGroup;
import lombok.Data;

import static com.bidanet.springmvc.demo.jkbuilder.JkBuilder.getTplString;

@Data
public class FormFieldInfo {
    private String id;
    private String name;
    private String title;
    private String placeholder;
    private Object val;
    private Class valCls;
    private String[] cssClass;
    private String[] attrs;
    private int sort;
    private FormFieldHtml type;
    private String layout;

    private String groupId;
    private JkFormGroup group;

    public String getCssClassTpl(){
        return getTplString(cssClass);
    }

    public String getAttrsTpl(){
        return getTplString(attrs);
    }


    public String getValString(){
        return String.valueOf(getVal());
    }
}
