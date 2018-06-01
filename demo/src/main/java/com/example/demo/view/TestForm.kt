package com.example.demo.view

import com.alibaba.fastjson.JSON
import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueDataImpl
import com.bidanet.springmvc.demo.jkbuilder.data.JkUploadFile
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import vip.xuejike.ktpl.PageJkKtView
import vip.xuejike.ktpl.libs.*

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
//                 lay-verify="required|
                 it.attributes["lay-verify"]="required|number"

             })
             jkAutoComplete(title = "自动完成",dataList =listData ,
                     placeholder = "输入查询",bind = vo!!::username,selectCall = {
                 it.attributes["sksk"]="sss"
             });
             jkCheckBox(title = "选中",dataList = listData,checkBoxCall ={
                 it.attributes["sss"]="sss"
             } ){}
             jkDate(title = "日期",inputCall = {

                 val map = hashMapOf("type" to "month","range" to "~" );
                 it.attributes["lay-data"]= JSON.toJSONString(map);

             }){

             }


             jkRadio(title = "单选框",dataList = listData,name = "rd",radioCall = {
                 it.attributes["ss"]="ss"
             });

             jkSelect(title = "下拉框",dataList = listData,bind = vo!!::username,selectCall = {
                 it.attributes["sss"]="ccc"
             })
             var f=JkUploadFile("https://www.baidu.com/img/baidu_jgylogo3.gif","ss");
             jkUpload(url = "上传文件URL",value = listOf(f))
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







    }

}
data class TestModel(var username:String?=null,var pwd:String?=null);