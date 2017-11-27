package com.bidanet.springmvc.demo.jkbuilder;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * Created by xuejike on 2017/7/19.
 */
public class FreeMarkerUtils {

    protected static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_22);
        try {

            configuration.setDirectoryForTemplateLoading(new ClassPathResource("\\jk_tpl\\").getFile());
            configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_22));

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static Template getTemplate(String file){
        try {
            return configuration.getTemplate(file);
        } catch (IOException e) {
            throw new  RuntimeException(e);
        }
    }
    public static String build(String file,Map<String,Object> data){

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        Template template = getTemplate(file);
        try {
            template.process(data,writer);
        } catch (TemplateException | IOException e) {
            throw new  RuntimeException(e);
        }
        return outputStream.toString();

    }


}
