package com.bidanet.springmvc.demo.jkbuilder.annotation.type;

import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueData;

public enum JkSourceType implements JkNameValueData {
    /**
     * 字符串数组
     */
    stringArray,
    /**
     * 枚举
     */
    enumType,
    /**
     * Url地址
     */
    url,
    /**
     * Spring BeanClass数据
     */
    beanClass
    ;

    @Override
    public String getName(){
        switch (this){
            case stringArray:
                return "字符串数组";
            case url:
                return "URL地址";
            case enumType:
                return "枚举类型";
            case beanClass:
                return "接口数据";
        }
        return "";
    }
    @Override
    public String getValue(){
        return toString();
    }


}
