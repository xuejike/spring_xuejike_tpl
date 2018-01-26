package com.bidanet.springmvc.demo.jkbuilder;

import com.bidanet.springmvc.demo.jkbuilder.core.JkTplViewBeanParser;
import com.bidanet.springmvc.demo.jkbuilder.model.JkInfo;
import com.bidanet.springmvc.demo.jkbuilder.model.JkTplText;

import java.util.ArrayList;

/**
 * 模板构建
 */
public class JkTplBuilder {
    JkInfo root;
    public static JkTplBuilder create(Class cls,Object obj){
        JkTplViewBeanParser parser = new JkTplViewBeanParser(cls,obj);
        JkTplBuilder builder = new JkTplBuilder();
        builder.root=parser.parser();
        return builder;
    }
    public static JkTplBuilder create(Object obj){
        return create(obj.getClass(),obj);
    }
    public static JkTplBuilder create(Class cls ){
        return create(cls,null);
    }
    public JkTplBuilder addParseSubs(Class cls,Object obj){
        JkTplViewBeanParser parser = new JkTplViewBeanParser(cls, obj);
        root.getSubTpl().addAll(parser.parser().getSubTpl());
        return this;
    }
    public JkTplBuilder addParseSubs(Class cls){
        return addParseSubs(cls,null);
    }
    public JkTplBuilder addParseSubs(Object obj){
        return addParseSubs(obj.getClass(),null);
    }
    public JkTplBuilder addParse(Class cls,Object obj){
        JkInfo parse = parse(cls, obj);
        root.addSubTpl(parse);
        return this;
    }
    public String build(){
        return buildJkInfo(root);
    }
    public JkInfo parse(Class cls,Object obj){
        JkTplViewBeanParser parser = new JkTplViewBeanParser(cls, obj);
        return parser.parser();
    }


    /**
     * 生成代码
     * @param cls
     * @return
     */

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
