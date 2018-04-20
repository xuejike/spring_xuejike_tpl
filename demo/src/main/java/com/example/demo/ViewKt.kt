package com.example.demo

import kotlinx.html.div
import kotlinx.html.input
import kotlinx.html.stream.createHTML
import vip.xuejike.ktpl.libs.jkForm

class TestView{
    var x:String?=null;
    var y:String?=null;



    fun content():String{
       return createHTML().div {
            div {
                jkForm {
                    input {  }
                }
            }
        }
//        return "--";
    }

    override fun toString(): String {
        return content();
    }
}