<#-- @ftlvariable name="val" type="com.bidanet.springmvc.demo.JkNameValueDataImpl" -->

    <label class="layui-form-label">${formField.title!}</label>
    <div class="layui-input-${layout!"block"}">
        <#if arrayData??>
            <#list arrayData as  val>
                <input type="checkbox" name="${formField.name}"
                ${disable!}
                ${formField.getAttrsTpl()}
                       <#if formField.val??>
                           <#list formField.val as v>
                                <#if "${v!}" == "${val.getValue()!}">checked</#if>
                           </#list>
                       </#if>

                       value="${val.getValue()!}" title="${val.getName()!}">
            </#list>
        </#if>

    </div>
