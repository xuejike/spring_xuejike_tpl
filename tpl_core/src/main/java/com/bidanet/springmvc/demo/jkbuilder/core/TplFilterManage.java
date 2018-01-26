package com.bidanet.springmvc.demo.jkbuilder.core;

import com.bidanet.springmvc.demo.jkbuilder.model.JkFilter;
import com.bidanet.springmvc.demo.jkbuilder.model.JkInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 过滤器管理器
 * @author xuejike
 */
@Component
@Slf4j
public class TplFilterManage {

    protected Map<Class,List<JkFilter>> jkAnnotationFilterMap=new HashMap<>();


    @EventListener(ContextRefreshedEvent.class)
    public void register(ContextRefreshedEvent contextRefreshedEvent){

        ApplicationContext context = contextRefreshedEvent.getApplicationContext();
        Map<String, JkFilter> textMap = context.getBeansOfType(JkFilter.class);
        textMap.forEach((k,v)->{
            Set<Class> set = v.getSupportSet();
            for (Class cls : set) {
                addTplTextMap(cls,v);
            }
        });

    }
    public void addTplTextMap(Class cls,JkFilter tplText){
        List<JkFilter> tplTextList = jkAnnotationFilterMap.get(cls);
        if (tplTextList==null){
            tplTextList=new ArrayList<>();
            jkAnnotationFilterMap.put(cls,tplTextList);
        }
        tplTextList.add(tplText);
    }

    protected void execFilterItem(Class cls, JkInfo jkInfo){
        List<JkFilter> list = jkAnnotationFilterMap.get(cls);
        if (list!=null){
            list.forEach(l->{
                l.doFilter(jkInfo);
            });
        }
    }
    public void execFilter(JkInfo jkInfo){
        for (Class<? extends Annotation> ann : jkInfo.getAnnoMapList().mapList.keySet()) {
            execFilterItem(ann,jkInfo);
        }
        execFilterItem(jkInfo.getVoCls(),jkInfo);
        if (jkInfo.getField()!=null){
            execFilterItem(jkInfo.getField().getType(),jkInfo);
        }

        //子节点过滤器
        if (jkInfo.getSubTpl()!=null){
            for (JkInfo info : jkInfo.getSubTpl()) {
                execFilter(info);
            }
        }

    }
}
