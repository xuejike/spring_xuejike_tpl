import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueData;
import com.google.common.collect.Lists;
import vip.xuejike.ktpl.libs.CommonKt;
import vip.xuejike.ktpl.libs.EditView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTest {
    public static void main(String[] args){


        List<JkNameValueData> jkNameValueData = enumToList(TestEnum.class);
        for (JkNameValueData jkNameValueDatum : jkNameValueData) {
            System.out.println(jkNameValueDatum.getValue()+"->"+jkNameValueDatum.getName());
        }

        System.out.println("s");
//        System.out.println(new EditView().toHtml());
    }

    public static List<JkNameValueData> enumToList(Class cls){
        if (cls.isEnum()){
            Object[] enumCls = cls.getEnumConstants();
            ArrayList<JkNameValueData> list = new ArrayList<>(enumCls.length);
            for (Object o : enumCls) {
                if (o instanceof JkNameValueData){
                    list.add(((JkNameValueData) o));
                }
            }
            return list;
        }
        return Collections.emptyList();
    }
    public enum TestEnum implements JkNameValueData{
        w1,w2,w3;

        public String cn(){
            return name();
        }

        @Override
        public String getName() {
            return cn();
        }

        @Override
        public String getValue() {
            return String.valueOf(ordinal());
        }
    }
}
