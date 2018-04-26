package vip.xuejike.ktpl;

import com.bidanet.springmvc.demo.jkbuilder.FreeMarkerUtils;

import java.util.HashMap;

public abstract class JkKtView {
    protected String tpl;
    HashMap<String, Object> map = new HashMap<>();

    public abstract String content();
    public String head(){
        return "";
    }
    public String footer(){
        return "";
    }

    public String toHtml(){
        if (tpl==null){
            return content();
        }

        map.put("content",content());
        map.put("head",head());
        map.put("footer",footer());
        return FreeMarkerUtils.build(tpl,map);

    }

    @Override
    public String toString() {
        return toHtml();
    }
}
