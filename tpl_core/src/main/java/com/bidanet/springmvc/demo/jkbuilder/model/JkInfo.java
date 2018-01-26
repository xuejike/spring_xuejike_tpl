package com.bidanet.springmvc.demo.jkbuilder.model;

import com.bidanet.springmvc.demo.jkbuilder.core.MapList;
import lombok.Data;

import java.lang.annotation.Annotation;
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
    private Object voObj;
    private Field field;
    private Object fieldVal;
    private List<JkInfo> subTpl;
    private JkTplText target;
    private MapList<Class<? extends Annotation>,Annotation> annoMapList =new MapList<>();
    private Map<String,Object> propertyMap=new HashMap<>();

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

    public String getCssClassString(){
        StringBuffer sb=new StringBuffer("class=\"");
        for (String s : getCssClass()) {
            sb.append(s).append(" ");
        }
        return sb.append("\"").toString();
    }
    public String getAttrString(){
        StringBuffer sb=new StringBuffer("");
        Map<String, Object> map = getAttrMap();
        map.forEach((k,v)->{
            sb.append(" "+k+"=\"").append(v).append("\" ");
        });
        return sb.toString();
    }
    public void addTagInfo(Annotation annotation){
        annoMapList.addItem(annotation.annotationType(), annotation);
    }
    public void addProperty(String name,Object val){
        propertyMap.put(name, val);
    }

    public MapList<Class<? extends Annotation>, Annotation> getAnnoMapList() {
        return annoMapList;
    }
}
