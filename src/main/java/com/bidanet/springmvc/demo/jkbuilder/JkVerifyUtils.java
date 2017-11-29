package com.bidanet.springmvc.demo.jkbuilder;

import com.bidanet.bdcms.core.common.SpringWebTool;
import com.bidanet.bdcms.core.exception.CheckException;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkVerify;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkVerifyRegExp;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkVerifyType;
import com.bidanet.springmvc.demo.jkbuilder.type.JkVerifyRemote;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class JkVerifyUtils {
    public static void verify(Object obj){
        if (obj==null){
            throw new CheckException("obj is null");
        }
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getFields();

    }
    protected static void checkField(Field field,Object obj){
        JkVerify jkVerify = AnnotationUtils.findAnnotation(field, JkVerify.class);
        JkVerifyType[] rules = jkVerify.rules();
        JkVerifyRegExp[] regExps = jkVerify.regExps();
        Class<? extends JkVerifyRemote> ajaxCls = jkVerify.ajaxCls();

        try {
            Object property = PropertyUtils.getProperty(obj, field.getName());
            for (JkVerifyType rule : rules) {
                switch (rule){

                    case required:
                        checkRequired(property,jkVerify.message());
                        break;
                    case ajax:
                        checkAjax(property,jkVerify.ajaxCls());
                    case regExp:
                        checkRegExp(property,regExps);
                    default:
                }
            }

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void checkRegExp(Object property, JkVerifyRegExp[] regExps) {

    }

    protected static void checkRequired(Object obj,String msg){
        if (obj==null){
            throw new CheckException(msg);
        }
        if (obj instanceof String){
            if (((String) obj).isEmpty()){
                throw new CheckException(msg);
            }
        }
    }

    protected static void checkAjax(Object obj, Class<? extends JkVerifyRemote> aClass){
        JkVerifyRemote bean = SpringWebTool.getBean(aClass);
        if (bean!=null){
            String verify = bean.verify(String.valueOf(obj));
            if (verify!=null){
                throw new CheckException(verify);
            }
        }else{
            throw new CheckException(aClass.getName()+"->未定义Bean");
        }
    }
}
