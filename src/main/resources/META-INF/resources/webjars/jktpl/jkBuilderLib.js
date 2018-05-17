//事件定义
var jkBuilderConfig={
  status:{
      success:200,
      error:300,
      timeout:301

  },
    dialogSize:["900px","600px"],
    event:{

    }
};


//通过URL打开对话框
function openUrlDialog(href,option) {
    option.type=2;
    option.content=href;
    var df={area:jkBuilderConfig.dialogSize}
    getLayer().open(layui.$.extend(df,option))
}
function openTab(href, title) {
    layui.use(["vip_tab"],function () {
        layui.vip_tab.add(this,title,href);
    })
}

//发送Post请求，并自动处理
function ajaxPost(url,data) {
    
    // console.log(parent.layer);

    var index = getLayer().load(1);
    layui.$.ajax(url,{
        type:"post",
        cache:false,
        async:true,
        data:data,
        timeout:10*1000,
        dataType:"json",
        complete:function () {
            getLayer().close(index);
        },
        success:function (data, textStatus, jqXHR) {

            ajaxDataHandle(data);
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown);
            getLayer().msg(errorThrown.message)
        },
        statusCode:{404:function () {
            //页面不存在
            getLayer().close(index);
            getLayer().msg("页面不存在")
        }},


    })
}
//Ajax状态处理器
function ajaxDataHandle(res) {

    if(res.statusCode==jkBuilderConfig.status.success){
        getLayer().msg(res.message)
    }else if(res.statusCode == jkBuilderConfig.status.error){
        getLayer().msg(res.message)
    }else if(res.statusCode == jkBuilderConfig.status.timeout){
        getLayer().msg("请求超时")
    }else{

    }
    ajaxActionHandle(res);
}
function closeNow() {
    var index = parent.layer.getFrameIndex(window.name);
    if(index){
        //关闭对话框，且刷新当前tab
        parent.layer.close(index); //再执行关闭

    }else{
        layui.use(["vip_tab"],function () {
            vipTab = layui.vip_tab;
            if(vipTab.getThisTabId()){
                //在Tab中
                vipTab.del(vipTab.getThisTabId());
            }

        });
    }
}
function ajaxActionHandle(res) {

    if(res.closeCurrent == true){
        closeNow();

    }

    var ifs=parent.getNowTabIframe();
    if(ifs.length>0){
        if(ifs[0].contentWindow.reloadContent){
            ifs[0].contentWindow.reloadContent();
        }else{
            ifs[0].contentWindow.location.reload(true)
        }

    }

    //
}
function getLayer() {
    if(parent){
        return parent.layer;
    }else{
        return layui.layer;
    }
}
// event  action@url@option
function btnEvent(event) {

    var tagIndex= event.indexOf("@");
    if(tagIndex>=0){
        var eventArgs= event.split("@");
        var action= eventArgs[0];
        var option="";
        var url=eventArgs[1];


        if(eventArgs.length>=3){
            eval("option="+eventArgs[2])
        }
        switch (action){
            case "tab":
                openTab(url,option["title"]);
                return;
            case "dialog":
                openUrlDialog(url,option);
                return;
            case "doAjax":
                ajaxPost(url,{});
                return;
            case "windows":
                window.open(url);
                return;
            case "confirm":
                getLayer().confirm(option, {icon: 3, title:'提示'}, function(index){
                    //do something
                    ajaxPost(url,{});
                    layer.close(index);
                });
                return;
            default:

        }
    }

    if(jkBuilderConfig.event[event]){
        jkBuilderConfig.event[event](event);
    }else{
        console.log("没有处理方法:"+event);
    }
}
function tableToolAction(event, data,row) {

    var tagIndex= event.indexOf("@");
    if(tagIndex>=0){
        var eventArgs= event.split("@");
        var action= eventArgs[0];
        var option="";
        var url="";
        if(eventArgs.length>1){
            url=buildUrl(eventArgs[1],data);
            if(url==null){
                return;
            }
        }

        if(eventArgs.length>=3){
            eval("option="+eventArgs[2])
        }
        switch (action){
            case "tab":
                openTab(url,option["title"]);
                return;
            case "dialog":
                openUrlDialog(url,option);
                return;
            case "doAjax":
                ajaxPost(url,{});
                return;
            case "windows":
                window.open(url);
                return;
            case "confirm":
                getLayer().confirm(option, {icon: 3, title:'提示'}, function(index){
                    //do something
                    ajaxPost(url,{});
                    layer.close(index);
                });
                return;
            default:

        }
    }

    if(jkBuilderConfig.event[event]){
        jkBuilderConfig.event[event](event,data,row);
    }else{
        console.log("没有处理方法:"+event);
    }

}
//构建URL  占位符 {属性名}
function buildUrl(url, data) {
    var params=getUrlParams(url);
    if(params.length>0&&data.length==0){
        layer.msg("请选择元素")
        return null;
    }
    if(Array.isArray(data)){
        if(data.length==0){
            return url;
        }

        for(var i=0;i<params.length;i++){
            var key= params[i];
            var val=data[0][key];
            for(var j=1;j<data.length;j++){
                val+=","+data[j][params[i]];
            }
            url=url.replace("{"+params[i]+"}",val);
        }
    }else{
        for(var i=0;i<params.length;i++){
            url=url.replace("{"+params[i]+"}",data[params[i]]);
        }
    }

    return url;
}

