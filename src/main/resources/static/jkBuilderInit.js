layui.use(["jquery","layer"],function () {
    var $=layui.$;
    var layer=layui.layer;
    layui.$(document).on("click",".jk-btn[lay-filter=doAjax]",function (e) {
        // console.log(e)
        ajaxPost($(e.currentTarget).attr("href"),{});
        e.preventDefault();
    });


    layui.$(".jk-table button").on("click",function (e) {
        // alert("ss")
        var evn=$(e.currentTarget).attr("data-event");
        if(evn){
            btnEvent(evn)
        }

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

layui.use(["jquery","form"],function () {
    var $=layui.$;
    setInputKeUp();
    function setInputKeUp() {
        layui.$(".jk-search-select  .layui-input").on("keyup",function (event) {
            var elBody=layui.$(event.currentTarget).parents(".jk-search-select");
            if((event.keyCode>=48&&event.keyCode<=105)||(event.keyCode==8)){
                var v=$(event.currentTarget).val();
                var opt="";


                var url= elBody.find("select").data("url");
                var beanCls= elBody.find("select").data("bean-cls");
                $.post(url,{key:v,beanCls:beanCls},function (data) {
                    for(var i=0;i<data.length;i++){
                        opt+="<option value='"+data[i].value+"'>"+data[i].name+"</option>"
                    }
                    elBody.find("select").html(opt);
                    layui.form.render();
                    elBody.find(".layui-input").click().focus().val(v);
                    setInputKeUp();
                });

                return;
            }
            var selectOpt= elBody.find(".layui-this");

            //Enter
            if(event.keyCode==13){
                var val=selectOpt.attr("lay-value");
                var txt=selectOpt.text();
                elBody.find("input").val(txt);
//                    console.log("-->"+val+"->"+txt);
                elBody.find("select").val(val);
                //关闭
                elBody.find(".layui-form-select").removeClass("layui-form-selected layui-form-selectup")
                return;
            }
            var nextSelectOpt;
            var selectDl=elBody.find("dl");
            if(selectOpt.length==0){
                selectOpt= $(elBody.find("dd")[0]);
                nextSelectOpt=selectOpt;
                selectDl.scrollTop(0);
            }else{
                //up
                if(event.keyCode==38){
                    nextSelectOpt=selectOpt.prev();
                    if(nextSelectOpt.length==0){
                        nextSelectOpt=selectOpt;
                    }

                }
                //down
                if(event.keyCode==40){
                    nextSelectOpt=selectOpt.next();
                    if(nextSelectOpt.length==0){
                        nextSelectOpt=selectOpt;
                    }

                }
            }
            selectOpt.removeClass("layui-this");
            nextSelectOpt.addClass("layui-this");


            if(nextSelectOpt.position().top<0){
                selectDl.scrollTop(selectDl.scrollTop()
                    -selectOpt.height());
            }
            if(nextSelectOpt.position().top>(selectDl.height()-selectOpt.height())){
                selectDl.scrollTop(selectDl.scrollTop()
                    +selectOpt.height());
            }

//            $('input').click().focus().val(val)
        })
        layui.$("select")
    }
});

layui.use(['form'], function(){
    var form = layui.form;

    //监听提交
    form.on('submit(ajax_submit)', function(data){
        setTimeout(function(){
            ajaxPost(data.form.action,layui.$(data.form).serialize());
        },1);


        return false;
    });

    form.on('submit(reset_submit)',function (data) {
        setTimeout(function () {
            data.form.reset();
            data.form.submit();
        })
    })
});

