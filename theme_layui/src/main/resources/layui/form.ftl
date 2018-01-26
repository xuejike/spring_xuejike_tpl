

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>form 标题</legend>
</fieldset>


<form class="layui-form"  method="post" enctype="multipart/form-data">
    <#list sub as item>
        <div class="layui-form-item">
            ${item!""}
        </div>

    </#list>
</form>