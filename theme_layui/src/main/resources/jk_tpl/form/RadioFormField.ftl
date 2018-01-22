<#-- @ftlvariable name="val" type="com.bidanet.springmvc.demo.JkNameValueDataImpl" -->

    <label class="layui-form-label">${formField.title!}</label>
    <div class="layui-input-${layout!"block"}">
        <#if arrayData??>
            <#list arrayData as  val>
                <input type="radio" name="${formField.name}"
                ${disable!}
                ${formField.getAttrsTpl()}
                    <#if formField.val??>
                        <#if "${formField.getValString()}" == "${val.getValue()!}"> checked</#if>
                    </#if>

                       value="${val.getValue()!}" title="${val.getName()!}">
            </#list>
        </#if>

    </div>