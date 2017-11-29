<!DOCTYPE html>
<html lang="en">
<#assign vipRoot="/vip/"/>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>${title!}</title>
    <#include "head_css_tpl.ftl"/>

    <style>
        .layui-upload-img{
            width: 90px;
            height: 90px;
            display: block;
            float: left;
            position: relative;
            border: 1px solid #eeeeee;
            margin: 10px;
        }
        .layui-upload-img .jk-remove   {
            color: red;
            font-size: 20px;
            position: absolute;
            right: -10px;
            top: -10px;
            cursor: pointer;
        }
        .layui-upload-img .jk-upload{
            position: absolute;
            font-size: 30px;
            left: 30px;
            top:35px;
        }
        .layui-upload-img img{
            width: 100%;
            height: 100%;
        }
        .layui-upload-img .jk-file-bg{
            font-size: 20px;
            /*position: absolute;*/
            /*top: 30px;*/
            /*left: 5px;*/
        }
        .layui-upload-img .jk-title{
            position: absolute;
            left: 30px;
            top:35px;
        }
    </style>
</head>
<body class="body">
<#include "body_js_tpl.ftl"/>
${content!}

<#if footerTpl??>
    <#include  footerTpl />
</#if>
<script type="text/javascript" src="/jkBuilderInit.js"></script>
<script type="text/javascript" src="/jkEvent.js"></script>
</body>
</html>