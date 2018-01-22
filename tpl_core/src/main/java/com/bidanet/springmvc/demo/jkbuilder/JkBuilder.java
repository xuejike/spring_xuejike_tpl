package com.bidanet.springmvc.demo.jkbuilder;

import com.alibaba.fastjson.JSON;

import com.bidanet.springmvc.demo.jkbuilder.annotation.*;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkColumnAlign;
import com.bidanet.springmvc.demo.jkbuilder.config.TplConfig;
import com.bidanet.springmvc.demo.jkbuilder.exception.JkBuilderException;
import com.bidanet.springmvc.demo.jkbuilder.type.*;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 模板生成器
 * @author xuejike
 */
public class JkBuilder {



    public static String parseForm(Object obj)  {
        JkForm form = AnnotationUtils.findAnnotation(obj.getClass(), JkForm.class);
        if (form==null){
            throw new JkBuilderException("没有JkForm注解");
        }
        ArrayList<FormFieldInfo> formFieldInfoList = getFormFieldInfoList(obj);

        HashMap<String, Object> map = new HashMap<>();
        map.put("form",form);
        JkButton[] btns = form.btns();
        map.put("btns",btns);
        map.put("formFieldList",formFieldInfoList);

        return FreeMarkerUtils.build("/content/form.ftl",map);


    }
    protected static ArrayList<FormFieldInfo> getFormFieldInfoList(Object obj){
        return getFormFieldInfoList(obj,obj.getClass());
    }
    protected static ArrayList<FormFieldInfo> getFormFieldInfoList(Object obj,Class cls) {
        if(obj==null){
            return new ArrayList<FormFieldInfo>(0);
        }
        JkDisable jkDisable = AnnotationUtils.getAnnotation(cls, JkDisable.class);

        List<Field> fields = getAllField(cls);
        ArrayList<FormFieldInfo> formFieldInfoList = new ArrayList<>(fields.size());
        try{
            for (Field field : fields) {
                //填充值
                FormFieldInfo formFieldInfo = parseFormField(field);
                if (formFieldInfo!=null){
                    formFieldInfo.setVal(PropertyUtils.getProperty(obj,field.getName()));
                    formFieldInfoList.add(formFieldInfo);
                    if (jkDisable!=null){
                        if (formFieldInfo.getType() instanceof AbsBaseFormField){
                            ((AbsBaseFormField) formFieldInfo.getType()).setJkDisable(jkDisable);
                        }
                    }
                }

            }
        }catch (Exception ex){
            throw new JkBuilderException("解析数据异常",ex);
        }

        formFieldInfoList.sort((o1, o2) -> o1.getSort()-o2.getSort());
        return formFieldInfoList;
    }

    /**
     * 对字段进行分组
     * @param fieldInfoList
     * @return
     */
    protected static List<FormFieldGroup> fieldsGroup(List<FormFieldInfo> fieldInfoList){
        ArrayList<FormFieldGroup> list = new ArrayList<>();
        HashMap<String, FormFieldGroup> map = new HashMap<>();

        for (FormFieldInfo info : fieldInfoList) {
            FormFieldGroup nowGroup;
            if (info.getGroupId()==null||info.getGroupId().isEmpty()){
                nowGroup=new FormFieldGroup(info.getGroupId(),info);
                nowGroup.setGroup(info.getGroup());
                list.add(nowGroup);
            }else{
                nowGroup = map.get(info.getGroupId());
                if (nowGroup==null){
                    nowGroup=new FormFieldGroup(info.getGroupId(),info);
                    nowGroup.setGroup(info.getGroup());
                    list.add(nowGroup);
                    map.put(info.getGroupId(),nowGroup);
                }else{
                    nowGroup.addItem(info);
                }
            }

        }
        list.forEach(group->{
            if (group.getItems().size()>1){
                group.getItems().forEach(item->{

                });
            }
        });
        return list;

    }
    public static String parseTable(Class tableCls,Object searchObj,String urlParams){

        HashMap<String, Object> map = new HashMap<>();
        JkTable table = AnnotationUtils.findAnnotation(tableCls, JkTable.class);
        if (table==null){
            throw new JkBuilderException("JkTable 注解未发现");
        }
        JkDataSource jkDataSource = AnnotationUtils.findAnnotation(tableCls, JkDataSource.class);
        if (jkDataSource!=null){
            if(urlParams==null){
                map.put("url",jkDataSource.url());
            }else{
                map.put("url",jkDataSource.url()+urlParams);
            }

        }
        Field[] tableField = tableCls.getDeclaredFields();
        ArrayList<List<TableColumnInfo>> headList = new ArrayList<>(2);
        ArrayList<TableColumnInfo> list = new ArrayList<>(tableField.length);
        TableColumnInfo idColumn = new TableColumnInfo();

        idColumn.setFixed(JkColumnAlign.left.toString());
        idColumn.setType("checkbox");
        list.add(idColumn);
        headList.add(list);
        HashMap<String, JkButton[]> toolMap = new HashMap<>();
        for (Field field : tableField) {
            TableColumnInfo tableColumnInfo = parseTableColumn(field);
            if (tableColumnInfo!=null){
                list.add(tableColumnInfo);
                if (tableColumnInfo.getToolbar()!=null){
                    JkToolBar jkToolBar = AnnotationUtils.findAnnotation(field, JkToolBar.class);
                    toolMap.put(tableColumnInfo.getToolbar().substring(1),jkToolBar.btns());
                }
            }
        }
        //排序
        list.sort((o1,o2)-> o1.getSortIndex()-o2.getSortIndex());
        if(searchObj != null){
            JkForm jkForm = searchObj.getClass().getAnnotation(JkForm.class);
            if (jkForm!=null){
                map.put("searchBtn",jkForm.btns());
                ArrayList<FormFieldInfo> formFieldInfoList = getFormFieldInfoList(searchObj);
                map.put("searchList",formFieldInfoList);
            }

        }


        map.put("head", JSON.toJSONString(headList));
        map.put("toolbar",toolMap);



        return FreeMarkerUtils.build("/content/table.ftl",map);


    }
    protected static FormFieldInfo parseFormField(Field field) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        JkFormField formField = AnnotationUtils.findAnnotation(field, JkFormField.class);

