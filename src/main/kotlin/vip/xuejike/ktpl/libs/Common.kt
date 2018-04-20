package vip.xuejike.ktpl.libs

import com.alibaba.fastjson.JSON
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkDisable
import com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueData
import kotlinx.html.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap
import kotlin.reflect.KCallable
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty1

fun FlowContent.jkForm(title:String="", action:String="",
                       method:FormMethod=FormMethod.post, block:FlowContent.(FORM)->Unit={}){
    form(action = action,method = method){
        this.classes+="layui-form"
        block(this)
    }
}

fun FlowContent.jkFormItem(block: FlowContent.() -> Unit={}){
    div {
        this.classes+="layui-form-item"
        block(this)
    }
}
fun FlowContent.jkFormTitle(title: String="",
                            inline:Boolean=false,
                            formItem:Boolean=true,
                            block:(DIV) -> Unit={}){
    var p={
        label {
            this.classes+="layui-form-label"
            this.text(title)
        }
        div {
            if (inline){
                this.classes+="layui-input-inline"
            }else{
                this.classes+="layui-input-block"
            }

            block(this)
        }
    }
    if (formItem){
        jkFormItem(){
            p()
        }
    }else{
        p();
    }
}

/**
 * input输入框
 */
fun FlowContent.jkInput(title:String="",formItem:Boolean=true,placeholder:String="",name:String="", value:Any?=null,
                        bind: KMutableProperty0<out Any?>?=null, type:InputType=InputType.text,
                        inline:Boolean=false, inputCall:(INPUT)->Unit={},block: FlowContent.() -> Unit={}
                        ){

    jkFormTitle(title, inline, formItem) {
        input {

            this.type=type;
            this.autoComplete=false
            this.placeholder=placeholder
            this.classes+="layui-input"


            var info=getBindInfo(
                    bind,
                    name,
                    value
            )
            this.name=info.name;
            this.value=info.valueString;
            inputCall(this)
        }
        block(this)
    }
}
data class JkFormItemConfig(var title: String="");
/**
 * 自动完成
 */
