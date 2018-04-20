package vip.xuejike.ktpl;

import com.bidanet.springmvc.demo.jkbuilder.FreeMarkerUtils;

import java.util.HashMap;

public abstract class JkKtView {
    protected String tpl;
    HashMap<String, Object> map = new HashMap<>();
    public abstract String content();

    public String toHtml(){
        if (tpl==null){
            return content();
        }

        map.put("content",content());
        return FreeMarkerUtils.build(tpl,map);

    }
}
