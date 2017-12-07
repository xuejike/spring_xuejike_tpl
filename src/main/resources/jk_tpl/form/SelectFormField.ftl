<#-- @ftlvariable name="val" type="com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueDataImpl" -->
<#include "/libs.ftl"/>
    <label class="layui-form-label">${formField.title!}</label>
    <div class="layui-input-${layout!"block"}">
        <select name="${formField.name!}"   ${disable!} <@verify verifyInfo=verifyInfo/> class="${formField.getCssClassTpl()}" ${formField.getAttrsTpl()} >
            <#if formField.placeholder??>
                <option value="">${formField.placeholder!}</option>
            </#if>

            <#if arrayData??>
                <#list arrayData as val>
                    <option value="${val.getValue()!}"
                        <#if formField.val??>

                            <#if "${formField.getValString()}" == "${val.getValue()!}"> selected</#if>
                        </#if>
                    >${val.getName()!}</option>
                </#list>
            </#if>

        </select>

    </div>
