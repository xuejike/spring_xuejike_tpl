package com.bidanet.springmvc.demo.jkbuilder.type;

import lombok.Data;

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

    private String[] divCss;
    private String[] divAttr;

    public String getCssClassTpl(){
        return getTplString(cssClass);
    }
    public String getDivCssTpl(){
        return getTplString(divCss);
    }
    public String getDivAttrTpl(){
        return getTplString(divAttr);
    }
    public String getAttrsTpl(){
        return getTplString(attrs);
    }

    private String getTplString(String[] strings){
        if (strings!=null){
            StringBuilder sb=new StringBuilder(" ");
            for (String css : strings) {
                sb.append(css).append(" ");
            }
            return sb.toString();
        }
        return "";
    }
    public String getValString(){
        return String.valueOf(getVal());
    }
}
