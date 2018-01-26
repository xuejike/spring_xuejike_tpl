package com.bidanet.springmvc.demo.jkbuilder.core;


import com.bidanet.springmvc.demo.jkbuilder.annotation.core.JkTplTypeRegister;
import com.bidanet.springmvc.demo.jkbuilder.model.JkTplText;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类型注册管理器
 * @author xuejike
 */
@Component
@Slf4j
public class TplTypeRegisterManage {
    HashMap<Class<? extends Annotation>,List<JkTplText>> tagsMap=new HashMap<>();

    @EventListener(ContextRefreshedEvent.class)
    public void register(ContextRefreshedEvent contextRefreshedEvent){
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        Map<String, JkTplText> map = applicationContext.getBeansOfType(JkTplText.class);
        map.forEach((k,o)->{
            JkTplTypeRegister register = AnnotationUtils.getAnnotation(o.getClass(), JkTplTypeRegister.class);
            if (register==null){
                log.error("{0} 未定义注解 @JkTplTypeRegister",o.getClass());
            }else{
                addRegister(register.value(),o);
            }
        });
    }
    protected void addRegister(Class<? extends Annotation> ann,JkTplText tplText){
        List<JkTplText> list = tagsMap.get(ann);
        if (list==null){
            list=new ArrayList<>();
            tagsMap.put(ann,list);
        }
        list.add(tplText);
    }
    public JkTplText getTplText(Class<? extends Annotation> annotation){
        List<JkTplText> list = tagsMap.get(annotation);
        if (list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }



}
