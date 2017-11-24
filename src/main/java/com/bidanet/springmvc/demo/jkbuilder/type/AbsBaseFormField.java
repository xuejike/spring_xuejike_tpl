package com.bidanet.springmvc.demo.jkbuilder.type;

import com.bidanet.springmvc.demo.jkbuilder.FreeMarkerUtils;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkPlaceholder;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkTitle;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public abstract class AbsBaseFormField implements FormFieldHtml {

    protected JkPlaceholder jkPlaceholder;
    protected JkTitle jkTitle;
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
