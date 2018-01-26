package com.bidanet.springmvc.demo.jkbuilder;

import com.bidanet.springmvc.demo.jkbuilder.core.JkTplViewBeanParser;
import com.bidanet.springmvc.demo.jkbuilder.model.JkInfo;
import com.bidanet.springmvc.demo.jkbuilder.model.JkTplText;

import java.util.ArrayList;

public class JkTplBuilder {
    public static String build(Class cls){
        JkTplViewBeanParser parser = new JkTplViewBeanParser(cls);
        JkInfo jkInfo = parser.parser();
        return buildJkInfo(jkInfo);
    }
    public static String build(Object obj){
        JkTplViewBeanParser parser = new JkTplViewBeanParser(obj);
        JkInfo jkInfo = parser.parser();
        return buildJkInfo(jkInfo);
    }
    public static String build(Class cls,Object obj){
        JkTplViewBeanParser parser = new JkTplViewBeanParser(cls,obj);
        JkInfo jkInfo = parser.parser();
        return buildJkInfo(jkInfo);
    }
    public static String buildJkInfo(JkInfo jkInfo){
        JkTplText target = jkInfo.getTarget();
        ArrayList sb = new ArrayList<String>();
        if (jkInfo.getSubTpl()!=null){
            for (JkInfo info : jkInfo.getSubTpl()) {
                sb.add(buildJkInfo(info));
            }
        }

        return target.text(jkInfo,sb);
    }
}
