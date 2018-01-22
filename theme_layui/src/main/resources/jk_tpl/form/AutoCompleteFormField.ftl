<#-- @ftlvariable name="val" type="com.bidanet.springmvc.demo.JkNameValueDataImpl" -->
<#-- @ftlvariable name="auto" type="com.bidanet.springmvc.demo.JkAutoCompleteFormField" -->
<#-- @ftlvariable name="dataSource" type="com.bidanet.springmvc.demo.JkDataSource" -->
<#include "/libs.ftl"/>
    <label class="layui-form-label">${formField.title!}</label>
    <div class="layui-input-${layout!"block"} ${formField.getCssClassTpl()} <#if auto.type()=="remote"> jk-search-select</#if>">
        <select name="${formField.name!}"
                lay-search=""
        ${disable!}

        <@verify verifyInfo=verifyInfo/>
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
