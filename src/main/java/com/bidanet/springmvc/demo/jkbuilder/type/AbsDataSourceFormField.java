package com.bidanet.springmvc.demo.jkbuilder.type;

import com.bidanet.bdcms.core.common.SpringWebTool;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkDataSource;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkSourceType;
import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueData;
import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueDataImpl;
import lombok.Data;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

@Data
public abstract class AbsDataSourceFormField extends AbsBaseFormField {
    protected JkDataSource jkDataSource;

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {
        if (jkDataSource!=null){
            if (jkDataSource.type()== JkSourceType.stringArray){
                List<JkNameValueData> data = JkNameValueDataImpl.parseList(jkDataSource.arrayData(), jkDataSource.arrayDivider());
                map.put("arrayData",data);
            }else if (jkDataSource.type()== JkSourceType.enumType){
                Object val = info.getVal();
                Class<? extends Enum> aClass = (Class<? extends Enum>) val.getClass();
                EnumSet enumSet = EnumSet.allOf(aClass);
                ArrayList<JkNameValueData> list = new ArrayList<>(enumSet.size());
                for (Object o : enumSet) {
                    if (o instanceof JkNameValueData){
                        list.add((JkNameValueData) o);
                    }else{
                        JkNameValueDataImpl data = new JkNameValueDataImpl(o.toString());
                        list.add(data);
                    }
                }
                map.put("arrayData",list);

            }else if (jkDataSource.type()== JkSourceType.url){
                // TODO: 2017/11/20 通过URL地址加载
            }else if (jkDataSource.type()==JkSourceType.beanClass){
                JkTypeDataSource bean = SpringWebTool.getBean(jkDataSource.beanCls());
                List<JkNameValueData> list = bean.search(null);
                map.put("arrayData",list);
            }
        }
    }
}
