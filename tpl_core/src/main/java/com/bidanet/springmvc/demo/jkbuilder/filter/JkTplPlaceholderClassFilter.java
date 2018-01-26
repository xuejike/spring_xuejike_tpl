package com.bidanet.springmvc.demo.jkbuilder.filter;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkCssClass;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkPlaceholder;
import com.bidanet.springmvc.demo.jkbuilder.model.JkFilter;
import com.bidanet.springmvc.demo.jkbuilder.model.JkInfo;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xuejike
 */
@Component
public class JkTplPlaceholderClassFilter implements JkFilter {
    @Override
    public void doFilter(JkInfo info) {
        JkPlaceholder item = (JkPlaceholder) info.getAnnoMapList().getItem(JkPlaceholder.class);
        if (item!=null){
            info.getAttrMap().put("placeholder",item.value());
//            Collections.addAll(info.getCssClass(),item.value());
        }
    }

    @Override
    public Set<Class> getSupportSet() {
        HashSet<Class> set = new HashSet<>(1);
        set.add(JkPlaceholder.class);
        return set;
    }
}
