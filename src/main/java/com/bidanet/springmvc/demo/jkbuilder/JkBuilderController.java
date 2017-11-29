package com.bidanet.springmvc.demo.jkbuilder;

import com.bidanet.bdcms.core.common.SpringWebTool;
import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueData;
import com.bidanet.springmvc.demo.jkbuilder.type.JkTypeDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RequestMapping("/jkBuilder")
public class JkBuilderController {


    @RequestMapping("/selectDataSource")
    @ResponseBody
    public List<JkNameValueData> selectDataSource(String key,String beanCls){
        ArrayList<JkNameValueData> list = new ArrayList<>();
        String msg="";
        try {
            Class<?> beanClass = Class.forName(beanCls);
            Object bean = SpringWebTool.getBean(beanClass);
            if (bean instanceof JkTypeDataSource){
                List<JkNameValueData> search = ((JkTypeDataSource) bean).search(key);
                return search;
            }else{
                msg=beanCls+"->未实现:"+JkTypeDataSource.class.getName();
            }
        } catch (ClassNotFoundException e) {
            msg="未找到Class->"+beanCls;
        }catch (Exception ex){
            msg=ex.getMessage();
        }
        String finalMsg = msg;
        list.add(new JkNameValueData() {
            @Override
            public String getName() {
                return finalMsg;
            }

            @Override
            public String getValue() {
                return "empty";
            }
        });
        return list;

    }
}
