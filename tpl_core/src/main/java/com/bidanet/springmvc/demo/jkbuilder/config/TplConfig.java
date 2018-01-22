package com.bidanet.springmvc.demo.jkbuilder.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class TplConfig implements BeanFactoryAware, ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    public static<T> T getBean(Class<T> cls){
        if (beanFactory!=null){
            return beanFactory.getBean(cls);
        }
        return null;
    }
    private static BeanFactory beanFactory;

    private ResourceLoader resourceLoader;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;


    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;

    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        TplTypeRegisterScan scanner = new TplTypeRegisterScan(beanDefinitionRegistry);


        if (this.resourceLoader != null) {
            scanner.setResourceLoader(this.resourceLoader);
        }
        List<String> packages = new ArrayList<>();
        try {
            packages = AutoConfigurationPackages.get(this.beanFactory);
            if (log.isDebugEnabled()) {
                for (String pkg : packages) {
                    log.debug("客户端扫描包:{}", pkg);
                }
            }

        } catch (Exception ex) {
            log.debug("无法获取到AutoConfig基础包", ex);
            packages.add("com");
        }

        scanner.doScan(StringUtils.toStringArray(packages));


    }

}
