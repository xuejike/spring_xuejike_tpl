package com.bidanet.springmvc.demo.jkbuilder.core;

import com.bidanet.springmvc.demo.jkbuilder.annotation.core.JkInfoTag;
import com.bidanet.springmvc.demo.jkbuilder.annotation.core.JkTplTag;
import com.bidanet.springmvc.demo.jkbuilder.config.TplConfig;
import com.bidanet.springmvc.demo.jkbuilder.exception.JkParserException;
import com.bidanet.springmvc.demo.jkbuilder.model.JkInfo;
import com.bidanet.springmvc.demo.jkbuilder.model.JkTplText;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

/**
 * 解析注解的Bean
 */
@Slf4j
public class JkTplViewBeanParser {
    private Class cls;
    private Object clsObj;
    TplFilterManage filterManage = TplConfig.getBean(TplFilterManage.class);
    TplTypeRegisterManage registerManage = TplConfig.getBean(TplTypeRegisterManage.class);


    public JkTplViewBeanParser(Class cls) {
        this.cls = cls;
        clsObj=null;
    }

    public JkTplViewBeanParser(Object clsObj) {
        this.clsObj = clsObj;

        if (clsObj==null){
            throw new JkParserException("clsObj 为 Null");
        }
        this.cls = clsObj.getClass();
    }

    public JkTplViewBeanParser(Class cls, Object clsObj) {
        this.cls = cls;
        this.clsObj = clsObj;
    }
    public JkInfo parser(){
        //解析类
        JkInfo jkInfo = parserElement(cls,clsObj);
        filterManage.execFilter(jkInfo);
        return jkInfo;
    }


    protected JkInfo parserElement(AnnotatedElement parseEle, Object clsObj){


        Annotation tplTag = AnnotationUtils.getAnnotation(parseEle, JkTplTag.class);
        if (tplTag!=null){
            Annotation[] annotations =  parseEle.getAnnotations();
            for (Annotation annotation : annotations) {
                JkTplTag tag = AnnotationUtils.getAnnotation(annotation, JkTplTag.class);
                if (tag!=null){
                    tplTag=annotation;
                    break;
                }
            }


            JkTplText tplText = registerManage.getTplText(tplTag.annotationType());
            if (tplText==null){
                log.error("{} 未定义解析器",tplTag.getClass());
            }else{
                Class<JkInfo> infoCls = tplText.getInfoCls();
                JkInfo jkInfo = BeanUtils.instantiate(infoCls);
                //解析  信息注解
                jkInfo.setTarget(tplText);



                for (Annotation annotation : annotations) {
                    JkInfoTag tag = AnnotationUtils.getAnnotation(annotation, JkInfoTag.class);
                    if (tag!=null){
                        jkInfo.addTagInfo(annotation);
                    }
                }
                JkTplBeanUtils.fillPropertys(jkInfo);


                //设置值
                if (parseEle instanceof Field){
                    jkInfo.setField((Field) parseEle);
                    try {
                        if (clsObj!=null){
                            jkInfo.setFieldVal(PropertyUtils.getProperty(clsObj,((Field) parseEle).getName()));
                        }

                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        log.error("无法读取字段",e);
                    }
                }else if (parseEle instanceof Class){
                    List<Field> allField = JkTplBeanUtils.getAllField((Class) parseEle);
                    for (Field field : allField) {
                        JkInfo fieldInfo = parserElement(field, clsObj);
                        if (fieldInfo!=null){
                            jkInfo.setVoObj(clsObj);
                            jkInfo.setVoCls((Class) parseEle);
                            jkInfo.addSubTpl(fieldInfo);
                        }
                    }
                }else{
                    log.error("非法数据类型");
                }
                return jkInfo;
            }
        }else{
            if (parseEle instanceof Field){
                try {
                    Object property=null;
                    if (clsObj!=null){
                        property= PropertyUtils.getProperty(clsObj, ((Field) parseEle).getName());
                    }


                    JkInfo jkInfo = parserElement(((Field) parseEle).getType(), property);
                    if (jkInfo!=null){
                        return jkInfo;
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    log.error("字段读取失败，是否设置了Getter方法",e);
                }

            }
        }

        return null;
    }


}
