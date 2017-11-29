package com.bidanet.springmvc.demo.jkbuilder.type;

import lombok.Data;

@Data
public class FormFieldInfo {
    private String id;
    private String name;
    private String title;
    private String placeholder;
    private Object val;
    private String[] cssClass;
    private String[] attrs;
    private int sort;
    private FormFieldHtml type;

    public String getCssClassTpl(){
        if (cssClass!=null){
            StringBuilder sb=new StringBuilder(" ");
            for (String css : cssClass) {
                sb.append(css).append(" ");
            }
            return sb.toString();
        }
        return "";
    }
    public String getAttrsTpl(){
        if (attrs!=null){
            StringBuffer sb = new StringBuffer(" ");
            for (String attr : attrs) {
                sb.append(attr).append(" ");
            }
            return sb.toString();
        }
        return "";
    }

    public String getValString(){
        return String.valueOf(getVal());
    }
}
