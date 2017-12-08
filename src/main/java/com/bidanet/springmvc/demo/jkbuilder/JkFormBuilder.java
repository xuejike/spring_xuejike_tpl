package com.bidanet.springmvc.demo.jkbuilder;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkButton;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkForm;
import com.bidanet.springmvc.demo.jkbuilder.exception.JkBuilderException;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;

import javax.validation.constraints.NotNull;
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

    /**
     * 解析表单
     * @param obj 数据对象
     * @param formCls 表单类型
     */
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
     * @param index 字段位置索引
     * @param formFieldInfo 字段描述
     * @return
     */
    public JkFormBuilder addField(int index,FormFieldInfo formFieldInfo){
        formFieldInfoList.add(index,formFieldInfo);
        return this;
    }

    /**
     * 设置替换为新的Form
     * @param obj 数据对象
     * @param formCls 表单类型
     * @return
     */
    public JkFormBuilder setForm(Object obj,Class formCls){
       parseForm(obj, formCls);
       return this;
    }

    /**
     * 设置替换为新的Form
     * @param obj 数据对象
     * @return
     */
    public JkFormBuilder setForm(Object obj){
        return setForm(obj,obj.getClass());
    }

    /**
     * 添加新Form
     * @param obj 数据对象
     * @param addIndex 新表单插入位置
     * @param formCls 表单类型
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

    /**
     * 添加新Form
     * @param obj 数据对象
     * @param addIndex 新表单插入位置
     * @return
     */
    public JkFormBuilder addForm(@NotNull Object obj,Integer addIndex){
        return addForm(obj,addIndex,obj.getClass());
    }

    /**
     *添加新Form到最下方
     * @param obj 数据对象
     * @return
     */
    public JkFormBuilder addForm(@NotNull Object obj){
        return addForm(obj,null,obj.getClass());
    }

    /**
     *添加新Form到最下方
     * @param obj 数据对象
     * @param formCls 表单类型
     * @return
     */
    public JkFormBuilder addForm(Object obj,@NotNull Class formCls){
        return addForm(obj, null,formCls);
    }

    /**
     * 重新设置URL地址
     * @param url 新URL地址
     */
    public void setActionUrl(String url){
        this.url=url;
    }

    /**
     * 构建生成 表单html内容
     * @param filter 字段过滤器，生成的时候动态去除无效字段
     * @return
     */
    public String buildTpl(Predicate<FormFieldInfo> filter){
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("form",form);
        map.put("btns",btns);
        map.put("url",url);
        List<FormFieldInfo> collect=formFieldInfoList;
        if (filter!=null){
            collect= formFieldInfoList.stream().filter(filter).collect(Collectors.toList());
        }

        map.put("formFieldList",collect);

        return FreeMarkerUtils.build("/content/form.ftl",map);
    }

    /**
     * 构建生成 表单html内容
     * @return
     */
    public String buildTpl(){
        return buildTpl(null);
    }

    /**
     *配合Spring MVC生成HTML模板
     * @param model
     * @param filter
     * @return
     */
    public String build(Model model,Predicate<FormFieldInfo> filter){
        model.addAttribute("content",buildTpl(filter));
        model.addAttribute("footerTpl",tplFooterList);
        return "/form_tpl";
    }

    /**
     * 配合Spring MVC生成HTML模板
     * @param model
     * @return
     */
    public String build(Model model){
        return build(model,null);
    }

    /**
     * 加底部模板
     * @param tplPath 模板路径
     * @return
     */
    public JkFormBuilder addTplFooter(String tplPath){
        tplFooterList.add(tplPath);
        return this;
    }

    /**
     * 加底部模板
     * @param tplPaths 模板路径
     * @return
     */
    public JkFormBuilder addTplFooters(String ... tplPaths){
        Collections.addAll(tplFooterList,tplPaths);
        return this;
    }
}
