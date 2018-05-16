[![](https://jitpack.io/v/xuejike/spring_xuejike_tpl.svg)](https://jitpack.io/#xuejike/spring_xuejike_tpl)

# 1.基于Kotlin的Layui 表单以及界面生成器
# 1.1 环境搭建
```kotlin

    @RequestMapping("/form")
    @ResponseBody
    public String form(){
//        FormJkKtView view = new FormJkKtView();
        TestFormView view = new TestFormView();
        view.setVo(new TestModel());
        return view.toHtml();
    }
```

# 1.2 基本使用


```kotlin
class TestFormView: PageJkKtView() {
    var vo:TestModel?=null;

    override fun content(): String {
     return createHTML().div {
//         JkNameValueDataImpl::getName.call()
         vo=TestModel("value-3")
         var listData=arrayListOf<JkNameValueDataImpl>();
         for (i in 0..10){
             listData.add(JkNameValueDataImpl("name${i}","value-${i}"));
         }
         jkForm {
             jkInput(bind = vo!!::username,title = "哇哈哈", type = InputType.text,inputCall =
             {
                 //修改input属性
                 it.id="sss"
//                 lay-verify="required|
                 it.attributes["lay-verify"]="required"
                 it.attributes["dd"]="1s1s1s"
             })
             jkAutoComplete(title = "自动完成",dataList =listData ,
                     placeholder = "输入查询",bind = vo!!::username,selectCall = {
                 it.attributes["sksk"]="sss"
             });
             jkCheckBox(title = "选中",dataList = listData,checkBoxCall ={
                 it.attributes["sss"]="sss"
             } ){}
             jkDate(title = "日期",inputCall = {

             }){

             }
             jkRadio(title = "单选框",dataList = listData,name = "rd",radioCall = {
                 it.attributes["ss"]="ss"
             });

             jkSelect(title = "下拉框",dataList = listData,bind = vo!!::username,selectCall = {
                 it.attributes["sss"]="ccc"
             })
             jkUpload()
             jkButton("提交",type = JkButtonType.ajax_submit)


         }

         jkForm(method = FormMethod.get){
             jkFormItem(){
                 jkInput("用户名",name = "username",formItem = false,inline = true)
                 jkInput("用户名",name = "pwd",formItem = false,inline = true)
             }
             jkFormItem(){
                 jkButton("查询")
             }

         }

         jkTable(linkedMapOf(
                 "用户名" to JkTableCol(attrsCall = { mapOf("width" to "450px")}){
                     img { src="http://www.baidu.com/img/bd_logo1.png" }
                 },
                 "操作" to JkTableCol{
                     a {
                         href="http://www.baidu.com"
                         classes+="layui-btn"
                         text("百度")
                     }
                     jkButton(type = JkButtonType.dialog.name,aLink = true,
                             url = "http://www.baidu.com") {
                         attributes["style"]="width:200px"
                     }
                 }
         ),dataList = listData)
         jkPage(1,100)

        }


    }

}
```
# 1.3 其它功能
## 1.3.1 按钮使用

```kotlin
 //按钮使用

         //请求 url地址
         jkButton("Ajax按钮",type = JkButtonType.doAjax,url = "http://www.baidu.com?dd=")
         //打开对话框 加载 url地址
         jkButton("对话框",type = JkButtonType.dialog,url = "http://www.baidu.com?dd=")

         jkButton(
                 "表单 ajax提交",
                 type = JkButtonType.ajax_submit,
                 option = hashMapOf("" to "")
         )
         jkButton("带确认的提示框",type = JkButtonType.confirm,url = "",option = "确认删除？")

        }
```
## 1.3.2 表单验证
具体表单验证规则 参考 layui
```kotlin

  jkInput(bind = vo!!::username,title = "哇哈哈", type = InputType.text,inputCall =
             {
                 //修改input属性
//                 lay-verify="required|
                 it.attributes["lay-verify"]="required"
                 
             })
```

# 1.4 扩展 html组件
```kotlin

fun FlowContent.jkTest(block: FlowContent.() -> Unit){
    div { 
        
    }
    block()
}

```






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
```java

@JkForm(btns = {
        @JkButton(value = "搜索",type = JkButtonType.submit),
        @JkButton(value = "测试",type = JkButtonType.dialog,url = "test?id={userName}")
})
@Data
public class TableHeader {
    @JkSortIndex(0)
    @JkFormField(title = "用户名",type = TextFormFieldImpl.class)
    private String username;
    @JkSortIndex(1)
    @JkFormField(title = "密码",type = TextFormFieldImpl.class)
    private String pwd;
}
```
# 3.事件定义
事件定义和处理
```java
//事件定义
 @JkButton(value = "搜索",type = JkButtonType.diy,event = "test")
```


```javascript
//事件处理
jkBuilderConfig.event={
  test:function (event, data, row) {
      console.log("处理-->"+event)
  }
};
```

# 4.API文档

## 4.1 表格生成器（JkTableBuilder）

### `private void parseTable(Class tableCls)`

解析表格

 * **Parameters:** `tableCls` — 

### `public JkTableBuilder addHeaderTool(Object headerTool)`

添加头部工具栏

 * **Parameters:** `headerTool` — 
 * **Returns:** 

### `public String buildTpl()`

构建-生成模板Html代码

 * **Returns:** 

### `public String build(Model model)`

 * **Parameters:** `model` — 
 * **Returns:** 

### `public JkTableBuilder setUrl(String url)`

设置新的数据URL地址

 * **Parameters:** `url` — 新URL地址
 * **Returns:** 

### `public JkTableBuilder addQueryString(String queryString)`

添加查询参数

 * **Parameters:** `queryString` — 
 * **Returns:** 

### `public JkTableBuilder addTplFooter(String tplPath)`

加底部模板

 * **Parameters:** `tplPath` — 
 * **Returns:** 

### `public JkTableBuilder addTplFooters(String ... tplPaths)`

加底部模板

 * **Parameters:** `tplPaths` — 
 * **Returns:** 

### `public JkTableBuilder addTplHeaderTool(String tplPath)`

添加 表格上方模板

 * **Parameters:** `tplPath` — 
 * **Returns:** 

### `public JkTableBuilder setData(List data)`

设置静态数据，当静态数据有效时，URL自动失效

 * **Parameters:** `data` — 表格数据
 * **Returns:** 

### `public JkTableBuilder setFinishFun(String finishFun)`

设置表格渲染完成后的回调

 * **Parameters:** `finishFun` — 
 * **Returns:** 
## 4.2 表单生成器（JkFormBuilder）

### `private void parseForm(Object obj,Class formCls)`

解析表单

 * **Parameters:**
   * `obj` — 数据对象
   * `formCls` — 表单类型

### `public JkFormBuilder addField(int index,FormFieldInfo formFieldInfo)`

添加字段

 * **Parameters:**
   * `index` — 字段位置索引
   * `formFieldInfo` — 字段描述
 * **Returns:** 

### `public JkFormBuilder setForm(Object obj,Class formCls)`

设置替换为新的Form

 * **Parameters:**
   * `obj` — 数据对象
   * `formCls` — 表单类型
 * **Returns:** 

### `public JkFormBuilder setForm(Object obj)`

设置替换为新的Form

 * **Parameters:** `obj` — 数据对象
 * **Returns:** 

### `public JkFormBuilder addForm(Object obj,Integer addIndex,Class formCls)`

添加新Form

 * **Parameters:**
   * `obj` — 数据对象
   * `addIndex` — 新表单插入位置
   * `formCls` — 表单类型
 * **Returns:** 

### `public JkFormBuilder addForm(@NotNull Object obj,Integer addIndex)`

添加新Form

 * **Parameters:**
   * `obj` — 数据对象
   * `addIndex` — 新表单插入位置
 * **Returns:** 

### `public JkFormBuilder addForm(@NotNull Object obj)`

 * **Parameters:** `obj` — 数据对象
 * **Returns:** 

### `public JkFormBuilder addForm(Object obj,@NotNull Class formCls)`

 * **Parameters:**
   * `obj` — 数据对象
   * `formCls` — 表单类型
 * **Returns:** 

### `public void setActionUrl(String url)`

重新设置URL地址

 * **Parameters:** `url` — 新URL地址

### `public String buildTpl(Predicate<FormFieldInfo> filter)`

构建生成 表单html内容

 * **Parameters:** `filter` — 字段过滤器，生成的时候动态去除无效字段
 * **Returns:** 

### `public String buildTpl()`

构建生成 表单html内容

 * **Returns:** 

### `public String build(Model model,Predicate<FormFieldInfo> filter)`

 * **Parameters:**
   * `model` — 
   * `filter` — 
 * **Returns:** 

### `public String build(Model model)`

配合Spring MVC生成HTML模板

 * **Parameters:** `model` — 
 * **Returns:** 

### `public JkFormBuilder addTplFooter(String tplPath)`

加底部模板

 * **Parameters:** `tplPath` — 模板路径
 * **Returns:** 

### `public JkFormBuilder addTplFooters(String ... tplPaths)`

加底部模板

 * **Parameters:** `tplPaths` — 模板路径
 * **Returns:** 
