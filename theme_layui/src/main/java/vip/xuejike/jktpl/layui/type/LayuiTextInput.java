package vip.xuejike.jktpl.layui.type;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkPlaceholder;
import com.bidanet.springmvc.demo.jkbuilder.annotation.core.JkTplTypeRegister;
import com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkTextFormField;
import com.bidanet.springmvc.demo.jkbuilder.model.AbsFreeMarkerTplText;
import com.bidanet.springmvc.demo.jkbuilder.model.JkInfo;
import vip.xuejike.jktpl.layui.info.LayuiInputInfo;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;

@JkTplTypeRegister(JkTextFormField.class)
public class LayuiTextInput extends AbsFreeMarkerTplText<LayuiInputInfo> {

    @Override
    public String getTpl() {
        return "/layui/form_input.ftl";
    }

    @Override
    public String text(LayuiInputInfo tpl, List subContent) {

//        tpl.
        HashMap<String, Object> map = new HashMap<>();
        JkPlaceholder item = (JkPlaceholder) tpl.getAnnoMapList()
                .getItem(JkPlaceholder.class);
        map.put("name",tpl.getField().getName());
        map.put("val",String.valueOf(tpl.getFieldVal()));
//        map.put("title",tpl.getJkTitle().value());
        map.put("attrs",tpl.getAttrString());
        return buildTpl(map);
    }

    @Override
    public Class<? extends JkInfo> getInfoCls() {
        return LayuiInputInfo.class;
    }
}