        if (formField!=null){
            //基础信息解析
            FormFieldInfo info = new FormFieldInfo();
            info.setId("id_"+field.getName());
            info.setName(field.getName());
            info.setTitle(formField.title());
            info.setAttrs(formField.attrs());
            info.setCssClass(formField.cssClass());
            info.setValCls(field.getType());
            JkFormGroup jkFormGroup = AnnotationUtils.getAnnotation(field, JkFormGroup.class);
            if (jkFormGroup!=null){
                info.setGroupId(jkFormGroup.value());
                info.setGroup(jkFormGroup);
            }

            //排序
            JkSortIndex jkSortIndex = AnnotationUtils.findAnnotation(field, JkSortIndex.class);
            if (jkSortIndex!=null){
                info.setSort(jkSortIndex.value());
            }else{
                info.setSort(0);
            }
            //类型二次解析
            Class<? extends FormFieldHtml> type = formField.type();

            FormFieldHtml formFieldHtml = type.newInstance();
            info.setType(formFieldHtml);

            //注解填充

            List<Field> formFieldHtmlFields = getAllField(type);

            for (Field formFieldHtmlField : formFieldHtmlFields) {
                Class<?> valType = formFieldHtmlField.getType();
                if (valType.isAnnotation()){
                    Annotation annotation = AnnotationUtils.findAnnotation(field, (Class<? extends Annotation>) valType);
                    if (annotation!=null){
                        PropertyUtils.setProperty(formFieldHtml,formFieldHtmlField.getName(),annotation);
                    }
                }
            }


            return info;


        }
        return null;
    }


    protected static TableColumnInfo parseTableColumn(Field field){
        TableColumnInfo tableColumnInfo = new TableColumnInfo();

        JkColumn jkColumn = field.getAnnotation(JkColumn.class);
        if (jkColumn==null){
            return  null;
        }
        tableColumnInfo.setField(field.getName());
        if (!jkColumn.title().isEmpty()){
            tableColumnInfo.setTitle(jkColumn.title());
        }
        tableColumnInfo.setSort(jkColumn.sort());
        tableColumnInfo.setColspan(jkColumn.colspan());
        tableColumnInfo.setRowspan(jkColumn.rowspan());
        JkToolBar jkToolBar = field.getAnnotation(JkToolBar.class);
        if (jkToolBar!=null){
            tableColumnInfo.setToolbar(jkToolBar.value());
        }
        JkSortIndex jkSortIndex = field.getAnnotation(JkSortIndex.class);
        if (jkSortIndex!=null){
            tableColumnInfo.setSortIndex(jkSortIndex.value());
        }
        if(jkColumn.fixed()!= JkColumnAlign.none){
            tableColumnInfo.setFixed(jkColumn.fixed().toString());
        }
        if (!jkColumn.templet().isEmpty()){
            tableColumnInfo.setTemplet(jkColumn.templet());
        }
        if (jkColumn.width()!=0){
            tableColumnInfo.setWidth(jkColumn.width());
        }
        return tableColumnInfo;
    }

    protected static List<Field> getAllField(Class cls){
        ArrayList<Field> list = new ArrayList<>();
        while (cls !=Object.class){
            Field[] fields = cls.getDeclaredFields();
            Collections.addAll(list,fields);
            cls=cls.getSuperclass();
        }
        return list;

    }


    public static String getTplString(String[] strings){
        if (strings!=null){
            StringBuilder sb=new StringBuilder(" ");
            for (String css : strings) {
                sb.append(css).append(" ");
            }
            return sb.toString();
        }
        return "";
    }
    public static String tableView(Class tableCls, Object searchTool, Model model,String urlParams,String... loadFooter)
    {

        return JkTableBuilder.create(tableCls)
                .addHeaderTool(searchTool).addQueryString(urlParams)
                .addTplFooters(loadFooter).build(model);

//        model.addAttribute("content",parseTable(tableCls,searchTool,urlParams));
//
//        model.addAttribute("footerTpl",loadFooter);
//        return "/table_tpl";
    }

    public static String formView(Object formObj,Model model,String... loadFooter){
        return JkFormBuilder.create(formObj).addTplFooters(loadFooter).build(model);
//        return "/form_tpl";
    }





    public static void main(String[] args){
        String s = StringEscapeUtils.escapeJavaScript("{\"\\}");
        System.out.println(s);
    }
}
