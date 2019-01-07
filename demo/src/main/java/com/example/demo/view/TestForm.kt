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
        headList.add("<meta name=\"viewport\" content=\"width=device-width\">")
        footerList.add("<script></script>")
        addCssFile("/test.css")
        addHeadJsFile("/head.js")
        addFooterJsFile("/foot.js")
     return createHTML().div {
        vo= TestModel("ss")
         val list = listOf(vo, vo, vo)
//         jkForm {
//             jkInput(bind = vo!!::username,title = "文本框", type = InputType.text,inputCall =
//             {
//                 it.attributes["lay-verify"]="required|number"
//
//             })
//            jkFormTitle(){
//
//                jkButton("提交",type = JkButtonType.ajax_submit)
//
//            }
//         }

         jkTable(linkedMapOf(
                 "index" to JkTableCol{ text("ssssssssssssssssss") },
                 "index1" to JkTableCol{ text("ssssssssssssss") },
                 "index2" to JkTableCol{ text("ssssssssssssss") },
                 "index3" to JkTableCol{ text("ssssssssssssss") },
                 "操作" to JkTableCol(width = "300px"){
                     jkButton("按钮1")
                     jkButton("按钮2")
                     jkButton("按钮3")
                 }),list){

            }
         }


    }

}
data class TestModel(var username:String?=null,var pwd:String?=null);