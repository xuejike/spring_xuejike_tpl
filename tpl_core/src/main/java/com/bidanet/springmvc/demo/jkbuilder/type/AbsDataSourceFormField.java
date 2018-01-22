package com.bidanet.springmvc.demo.jkbuilder.type;


import com.bidanet.springmvc.demo.jkbuilder.annotation.JkDataSource;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkSourceType;
import com.bidanet.springmvc.demo.jkbuilder.config.TplConfig;
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
    protected List<JkNameValueData> dataSource;

    @Override
    protected void addExpansionData(FormFieldInfo info, Map<String, Object> map) {
        if (jkDataSource!=null){
            map.put("dataSource",jkDataSource);
            if (jkDataSource.type()== JkSourceType.stringArray){
                dataSource = JkNameValueDataImpl.parseList(jkDataSource.arrayData(), jkDataSource.arrayDivider());

            }else if (jkDataSource.type()== JkSourceType.enumType){
//                Object val = info.getVal();
                Class<? extends Enum> aClass = (Class<? extends Enum>) info.getValCls();
                EnumSet enumSet = EnumSet.allOf(aClass);
                dataSource = new ArrayList<>(enumSet.size());
                for (Object o : enumSet) {
                    if (o instanceof JkNameValueData){
                        dataSource.add((JkNameValueData) o);
                    }else{
                        JkNameValueDataImpl data = new JkNameValueDataImpl(o.toString());
                        dataSource.add(data);
                    }
                }

            }else if (jkDataSource.type()== JkSourceType.url){
                // TODO: 2017/11/20 通过URL地址加载
            }else if (jkDataSource.type()==JkSourceType.beanClass){
                JkTypeDataSource bean = TplConfig.getBean(jkDataSource.beanCls());
                dataSource = bean.search(null);
            }

            map.put("arrayData",dataSource);
        }
    }
}
