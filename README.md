# 后台模板生成器
---
title: 模板生成器-使用手册
tags: spring
grammar_cjkRuby: true
---
# 1.表单生成器
```java

@JkForm(url = "save",title = "标题")
@Data
public class TestView {

    @JkSortIndex(0)
    @JkFormField(type = HiddenFormFieldImpl.class)
    public Long id;
    @JkSortIndex(1)
    @JkFormField(title = "名称",type = TextFormFieldImpl.class)
    private String name;

    @JkSortIndex(2)
    @JkPlaceholder("哈哈")
    @JkTitle("查询")
    @JkDataSource(type = JkSourceType.enumType)
    @JkSelectFormField()
    private JkSourceType name1=JkSourceType.stringArray;


    @JkSortIndex(3)
    @JkTitle("日期")
    @JkDateFormField()
    private String name2;
    @JkSortIndex(4)
    @JkRadioFormField({"测试1-123","测试2-223","测试3-323","测试4-423"})
    private String name3="123";

    @JkSortIndex(5)
    @JkTextAreaFormField
    @JkTitle("文本域")
    private String name4="123";

    @JkSortIndex(6)
    @JkCheckBoxFormField({"测试1-123","测试2-223","测试3-323","测试4-423"})
    @JkTitle("复选框")
    private String[] name5={"123","223"};
}

```
## @JkForm 表单注解：
```java
@JkForm(url = "save",title = "标题")

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JkForm {
    /**
     * form表单请求地址
     * @return
     */
    String url() default "";

    /**
     * form表单标题
     * @return
     */
    String title() default "";

    /**
     * form表单CSS的class
     * @return
     */
    String[] cssClass() default {};

    /**
     * form表单属性
     * @return
     */
    String[] attrs() default {};

    /**
     * 按钮定义
     * @return
     */
    JkButton[] btns() default {
        @JkButton(value = "提交",type = JkButtonType.submit)
    };

}
```
## @JkFormField 表单元素
```java
public @interface JkFormField {
    String title() default "";
    String[] cssClass() default {};
    String placeholder() default "";
    String[] attrs() default {};

    /**
     * form表单实现类
     * @return
     */
    Class<? extends FormFieldHtml> type();
}
```

## @JkSortIndex 表单排序
```java
public @interface JkSortIndex {
    int value() default 0;
}
```

## @JkDateFormField 日期控件

## @JkRadioFormField 单选框(支持数据源注解)
```java
public @interface JkRadioFormField {
    /**
     * select 数据，使用分隔符拆分，text是前面的值，value是后面的值
     * @return
     */
    String[] value() default {};

    /**
     * 分隔符
     * @return
     */
    String divider() default "-";
}
//示例
    @JkSortIndex(4)
    @JkRadioFormField({"测试1-123","测试2-223","测试3-323","测试4-423"})
    private String name3="123";
```
##  @JkDataSource 数据源（目前支持：数组、枚举）
```java
public enum JkSourceType implements JkNameValueData {
    /**
     * 字符串数组
     */
    stringArray,
    /**
     * 枚举
     */
    enumType,
    /**
     * Url地址
     */
    url;
}
public @interface JkDataSource {
    /**
     * 数据源类型
     * @return
     */
    JkSourceType type() default JkSourceType.stringArray;
    /**
     * 数组数据
     * @return
     */
    String[] arrayData() default {};

    /**
     * 分割线
     * @return
     */
    String arrayDivider() default "-";
    String url() default "";
}
```
## @JkSelectFormField 下拉框（支持数据源注解）
```java
public @interface JkSelectFormField {
    /**
     * select 数据，使用分隔符拆分，text是前面的值，value是后面的值
     * @return
     */
    String[] value() default {};
    String divider() default "-";

}
//示例
   @JkSortIndex(2)
    @JkPlaceholder("哈哈")
    @JkTitle("查询")
    @JkDataSource(type = JkSourceType.enumType)
    @JkSelectFormField()
    private JkSourceType name1=JkSourceType.stringArray;

```
## @JkCheckBoxFormField 复选框（支持数据源注解）
## @JkPlaceholder 提示注解
## @JkTitle 标题注解
## @Button 按钮注解
```java
public @interface JkButton {
    String value() default "按钮";

    /**
     * 按钮事件
     * @return
     */
    String event() default "";

    String cssClass() default "";

    /**
     * 按钮类型
     * @return
     */
    JkButtonType type() default JkButtonType.diy;

    /**
     * 请求地址
     * @return
     */
    String url() default "";

    /**
     * 选项
     * @return
     */
    String option() default "{}";

}

public enum JkButtonType {
    /**
     * 打开tab
     */
    tab,
    /**
     * 打开dialog
     */
    dialog,
    /**
     * 发送Ajax请求
     */
    doAjax,
    /**
     * 窗口
     */
    windows,

    submit,
    reset,
    diy
}
```

# 2.表格生成器

```java
@JkTable
@JkDataSource(type = JkSourceType.url,url = "tableData")
public class TableView {

    private Long id;
    @JkColumn(title = "标题")
    @JkSortIndex(0)
    private String userName;
    @JkColumn(title = "密码")
    @JkSortIndex(1)
    private String pwd;
    @JkColumn(title = "角色")
    @JkSortIndex(2)
    private String role;
    @JkColumn(title = "工具栏",fixed = JkColumnAlign.right)
    @JkSortIndex(3)
    @JkToolBar(btns = {
            @JkButton(value = "编辑",
                    type = JkButtonType.dialog,
                    url = "test",
                    option = "{title:'ss',area:['900px','600px']}"
                     )
    })
    private String tool;


}

```
# 3.事件定义

