package com.example.demo.view

import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueDataImpl
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
data class TestModel(var username:String?=null,var pwd:String?=null);