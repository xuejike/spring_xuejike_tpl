package vip.xuejike.jktpl.layui.type;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkForm;
import com.bidanet.springmvc.demo.jkbuilder.annotation.core.JkTplTypeRegister;
import com.bidanet.springmvc.demo.jkbuilder.model.AbsFreeMarkerTplText;
import com.bidanet.springmvc.demo.jkbuilder.model.JkInfo;
import com.bidanet.springmvc.demo.jkbuilder.model.JkTplText;

import java.util.HashMap;
import java.util.List;

@JkTplTypeRegister(JkForm.class)
public class LayuiForm extends AbsFreeMarkerTplText {

    @Override
    public String text(JkInfo tpl, List subContent) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("sub",subContent);
        return buildTpl(map);
    }



    @Override
    public String getTpl() {
        return "/layui/form.ftl";
    }
}
