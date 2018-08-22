package com.example.demo

import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.input
import kotlinx.html.stream.createHTML
import vip.xuejike.ktpl.libs.commonFooter
import vip.xuejike.ktpl.libs.commonHeader

fun loadTpl(){
    commonHeader.add("ss")
    commonFooter.add("ff")
}

var t=loadTpl()
fun FlowContent.jkXuejike(){

}