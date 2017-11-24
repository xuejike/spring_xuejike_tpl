
    <label class="layui-form-label">${formField.title!}</label>
    <div class="layui-input-${layout!"block"}">
        <input type="text" name="${formField.name!}"
               id="id_${formField.name!}"
               autocomplete="off"
               ${formField.getAttrsTpl()}
               placeholder="${formField.placeholder!}"
               class="layui-input ${formField.getCssClassTpl()}">
    </div>
    <script>
        layui.use('laydate', function(){
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#id_${formField.name!}' //指定元素
            });
        });
    </script>