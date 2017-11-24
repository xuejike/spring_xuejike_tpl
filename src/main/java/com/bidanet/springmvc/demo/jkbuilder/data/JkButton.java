package com.bidanet.springmvc.demo.jkbuilder.data;

import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkButtonType;
import lombok.Data;

/**
 * 按钮
 * @author xuejike
 */
@Data
public class JkButton {
    private String id;
    private String text;
    private String[] cssClass;
    private String event;
    private JkButtonType type;

    public JkButton(String id, String text, String[] cssClass, String event) {
        this.id = id;
        this.text = text;
        this.cssClass = cssClass;
        this.event = event;
    }

    public JkButton(String id, String text, String[] cssClass, JkButtonType type,String url,String option) {
        this.id = id;
        this.text = text;
        this.cssClass = cssClass;
        this.type = type;
        if (option==null){
            option="{}";
        }
        this.event=type.toString()+"@"+url+"@"+option;
    }

    public static JkButton edit(String title, String url, String option){
        return new JkButton(null,title,
                new String[]{"layui-btn layui-btn-mini layui-btn-normal"}
                ,JkButtonType.dialog,url,option);
    }
    public static JkButton edit(String url, String option){
        return edit("编辑",url,option);
    }
    public static JkButton del(String title, String url, String option){
        return new JkButton(null,title,
                new String[]{"layui-btn layui-btn-mini layui-btn-dange"}
                ,JkButtonType.doAjax,url,option);
    }
    public static JkButton details(String title, String url, String option){
        return new JkButton(null,title,
                new String[]{"layui-btn layui-btn-mini"}
                ,JkButtonType.dialog,url,option);
    }

}
