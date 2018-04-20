package vip.xuejike.ktpl.libs

import kotlinx.html.stream.createHTML

class JkKtTool{
    fun editBuild(cls:Class<out Any>,method:String){

        val m = cls.getDeclaredMethod(method)
        m.parameters;


    }
}

