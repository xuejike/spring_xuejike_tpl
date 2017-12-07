<#-- @ftlvariable name="btn" type="com.bidanet.springmvc.demo.jkbuilder.annotation.JkButton" -->
<#-- @ftlvariable name="searchItem" type="com.bidanet.springmvc.demo.jkbuilder.type.FormFieldInfo" -->
<!-- 工具集 -->
<div class="my-btn-box" style="padding-top: 20px;height: auto">
    <form class="layui-form" action="">
        <#if searchList??>
            <#list searchList as searchItem>
                <#if searchItem_index%3 == 0>
                <div class="layui-form-item">
                </#if>
            ${searchItem.type.html(searchItem,"inline")}
                <#if searchItem_index%3 == 2|| !searchItem_has_next>
                </div>
                </#if>

            </#list>

        </#if>




        <div class="layui-form-item" style="padding-left: 40px">
            <#if searchBtn??>
                <#list searchBtn as btn>

                    <#switch btn.type()>
                        <#case "submit">
                            <button class="layui-btn ${btn.cssClass()}" lay-submit="" lay-filter="search_table">${btn.value()!}</button>
                            <#break>
                        <#case "reset">
                            <button type="reset" class="layui-btn ${btn.cssClass()}">${btn.value()!}</button>
                            <#break >
                        <#case "brBtn">
                            <br/>
                            <#break >
                        <#default>
                            <a class="layui-btn tool-btn  ${btn.cssClass()}"
                               lay-filter="toolClick"
                               data-event="<#if btn.type()=="diy">${btn.event()}<#else>${btn.type()}@${btn.url()}@${btn.option()}</#if>">
                            ${btn.value()}</a>
                    </#switch>
                </#list>
            </#if>



        </div>
    </form>


    <#--<span class="fl">-->
        <#--<a class="layui-btn layui-btn-danger radius btn-delect" id="btn-delete-all">批量删除</a>-->
        <#--<a class="layui-btn btn-add btn-default" id="btn-add">添加</a>-->
        <#--<a class="layui-btn btn-add btn-default" id="btn-refresh"><i class="layui-icon">&#x1002;</i></a>-->
    <#--</span>-->
    <#--<span class="fr">-->
        <#--<span class="layui-form-label">搜索条件：</span>-->
        <#--<div class="layui-input-inline">-->
            <#--<input type="text" autocomplete="off" placeholder="请输入搜索条件" class="layui-input">-->
        <#--</div>-->
        <#--<button class="layui-btn mgl-20">查询</button>-->
    <#--</span>-->
</div>

<!-- 表格 -->
<div id="dateTable" lay-filter="dataTableEvent"></div>

<script type="text/javascript">


    // layui方法
    layui.use(['table', 'form', 'layer', 'vip_table'], function () {

        // 操作对象
        var form = layui.form
                , table = layui.table
                , layer = layui.layer
                , vipTable = layui.vip_table
                , $ = layui.jquery;

        // 表格渲染
        var tableIns = table.render({
            elem: '#dateTable'                  //指定原始表格元素选择器（推荐id选择器）
            , height: vipTable.getFullHeight()    //容器高度
            , cols: ${head!"[[]]"}
            , id: 'dataTable'
            <#if data??>
                , data: '${data![]}'
                <#else>
                    , url: '${url!}'
            </#if>

            , method: 'post'
            , page: true
            , limits: [30, 60, 90, 150, 300]
            , limit: 30 //默认采用30
            , loading: true
            , response:{
                statusName: 'statusCode' //数据状态的字段名称，默认：code
                ,statusCode: 200 //成功的状态码，默认：0
                ,msgName: 'message' //状态信息的字段名称，默认：msg
                ,countName: 'total' //数据总数的字段名称，默认：count
                ,dataName: 'rows' //数据列表的字段名称，默认：data
            }
            , done: function (res, curr, count) {
                <#if finishFun??>
                    ${finishFun}(res, curr, count);
                </#if>
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
//                console.log(res);

                //得到当前页码
//                console.log(curr);

                //得到数据总量
//                console.log(count);
            }
        });

        // 获取选中行
//        table.on('checkbox(dataTableEvent)', function (obj) {
////            layer.msg('123');
//            console.log(obj.checked); //当前是否选中状态
//            console.log(obj.data); //选中行的相关数据
//            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
//        });


        //监听工具条
        table.on('tool(dataTableEvent)', function(obj){
            var data = obj.data;
            var event= obj.event;
            tableToolAction(event,data,obj);

        });

        $('.my-btn-box .tool-btn').on('click', function(){
            var type = $(this).data('event');
            var data=table.checkStatus('dataTable');
       
            tableToolAction(type,data.data,data)
        });


        //搜索
        //监听提交
        form.on('submit(search_table)', function(data){
            //清除无用数据
            for(key in data.field){
                if(data.field[key]&&data.field[key]!=undefined&&data.field[key]!=null&&data.field[key]!=""){

                }else {
                    delete data.field[key];
                }
            }
            tableIns.reload({
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: data.field
            });
            return false;
        });
        // you code ...


        window.reloadContent=function () {
            tableIns.reload();
        }

    });



</script>
<!-- 表格操作按钮集 -->


<#list toolbar?keys as key>
<script type="text/html" id="${key!}">
    <#list toolbar[key] as btn>
        <#if btn.ifExp() != "">
            {{#  if(${btn.ifExp()}){ }}
        </#if>
    <a class="layui-btn layui-btn-sm ${btn.cssClass()}" lay-event="<#if btn.type()=="diy">${btn.event()}<#else>${btn.type()}@${btn.url()}@${btn.option()}</#if>">${btn.value()}</a>
        <#if btn.ifExp() != "">
        {{#  }  }}
        </#if>

    </#list>

</script>
</#list>