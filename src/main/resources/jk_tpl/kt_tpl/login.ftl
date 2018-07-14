<#-- @ftlvariable name="info" type="vip.xuejike.ktpl.AdminLoginKtView.LoginInfo" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>登录</title>
      <#include "./head_css_tpl.ftl"/>
</head>
<body>

<div class="login-main">
    <#if content == "">
        <header class="layui-elip">${info.title!""}</header>
        <form class="layui-form" action="${info.actionUrl!""}" method="post">
            <div class="layui-input-inline">
                <input type="text" name="username" required
                       lay-verify="required"
                       value="${info.userName!""}"
                       placeholder="用户名" autocomplete="off"
                       class="layui-input">
            </div>
            <div class="layui-input-inline">
                <input type="password" name="pwd" required
                       lay-verify="required" placeholder="密码"
                       autocomplete="off"
                       value="${info.pwd!""}"
                       class="layui-input">
            </div>
            <div class="layui-inline">
                <span style="color: red">${info.msg!""}</span>
            </div>
            <div class="layui-input-inline login-btn">
                <button type="submit" class="layui-btn">登录</button>
            </div>
            <hr/>


        </form>
        <#else>
            ${content}
    </#if>

</div>
    <#include "./body_js_tpl.ftl"/>

<script type="text/javascript">

</script>
</body>
</html>