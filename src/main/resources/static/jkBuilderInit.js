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