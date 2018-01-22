<#-- @ftlvariable name="regexp" type="com.bidanet.springmvc.demo.JkVerifyRegExp" -->
<#-- @ftlvariable name="verifyInfo" type="com.bidanet.springmvc.demo.VerifyInfo" -->

<#macro verify verifyInfo>

    lay-verify="${verifyInfo.verify!}"
        <#if verifyInfo.regExps??>
            <#list verifyInfo.regExps as regexp>
                data-verify-${regexp_index}="${regexp.regExp()}"
                data-verify-${regexp_index}-msg="${regexp.message()}"
            </#list>
        </#if>
    data-verify-ajax-url="/jkBuilder/verify"
    data-verify-ajax-cls="${verifyInfo.ajaxCls!}"

</#macro>