//事件定义
var jkBuilderConfig={
  status:{
      success:200,
      error:300,
      timeout:301

  },
    dialogSize:["900px","600px"]
};

layui.use(["jquery","layer"],function () {
    var $=layui.$;
    var layer=layui.layer;
    layui.$(document).on("click",".jk-btn[lay-filter=doAjax]",function (e) {
        // console.log(e)
        ajaxPost($(e.currentTarget).attr("href"),{});
        e.preventDefault();
    });

    layui.$(document).on("click",".jk-btn[lay-filter=dialog]",function (e) {
        var dom=$(e.currentTarget);
        var width= dom.data("width");
        var height= dom.data("height");
        var title= dom.data("title");
        var area=["800px","500px"];
        if(height){
            area[1]=height+"px";
        }
        if(width){
            area[0]=width+"px";
        }
        var href= dom.attr("href");
        openUrlDialog(href,{
            area:[width,height],
            title:title
        });
        e.preventDefault();
    });
});
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
function ajaxActionHandle(res) {

    if(res.closeCurrent == true){
        var index = parent.layer.getFrameIndex(window.name);
        if(index){
            //关闭对话框，且刷新当前tab
            parent.layer.close(index); //再执行关闭

            var ifs=parent.getNowTabIframe();
            if(ifs.length>0){
                if(ifs[0].contentWindow.reloadContent){
                    ifs[0].contentWindow.reloadContent();
                }
            }
        }else{
            layui.use(["vip_tab"],function () {
                vipTab = layui.vip_tab
                if(vipTab.getThisTabId()){
                    //在Tab中
                    vipTab.del(vipTab.getThisTabId());
                }

            });
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
function tableToolAction(event, data,row) {
    if(data.length==0){
        layer.msg("请选择元素")
        return;
    }
    var tagIndex= event.indexOf("@");
    if(tagIndex>=0){
        var eventArgs= event.split("@");
        var action= eventArgs[0];
        var option="";
        var url="";
        if(eventArgs.length>1){
            url=buildUrl(eventArgs[1],data);
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
            default:



        }
    }
    console.log("没有处理方法:"+event);

}
//构建URL  占位符 {属性名}
function buildUrl(url, data) {
    var params=getUrlParams(url);
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