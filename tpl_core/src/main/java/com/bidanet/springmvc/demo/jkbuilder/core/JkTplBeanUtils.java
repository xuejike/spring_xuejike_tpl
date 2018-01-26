package com.bidanet.springmvc.demo.jkbuilder.core;

import com.bidanet.springmvc.demo.jkbuilder.model.JkInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class JkTplBeanUtils {
    public static void fillPropertys(JkInfo jkInfo){
        Class<? extends JkInfo> cls = jkInfo.getClass();
        List<Field> list = getAllField(cls);
        for (Field field : list) {
            Class<?> type = field.getType();
            if (type.isAnnotation()){
                Annotation item = jkInfo.getAnnoMapList().getItem((Class<? extends Annotation>) type);
                try {
                    PropertyUtils.setProperty(jkInfo,field.getName(),item);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    log.error("属性设置失败",e);
                }
            }

        }


//        BeanUtils.
//        cls.getDeclaredFields()
    }
//    public List<Field> getByCache(Class cls){
//
//    }
    public static List<Field> getAllField(Class cls){
        ArrayList<Field> list = new ArrayList<>();
        while (cls !=Object.class){
            Field[] fields = cls.getDeclaredFields();
            Collections.addAll(list,fields);
            cls=cls.getSuperclass();
        }
        return list;

    }
}
