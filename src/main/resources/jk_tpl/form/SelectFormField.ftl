<#-- @ftlvariable name="val" type="com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueDataImpl" -->

    <label class="layui-form-label">${formField.title!}</label>
    <div class="layui-input-${layout!"block"}">
        <select name="${formField.name!}" lay-verify="" class="${formField.getCssClassTpl()}" ${formField.getAttrsTpl()} >
            <#if formField.placeholder??>
                <option value="">${formField.placeholder!}</option>
            </#if>

            <#if arrayData??>
                <#list arrayData as val>
                    <option value="${val.getValue()!}" <#if "${formField.val!}" == "${val.getValue()!}"> selected</#if>>${val.getName()!}</option>
                </#list>
            </#if>

        </select>

    </div>
