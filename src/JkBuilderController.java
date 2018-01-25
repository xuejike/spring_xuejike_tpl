import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueData;
import com.bidanet.springmvc.demo.jkbuilder.type.JkTypeDataSource;
import com.bidanet.springmvc.demo.jkbuilder.type.JkVerifyRemote;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


//@RequestMapping("/jkBuilder")
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
    @RequestMapping("/verify")
    @ResponseBody
    public ApiResult<String> verify(String value,String beanCls){
        try {
            Class<?> beanClass = Class.forName(beanCls);
            Object bean = SpringWebTool.getBean(beanClass);
            if (bean instanceof JkVerifyRemote){
                String verify = ((JkVerifyRemote) bean).verify(value);
                if (verify==null){
                    return ApiResult.success("");
                }else{
                    return ApiResult.error(verify);
                }
            }
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            return ApiResult.error("处理类不存在："+beanCls);
        }catch (Exception ex){
            return ApiResult.error(ex.getMessage());
        }
        return ApiResult.error("服务异常");
    }
}