fun FlowContent.jkAutoComplete(title: String="",
                               inline:Boolean=false,
                               formItem:Boolean=true,
                               bind: KMutableProperty0<out Any?>?=null,
                               name: String?=null,
                               value: Any?=null,
                               remote:Boolean=false,
                               url:String="",
                               search:Boolean=true,
                               dataList:List<out JkNameValueData>?=null,
                               beanCls:Class<Any>?=null,
                               placeholder:String?=null,
                               selectCall:(SELECT)->Unit={},
                               block: FlowContent.() -> Unit={} ){
    jkFormTitle(title, inline,formItem){
//        this.+="jk-search-select"
        if (remote){
            it.classes+="jk-search-select"
        }


        select {

            var info=getBindInfo(bind, name, value);
            this.name=info.name;

            if (search){
                this.attributes["lay-search"]=""
            }

            if (remote){
                this.attributes["data-url"]=url;
                this.attributes["data-bean-cls"]= beanCls!!.name
            }
            if (placeholder!=null){
                option {
                    this.value=""
                    this.text(placeholder)
                }
            }
            if(dataList!=null){
                for (item in dataList){
                    option{
                        this.value=item.value
                        this.text(item.name)
                        if (item.value==info.valueString){
                            this.selected=true
                        }
                    }
                }
            }

            selectCall(this)


        }
        block(this);

    }
}
fun FlowContent.jkCheckBox(
        dataList:List<out JkNameValueData>,
        title: String="",
                           inline:Boolean=false,
                           formItem:Boolean=true,
                           bind: KMutableProperty0<out Any?>?=null,
                           name: String?=null,
                           value: Any?=null,
                            checkBookCall:(INPUT)->Unit={},
                           block: FlowContent.() -> Unit={}){

    jkFormTitle (title, inline, formItem){
        for (item in dataList){

            input(type = InputType.checkBox) {
                val info = getBindInfo(bind, name, value)
                this.name=info.name
                this.attributes["title"]=item.name
                this.value=item.value
                //判断是否选中
                if (info.value is Iterable<*>){
                    for(vItem in info.value as Iterable<*>){
                        if (vItem is JkNameValueData){
                            if (vItem.value==item.value){
                                this.checked=true
                                break;
                            }
                        }else{
                            if (vItem.toString()==item.value){
                                this.checked=true
                                break;
                            }
                        }
                    }

                }
                checkBookCall(this)

            }
        }
        block(this)
    }
}
fun FlowContent.jkDate(title:String="", formItem:Boolean=true, placeholder:String="", name:String="", value:Any?=null,
                       bind: KMutableProperty0<out Any?>?=null, type:InputType=InputType.text,
                       inline:Boolean=false, inputCall: (INPUT) -> Unit={}, block: FlowContent.() -> Unit={}
){
    val inputId = "id_${UUID.randomUUID()}"
   jkInput(title, formItem, placeholder, name, value, bind, type, inline,
           inputCall = {
        it.id= inputId
               inputCall(it)
   }){

       jkJavaScript("""
               initDateInput("#${inputId}")
       """);

       block(this)
   }
}
fun FlowContent.jkRadio(
        dataList:List<out JkNameValueData>,
        title: String="",
        inline:Boolean=false,
        formItem:Boolean=true,
        bind: KMutableProperty0<out Any?>?=null,
        name: String?=null,
        value: Any?=null,
        checkBookCall:(INPUT)->Unit={},
        block: FlowContent.() -> Unit={}){
    jkFormTitle (title, inline, formItem){
        for (item in dataList){

            input(type = InputType.radio) {
                val info = getBindInfo(bind, name, value)
                this.name=info.name
                this.attributes["title"]=item.name
                this.value=item.value
                //判断是否选中
                if (item.value==info.valueString){
                    this.checked=true;
                }
                checkBookCall(this)

            }
        }
        block(this)
    }
}
fun FlowContent.jkSelect(title: String="",
                         inline:Boolean=false,
                         formItem:Boolean=true,
                         bind: KMutableProperty0<out Any?>?=null,
                         name: String?=null,
                         value: Any?=null,
                         dataList:List<out JkNameValueData>?=null,
                         placeholder:String?=null,
                         selectCall:(SELECT)->Unit={},
                         block: FlowContent.() -> Unit={}){

    jkAutoComplete(title, inline, formItem, bind, name, value,search = false,dataList = dataList,placeholder = placeholder,selectCall = selectCall,block = block)
}
fun FlowContent.jkTextArea(){

}
fun FlowContent.jkUpload(title: String="",
                         inline:Boolean=false,
                         formItem:Boolean=true,
                         disable:Boolean=false,
                         bind: KMutableProperty0<out Any?>?=null,
                         name: String?=null,
                         value: Any?=null,
                         url: String="",
                         maxNum:Int=10,
                         exts:String="jpg|png|gif|bmp|jpeg",
                         fileType:String="images",
                         btnTitle:String="上传图片"){

    var uploadId=UUID.randomUUID().toString();
    val bindInfo = getBindInfo(bind, name, value)
    if(bindInfo.value==null){
        bindInfo.value= listOf<String>()
    }
    jkFormTitle (title, inline, formItem){
        it.id="upload_img_${uploadId}"
        div {
            this.classes+="layui-upload"
            if (!disable){
                button {
                    type=ButtonType.button
                    classes+="layui-btn"
                    id="upload_img_btn_${uploadId}"
                    text(btnTitle)
                }
            }
            input {
                type=InputType.hidden
                attributes["v-model"]="filejson"
                this.name=bindInfo.name
            }
            blockQuote {
                classes+="layui-elem-quote layui-quote-nm"
                attributes["style"]="margin-top: 10px;"
                if(fileType=="images"){
                    unsafe { """
                    预览：
                <div class="layui-upload-list" >
                    <span class="layui-upload-img " v-for="file in files">
                        <i class="layui-icon jk-remove" v-on:click="remove(index)">&#x1007;</i>

                        <i v-if="file.status =='loading'" class="layui-icon jk-upload layui-anim layui-anim-rotate layui-anim-loop">&#xe63d;</i>
                        <img v-if="file.status =='finish'"
                             style="width: 100px;height: 100px"
                             v-bind:src="file.url" v-bind:alt="file.filename"/>
                    </span>
                    <br style="clear:both;" />
                </div>
                """ }
                }else{
                    unsafe { """
                        <div class="layui-upload-list">
                    <table class="layui-table">
                        <thead>
                        <tr><th>文件名</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr></thead>
                        <tbody >
                            <tr v-for="file in files">
                                <td>{{file.filename}}</td>
                                <td>
                                    <i class="layui-icon" v-if="file.status=='finish'" style="color: #5FB878">&#xe616;</i>
                                    <i class="layui-icon" v-if="file.status=='error'" style="color: red" >&#x1007;</i>
                                    <i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop" v-if="file.status=='loading'">&#xe63e;</i>
                                </td>
                                <td>
                                    <a target="_blank" v-bind:href="file.url" class="layui-btn" >下载</a>
                                    <a class="layui-btn layui-btn-warm" v-on:click="remove(index)">删除</a>

                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                    """ }
                }

            }
        }

        jkJavaScript("""
            uploadComponent("${uploadId}","${url}","${maxNum}",${JSON.toJSONString(bindInfo.value)},"${fileType}","${exts}")
        """.trimIndent())
    }
}
fun FlowContent.jkButton(title:String="按钮",
                         aLink:Boolean=false,
                         type:String=JkButtonType.submit.name
                         ,event:String="",url:String=""
                         ,option:Map<String,Any>?=null,block: BUTTON.() -> Unit={}){

    if (aLink){
        a{

        }
    }
    button {

        var map= HashMap<String,String>();
        when(type){
            "submit"->{
                map["lay-submit"]="";
                map["lay-filter"]="submit"
            }
            "ajax_submit"->{
                map["lay-submit"]="";
                map["lay-filter"]="ajax_submit"
            }
            "reset"->{
                this.type=ButtonType.reset
            }
            "diy"->{
                map["lay-submit"]="";
                map["lay-filter"]=event
                map["data-event"]=event
            }
            else->{
                map["lay-submit"]="";
                map["lay-filter"]=type
                var op=option;
                if(option==null){
                    op= mapOf();
                }
                map["data-event"]="${type}@${url}@${JSON.toJSONString(op)}"
            }
        }
        attributes.putAll(map);
        classes+="layui-btn"
        text(title)
    }
}
enum class JkButtonType{
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
    ajax_submit,
    reset,
    /**
     * 询问框
     */
    confirm,
    /**
     * 自定义数据
     */
    diy,
    /**
     * 换行
     */
    brBtn
}
//-----------------表单部分结束---------------------------
fun <T>FlowContent.jkTable(headNames:LinkedHashMap<String, JkTableCol<T>>
                           ,dataList:List<T>){


    table {
        classes+="layui-table jk-table"
        thead {
            tr{
                for (head in headNames){
                    th { text(head.key) }
                }
            }
        }
        tbody {


            for (item in dataList){
                tr {
                    for(head in headNames){
                        td {

                            if(head.value.value!=null){
                                when(head.value.value){
                                    is KProperty1<*,*>->{
                                        var pget=head.value.value as KProperty1<T,*>;
                                        text(pget.get(item).toString())
                                    }
                                    is KCallable<*>->{
                                        var callM=head.value.value as KCallable<T>;
                                        text(callM.call(item).toString())
                                    }

                                    else->{
                                        text(head.value.value.toString())
                                    }
                                }
                            }
                            var map=head.value.attrsCall(item);
                            if(map!=null){
                                attributes.putAll(map);
                            }

                            head.value.call(item)

                        }


                    }
                }
            }

        }
    }
}
data class JkTableCol<T>(var value:Any?=null,
                         var attrsCall:(T)->Map<String,String>?={null},
                      var call:(T)->Unit={})

fun FlowContent.jkPage(pageNo:Int,count:Int,limit:Int=30,block:(Map<String,Any>) -> Unit={}){
    var uuid=UUID.randomUUID().toString();
    div {
        id=uuid
        var config=mapOf(
                "count" to count,
                "curr" to pageNo,
                "limit" to limit
        );
        block(config)
        attributes["lay-data"]=JSON.toJSONString(
                config
        );


    }
    jkJavaScript("""
            initPage("${uuid}")
        """.trimIndent())

}
fun getBindInfo(bind: KMutableProperty0<out Any?>?=null,name:String?=null,value:Any?=null): JkBindInfo {
    val info = JkBindInfo();
    if (bind==null){
        info.name=name.toString();
        info.valueString=value.toString();
        info.value=value;
    }else{
        info.name=bind.name;
        info.valueString= bind.get().toString();
        info.value= bind.get();
    }

    if (info.name=="null"){
        info.name="";
    }
    if (info.valueString=="null"){
        info.valueString="";
    }


    return info;
}
fun FlowContent.jkJavaScript(script:String,block: FlowContent.() -> Unit={}){

    script {
        unsafe {
            raw(script)
        }
    }
}
data class JkBindInfo(var name: String="",var value: Any?=null,var valueString: String="");
