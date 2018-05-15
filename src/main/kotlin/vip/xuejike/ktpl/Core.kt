package vip.xuejike.ktpl

import com.bidanet.springmvc.demo.jkbuilder.FreeMarkerUtils
import java.util.ArrayList
import java.util.HashMap


abstract class JkKtView {
    @JvmField
    protected var tpl: String? = null
    @JvmField
    protected var rootPath="/webjars/jktpl"
    @JvmField
    protected var map = HashMap<String, Any>()
    private val headList = ArrayList<String>()
    private var footerList=ArrayList<String>()



    abstract fun content(): String
    fun head(): String {
        val headBuilder = StringBuilder()
        for (link in headList) {
            headBuilder.append(link)
                    .append("\n")
        }

        return headBuilder.toString()
    }

    fun footer(): String {
        var footerBuilder=StringBuffer();
        for (link in footerList){
            footerBuilder.append(link).append("\n");
        }
        return footerBuilder.toString()
    }

    fun addHeadJsFile(url: String): JkKtView {


        headList.add("<script type=\"text/javascript\" src=\"$url\"></script>")
        return this
    }
    fun addFooterJsFile(url:String):JkKtView{
        footerList.add("<script type=\"text/javascript\" src=\"$url\"></script>")
        return this
    }

    fun addCssFile(url: String): JkKtView {
        headList.add("<link rel=\"stylesheet\" href=\"$url\">")
        return this
    }


    fun toHtml(): String {
        if (tpl == null) {
            return content()
        }

        map["content"] = content()
        map["head"] = head()
        map["footer"] = footer()
        map["vipRoot"]=rootPath;
        return FreeMarkerUtils.build(tpl, map)

    }

    override fun toString(): String {
        return toHtml()
    }
}
