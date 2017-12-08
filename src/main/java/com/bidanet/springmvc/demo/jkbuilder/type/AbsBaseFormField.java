package com.bidanet.springmvc.demo.jkbuilder.type;

import com.bidanet.springmvc.demo.jkbuilder.FreeMarkerUtils;
import com.bidanet.springmvc.demo.jkbuilder.annotation.*;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkVerifyType;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class AbsBaseFormField implements FormFieldHtml {

    protected JkPlaceholder jkPlaceholder;
    protected JkTitle jkTitle;
    protected JkVerify jkVerify;
    protected JkDisable jkDisable;
    protected JkCssClass jkCssClass;
    protected JkInputAttrs jkInputAttrs;


// <input type="hidden" name="" value=""/>
    @Override
    public String html(FormFieldInfo info) {
        return html(info,"block");
    }

    @Override
    public String html(FormFieldInfo info, String layout) {
        HashMap<String, Object> map = new HashMap<>(1);
        if (jkPlaceholder!=null){
            info.setPlaceholder(jkPlaceholder.value());
        }
        if (jkTitle!=null){
            info.setTitle(jkTitle.value());
        }

        map.put("formField",info);
        map.put("layout",layout);

        map.put("verifyInfo",new VerifyInfo(jkVerify));
        if (jkDisable!=null){
            map.put("disable","disabled");
        }
        if (jkCssClass!=null){
            info.setCssClass(jkCssClass.value());
        }
        if (jkInputAttrs!=null){
            info.setAttrs(jkInputAttrs.value());
        }


        addExpansionData(info,map);
        return FreeMarkerUtils.build(getTpl(),map);
    }


    /**
     * 获取模板文件
     * @return
     */
    protected abstract String getTpl();
    protected abstract void addExpansionData(FormFieldInfo info, Map<String,Object> map);
}
