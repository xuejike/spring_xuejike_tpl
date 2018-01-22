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

</head>
<body class="body">
<#include "body_js_tpl.ftl"/>
${content!}
<script type="text/javascript" src="/jkBuilderInit.js"></script>
<script type="text/javascript" src="/jkEvent.js"></script>
<#if footerTpl??>
    <#list footerTpl as tpl>
        <#include tpl/>
    </#list>
</#if>

</body>
</html>