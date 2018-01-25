package com.bidanet.springmvc.demo.jkbuilder.model;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
/**
 * 模板节点基础信息
 */
public class JkInfo {
    private Class voCls;
    private Field field;
    private Object fieldVal;
    private List<JkInfo> subTpl;
    private JkTplText target;
    /**
     * CSS的Class
     */
    private List<String> cssClass=new ArrayList<>();
    private Map<String,Object> attrMap=new HashMap<>();

    public JkInfo addSubTpl(JkInfo jkInfo){
        if (subTpl==null){
            subTpl=new ArrayList<>();
        }
        subTpl.add(jkInfo);
        return this;
    }
    public JkInfo addCssClass(String cls){
        cssClass.add(cls);
        return this;
    }
    public JkInfo addAttr(String name,Object val){
        attrMap.put(name, val);
        return this;
    }

}