function getUrlParams(url) {
    var rx=/\{\w+\}/g;
    var params=[];
    var rs;
    while(rs=rx.exec(url)){
        console.log(rs);
        params.push(rs[0].substring(1,rs[0].length-1));
    };

    return params;
}
function uploadComponent(subId,url,max,data,type,exts) {
    var uploadFiles={

    };

    var uploadVue= new Vue({
        el:"#upload_img_"+subId,
        data:{
            files:data
        },
        methods:{
            remove:function (index) {
                this.files.splice(index, 1);
            },
            addFile:function (file) {
                if(this.files.length >= max){
                    layer.msg("文件已经达到上限")
                }else{
                    this.files.push(file);
                }
            }
        },
        computed:{
            filejson:function () {
                var filedata=[];
                for(var i=0;i<this.files.length;i++){
                    var f= this.files[i];
                    if(f.status=="finish"){
                        filedata.push(f);
                    }
                }
                return JSON.stringify(filedata);
            }
        }
    });
    layui.use(["upload","layer"],function () {
        var upload=layui.upload;
        var layer=layui.layer;
        var obj={
            elem: '#upload_img_btn_'+subId
            ,url: url
            ,accept:type
            ,multiple: true
            ,before: function(obj){
                //预读本地文件示例，不支持ie8

                obj.preview(function(index, file, result){
                    uploadFiles[index]={
                        status:"loading", url:result,filename:file.name
                    };
                    uploadVue.addFile(uploadFiles[index])
                });
            }
            ,done: function(res,index,upload){
                //上传完毕
                if(res.statusCode==200){
                    uploadFiles[index].status="finish";
                    uploadFiles[index].url=res.data.url;
                }
            },
            error:function (res, index, upload) {
                layer.msg("网络异常");
                if(uploadFiles[index]){
                    uploadFiles[index].status="error";
                }

            }
        };
        if(exts!=""){
            obj['exts']=exts;
        }
        //多图片上传
        upload.render(obj);
    });
};

function initDateInput(id) {
    layui.use(['laydate',"jquery"], function(){
        var laydate = layui.laydate;


        var config=getLayuiDomConfig(id);
        config["elem"]=id;
        //执行一个laydate实例
        laydate.render(config);
    });
}
function initPage(id) {
    layui.use(['laypage',"jquery"], function(){
        var laypage = layui.laypage;
        var config=getLayuiDomConfig("#"+id);
        config["elem"]=id;
        //执行一个laypage实例
        if(!config["jump"]){
            config["jump"]=function (obj, first) {
                if(!first){
                    var params=getQueryString();
                    params["pageNo"]=obj.curr;
                    params["pageSize"]=obj.limit;
                    var url=window.location.pathname+"?";
                    for(var k in params){
                        url+=k+"="+params[k]+"&"
                    }
                    window.location.href=url;

                }
            }
        }
        laypage.render(config);
    });
}
function getLayuiDomConfig(id) {
    var date=layui.$(id);
    var ext=date.attr("lay-data");
    var config={};
    if(ext!=undefined&&ext!=""){
        config=layui.$.parseJSON(ext);
    }
    return config;
}
function getQueryString() {
    var qs = location.search.substr(1), // 获取url中"?"符后的字串
        args = {}, // 保存参数数据的对象
        items = qs.length ? qs.split("&") : [], // 取得每一个参数项,
        item = null,
        len = items.length;

    for(var i = 0; i < len; i++) {
        item = items[i].split("=");
        var name = decodeURIComponent(item[0]),
            value = decodeURIComponent(item[1]);
        if(name) {
            args[name] = value;
        }
    }
    return args;
}
