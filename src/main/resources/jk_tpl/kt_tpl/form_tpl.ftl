<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>${title!}</title>
    <#include "head_css_tpl.ftl"/>

    ${head!""}
</head>
<body class="body">
<#include "body_js_tpl.ftl"/>
${content!}

<script type="text/javascript" src="${vipRoot}/jkBuilderInit.js"></script>
${footer!""}
</body>
</html>