package com.bidanet.springmvc.demo.jkbuilder.filter;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkCssClass;
import com.bidanet.springmvc.demo.jkbuilder.model.JkFilter;
import com.bidanet.springmvc.demo.jkbuilder.model.JkInfo;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class JkTplCssClassFilter implements JkFilter {
    @Override
    public void doFilter(JkInfo info) {
        JkCssClass item = (JkCssClass) info.getAnnoMapList().getItem(JkCssClass.class);
        if (item!=null){
            Collections.addAll(info.getCssClass(),item.value());
        }
    }

    @Override
    public Set<Class> getSupportSet() {
        HashSet<Class> set = new HashSet<>(1);
        set.add(JkCssClass.class);
        return set;
    }
}
