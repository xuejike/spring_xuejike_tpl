<#-- @ftlvariable name="val" type="com.bidanet.springmvc.demo.jkbuilder.data.JkNameValueDataImpl" -->
<#-- @ftlvariable name="auto" type="com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkAutoCompleteFormField" -->
<#-- @ftlvariable name="dataSource" type="com.bidanet.springmvc.demo.jkbuilder.annotation.JkDataSource" -->
    <label class="layui-form-label">${formField.title!}</label>
    <div class="layui-input-${layout!"block"} ${formField.getCssClassTpl()} <#if auto.type()=="remote"> jk-search-select</#if>">
        <select name="${formField.name!}"
                lay-search=""
                lay-verify="${verify!}"
                ${formField.getAttrsTpl()}
                <#if auto.type()=="remote">
                    data-url="/jkBuilder/selectDataSource"
                    data-bean-cls="${dataSource.beanCls().getName()}"
                </#if>
                 >
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
