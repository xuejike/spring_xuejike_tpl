<#include "/libs.ftl"/>
<label class="layui-form-label">${formField.title!}</label>
<div class="layui-input-${layout!"block"}">
    <div id="${divId!}" data-tpl="${tpl!}">

    </div>
    <script>
        layui.use(["laytpl","jquery"],function () {
            var laytpl=layui.laytpl,$=layui.$;

            var divDom=$("#${divId!}");
            var tpl=divDom.data("tpl");
            var tplText=$(tpl).text();



            //直接解析字符
            laytpl(tplText).render({
                name: '${formField.name!}',
                value:"${formField.val!}"
            }, function(string){
                divDom.replaceWith(string);
            });
        })
    </script>
</div>
