<#-- @ftlvariable name="info" type="vip.xuejike.ktpl.AdminJkKtView.AdminInfo" -->
<!DOCTYPE html>
<html lang="en">
<head>

    <title>${info.title!"必答后台模板"}</title>
      <#include "./head_css_tpl.ftl"/>
</head>
<body>

<!-- layout admin -->
<div class="layui-layout layui-layout-admin"> <!-- 添加skin-1类可手动修改主题为纯白，添加skin-2类可手动修改主题为蓝白 -->
    <!-- header -->
    <div class="layui-header my-header">
        <a href="${info.indexUrl!"index.html"}">
            <!--<img class="my-header-logo" src="" alt="logo">-->
            <div class="my-header-logo">${info.title!"必答后台模板"}</div>
        </a>
        <div class="my-header-btn">
            <button class="layui-btn layui-btn-small btn-nav">
                <i class="layui-icon">&#xe65f;</i></button>
        </div>

        <!-- 顶部左侧添加选项卡监听 -->
        <ul class="layui-nav" lay-filter="side-top-left" id="mainTopMenu">

        </ul>

        <!-- 顶部右侧添加选项卡监听 -->
        <ul class="layui-nav my-header-user-nav" lay-filter="side-top-right">
            <#--<li class="layui-nav-item"><a href="javascript:;" class="pay" href-url="">支持作者</a></li>-->
            <li class="layui-nav-item">
                <a class="name" href="javascript:;"><i class="layui-icon">&#xe629;</i>主题</a>
                <dl class="layui-nav-child">
                    <dd data-skin="0"><a href="javascript:;">默认</a></dd>
                    <dd data-skin="1"><a href="javascript:;">纯白</a></dd>
                    <dd data-skin="2"><a href="javascript:;">蓝白</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a class="name" href="javascript:;"><img src="/vip/frame/static/image/code.png" alt="logo"> ${nickName!} </a>
                <dl class="layui-nav-child">

                    <#--<dd><a href="javascript:;" href-url="demo/login.html"><i class="layui-icon">&#xe621;</i>登录页</a></dd>-->
                    <#--<dd><a href="javascript:;" href-url="demo/map.html"><i class="layui-icon">&#xe621;</i>图表</a></dd>-->
                    <dd><a href="${info.logoutUrl!"./public/logout"}">
                        <i class="layui-icon">&#x1006;</i>退出</a></dd>
                </dl>
            </li>
        </ul>

    </div>
    <!-- side -->
    <div class="layui-side my-side">
        <div class="layui-side-scroll">
            <!-- 左侧主菜单添加选项卡监听 -->
            <ul class="layui-nav layui-nav-tree" lay-filter="side-main">

            </ul>

        </div>
    </div>
    <!-- body -->
    <div class="layui-body my-body">
        <div class="layui-tab layui-tab-card my-tab" lay-filter="card"
             lay-allowClose="true">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="1"><span>
                    <i class="layui-icon">&#xe638;</i>欢迎页</span></li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <iframe id="iframe" src="${info.welcomeUrl!"home"}" frameborder="0"></iframe>
                </div>
            </div>
        </div>
    </div>
    <!-- footer -->
    <div class="layui-footer my-footer">

        <p><a href="http://www.bidanet.com" target="_blank">vip-admin后台模板v1.8.0</a>&nbsp;&nbsp;&&nbsp;&nbsp;<a href="http://vip-admin.com/index/gather/index.html" target="_blank">苏州必答网络科技有限公司</a></p>
        <p>2017 © copyright </p>
    </div>
</div>



<!-- 右键菜单 -->
<div class="my-dblclick-box none">
    <table class="layui-tab dblclick-tab">
        <tr class="card-refresh">
            <td><i class="layui-icon">&#x1002;</i>刷新当前标签</td>
        </tr>
        <tr class="card-close">
            <td><i class="layui-icon">&#x1006;</i>关闭当前标签</td>
        </tr>
        <tr class="card-close-all">
            <td><i class="layui-icon">&#x1006;</i>关闭所有标签</td>
        </tr>
    </table>
</div>
 <#include "./body_js_tpl.ftl"/>

<script type="text/javascript" src="/vip/frame/static/js/vip_comm.js"></script>
<script type="text/javascript">
layui.use(['layer','vip_nav'], function () {

    // 操作对象
    var layer       = layui.layer
        ,vipNav     = layui.vip_nav
        ,$          = layui.jquery;
    // 顶部左侧菜单生成 [请求地址,过滤ID,是否展开,携带参数]
    vipNav.top_left('${info.top!"./menu/top-menu"}','side-top-left',false);
    // 主体菜单生成 [请求地址,过滤ID,是否展开,携带参数]
    vipNav.main('${info.left!"./menu/left-menu?pid=1"}','side-main',true);


    // you code ...




});
</script>
</body>
</html>