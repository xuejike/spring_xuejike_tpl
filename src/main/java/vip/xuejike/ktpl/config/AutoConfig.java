package vip.xuejike.ktpl.config;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vip.xuejike.ktpl.common.SpringTool;
import vip.xuejike.ktpl.mvc.JkKtViewMessageConverters;

/**
 * @author xuejike
 */
@Configuration
@Import(SpringTool.class)
public class AutoConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringTool.setApplicationContext(applicationContext);

    }
    @Bean
    public JkKtViewMessageConverters jkKtViewMessageConverters(){
        return new JkKtViewMessageConverters();
    }
}
