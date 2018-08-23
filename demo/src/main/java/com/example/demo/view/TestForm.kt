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
         jkForm {
             jkInput(bind = vo!!::username,title = "文本框", type = InputType.text,inputCall =
             {
                 it.attributes["lay-verify"]="required|number"

             })
            jkFormTitle(){

                jkButton("提交",type = JkButtonType.ajax_submit)

            }
         }

         }


    }

}
data class TestModel(var username:String?=null,var pwd:String?=null);