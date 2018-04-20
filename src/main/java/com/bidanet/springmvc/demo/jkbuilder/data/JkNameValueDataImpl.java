package com.bidanet.springmvc.demo.jkbuilder.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuejike
 */
@Data
public class JkNameValueDataImpl implements JkNameValueData {
    public String name;
    public String value;

    public JkNameValueDataImpl(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public JkNameValueDataImpl(String name) {
        this(name,name);
    }

    public JkNameValueDataImpl() {
        this("");
    }

    public static List<JkNameValueData> parseList(String [] value, String divider){
        ArrayList<JkNameValueData> list = new ArrayList<>(value.length);
        for (String v : value) {
            String[] split = v.split(divider);
            JkNameValueData data;
            if (split.length>1){
                data = new JkNameValueDataImpl(split[0], split[1]);

            }else if (split.length>0){
                data = new JkNameValueDataImpl(split[0]);
            }else{
                data = new JkNameValueDataImpl(v);
            }
            list.add(data);
        }
        return list;
    }
}
