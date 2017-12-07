package com.bidanet.springmvc.demo.jkbuilder;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkButton;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkForm;
import com.bidanet.springmvc.demo.jkbuilder.exception.JkBuilderException;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.bidanet.springmvc.demo.jkbuilder.JkBuilder.getFormFieldInfoList;

public class JkFormBuilder {

    private List<FormFieldInfo> formFieldInfoList=new ArrayList<>(1);
    private List<JkButton> btns=new ArrayList<>(1);
    protected JkForm form;
    private String url;
    private List<String> tplFooterList=new ArrayList<>(1);

    private JkFormBuilder(Class formCls, Object obj){
        parseForm(obj, formCls);
    }
    public static JkFormBuilder create(Object formObj){
        return create(formObj,formObj.getClass());
    }
    public static JkFormBuilder create(Object formObj,Class formCls){
        return new JkFormBuilder(formCls,formObj);
    }

    private void parseForm(Object obj,Class formCls){
        form = AnnotationUtils.findAnnotation(formCls, JkForm.class);
        if (form ==null){
            throw new JkBuilderException("没有@JkForm注解");
        }
        url=form.url();
        formFieldInfoList = getFormFieldInfoList(obj,formCls);
        btns.clear();
        Collections.addAll(btns, form.btns());
    }

    /**
     * 添加字段
     * @param formFieldInfo
     * @return
     */
    public JkFormBuilder addField(FormFieldInfo formFieldInfo){
        formFieldInfoList.add(formFieldInfo);
        return this;
    }

    /**
     * 设置新的Form
     * @param obj
     * @param formCls
     * @return
     */
    public JkFormBuilder setForm(Object obj,Class formCls){
       parseForm(obj, formCls);
       return this;
    }
    public JkFormBuilder setForm(Object obj){
        return setForm(obj,obj.getClass());
    }

    /**
     * 添加新Form
     * @param obj
     * @param addIndex
     * @param formCls
     * @return
     */
    public JkFormBuilder addForm(Object obj,Integer addIndex,Class formCls){
        List<FormFieldInfo> list = getFormFieldInfoList(obj,formCls);

        if (addIndex==null){
            formFieldInfoList.addAll(list);
        }else{
            formFieldInfoList.addAll(addIndex,list);
        }
        return this;
    }
    public JkFormBuilder addForm(Object obj,Integer addIndex){
        return addForm(obj,addIndex,obj.getClass());
    }
    public JkFormBuilder addForm(Object obj){
        return addForm(obj,null,obj.getClass());
    }
    public JkFormBuilder addForm(Object obj,Class formCls){
        return addForm(obj, null,formCls);
    }

    public void setActionUrl(String url){
        this.url=url;
    }
    public String buildTpl(Predicate<FormFieldInfo> filter){
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("form",form);
        map.put("btns",btns);
        map.put("url",url);

        if (filter!=null){
            formFieldInfoList= formFieldInfoList.stream().filter(filter).collect(Collectors.toList());
        }

        map.put("formFieldList",formFieldInfoList);

        return FreeMarkerUtils.build("/content/form.ftl",map);
    }
    public String buildTpl(){
        return buildTpl(null);
    }
    public String build(Model model,Predicate<FormFieldInfo> filter){
        model.addAttribute("content",buildTpl(filter));
        model.addAttribute("footerTpl",tplFooterList);
        return "/form_tpl";
    }
    public String build(Model model){
        return build(model,null);
    }

    /**
     * 加底部模板
     * @param tplPath
     * @return
     */
    public JkFormBuilder addTplFooter(String tplPath){
        tplFooterList.add(tplPath);
        return this;
    }

    /**
     * 加底部模板
     * @param tplPaths
     * @return
     */
    public JkFormBuilder addTplFooters(String ... tplPaths){
        Collections.addAll(tplFooterList,tplPaths);
        return this;
    }
}
