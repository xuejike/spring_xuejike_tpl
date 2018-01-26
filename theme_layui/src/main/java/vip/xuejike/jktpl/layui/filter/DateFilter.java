package vip.xuejike.jktpl.layui.filter;

import com.bidanet.springmvc.demo.jkbuilder.model.JkFilter;
import com.bidanet.springmvc.demo.jkbuilder.model.JkInfo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DateFilter implements JkFilter {
    @Override
    public void doFilter(JkInfo info) {
        Object fieldVal = info.getFieldVal();
        info.setFieldVal("ssss");
    }

    @Override
    public Set<Class> getSupportSet() {
        HashSet<Class> set = new HashSet<>();
        set.add(Date.class);
        return set;
    }
}
