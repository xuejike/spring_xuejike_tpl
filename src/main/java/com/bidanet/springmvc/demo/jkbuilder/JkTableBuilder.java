package com.bidanet.springmvc.demo.jkbuilder;

import com.alibaba.fastjson.JSON;
import com.bidanet.springmvc.demo.jkbuilder.annotation.*;
import com.bidanet.springmvc.demo.jkbuilder.annotation.type.JkColumnAlign;
import com.bidanet.springmvc.demo.jkbuilder.exception.JkBuilderException;
import com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo;
import com.bidanet.springmvc.demo.jkbuilder.type.TableColumnInfo;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.bidanet.springmvc.demo.jkbuilder.JkBuilder.getFormFieldInfoList;
import static com.bidanet.springmvc.demo.jkbuilder.JkBuilder.parseTableColumn;

public class JkTableBuilder {

    protected String url;
    private String queryString="";
    private String tableId="";

    private ArrayList<List<TableColumnInfo>> headList;
    private HashMap<String, JkButton[]> toolMap;
    private List<String> tplFooterList=new ArrayList<>(1);
    private List<String> tplHeaderTool=new ArrayList<>(1);
    private String finishFun;

    private List data;
    private JkButton[] searchBtn;
    private ArrayList<FormFieldInfo> searchList;

    private JkTableBuilder(Class tableCls){
        parseTable(tableCls);
    }
    public static JkTableBuilder create(Class tableCls){
        JkTableBuilder builder = new JkTableBuilder(tableCls);

        return builder;
    }

    /**
     * 解析表格
     * @param tableCls
     */
    private void parseTable(Class tableCls){
        HashMap<String, Object> map = new HashMap<>();
        //解析头
        JkTable table = AnnotationUtils.findAnnotation(tableCls, JkTable.class);
        if (table==null){
            throw new JkBuilderException("JkTable 注解未发现");
        }
        tableId=table.id();
        JkDataSource jkDataSource = AnnotationUtils.findAnnotation(tableCls, JkDataSource.class);
        if (jkDataSource!=null){
            url = jkDataSource.url();
        }


        //解析表格头

        List<Field> tableField = JkBuilder.getAllField(tableCls);
        headList = new ArrayList<>(2);
        ArrayList<TableColumnInfo> list = new ArrayList<>(tableField.size());
        TableColumnInfo idColumn = new TableColumnInfo();

        idColumn.setFixed(JkColumnAlign.left.toString());
        idColumn.setType("checkbox");
        list.add(idColumn);
        headList.add(list);
        toolMap = new HashMap<>();
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

    }

    /**
     * 添加头部工具栏
     * @param headerTool
     * @return
     */
    public JkTableBuilder addHeaderTool(Object headerTool){
        if (headerTool==null){
            return this;
        }
        JkForm jkForm = headerTool.getClass().getAnnotation(JkForm.class);
        if (jkForm!=null){
            searchBtn = jkForm.btns();
            searchList = getFormFieldInfoList(headerTool);

        }else{
            throw new JkBuilderException("缺少 JkForm 注解");
        }
        return this;
    }
    /**
     * 构建-生成模板Html代码
     * @return
     */
    public String buildTpl(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("head", JSON.toJSONString(headList));
        map.put("toolbar",toolMap);
        map.put("searchBtn",searchBtn);
        map.put("searchList",searchList);
        if (data==null){
            if (url!=null){
                map.put("url",url+queryString);
            }
        }else{
            map.put("data",JSON.toJSONString(data));
        }
        map.put("finishFun",finishFun);
        map.put("tableId",tableId);
        return FreeMarkerUtils.build("/content/table.ftl",map);
    }

    /**
     *构建结合Spring MVC模板生成页面
     * @param model
     * @return
     */
    public String build(Model model){
        model.addAttribute("content",buildTpl());

        model.addAttribute("footerTpl",tplFooterList);
        return "/table_tpl";
    }


    /**
     * 设置新的数据URL地址
     * @param url 新URL地址
     * @return
     */
    public JkTableBuilder setUrl(String url){
        this.url=url;
        return this;
    }

    /**
     * 添加查询参数
     * @param queryString
     * @return
     */
    public JkTableBuilder addQueryString(String queryString){
        this.queryString=queryString;
        return this;
    }

    /**
     * 加底部模板
     * @param tplPath
     * @return
     */
    public JkTableBuilder addTplFooter(String tplPath){
        tplFooterList.add(tplPath);
        return this;
    }

    /**
     * 加底部模板
     * @param tplPaths
     * @return
     */
    public JkTableBuilder addTplFooters(String ... tplPaths){
        Collections.addAll(tplFooterList,tplPaths);
        return this;
    }

    /**
     * 添加 表格上方模板
     * @param tplPath
     * @return
     */
    public JkTableBuilder addTplHeaderTool(String tplPath){
        tplHeaderTool.add(tplPath);
        return this;
    }

    /**
     * 设置静态数据，当静态数据有效时，URL自动失效
     * @param data 表格数据
     * @return
     */
    public JkTableBuilder setData(List data){
        this.data=data;
        return this;
    }

    /**
     * 设置表格渲染完成后的回调
     * @param finishFun
     * @return
     */
    public JkTableBuilder setFinishFun(String finishFun) {
        this.finishFun = finishFun;
        return this;
    }
}
