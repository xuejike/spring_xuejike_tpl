package vip.xuejike.ktpl.common;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuejike
 */
public class SpringTool  {
    protected static ApplicationContext applicationContext;

    @Nullable
    public static <T> T getBean(@NotNull Class<T> cls) {
        if (applicationContext != null){
            return applicationContext.getBean(cls);
        }
        return null;
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        return applicationContext.getAutowireCapableBeanFactory().;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringTool.applicationContext = applicationContext;
    }
}
