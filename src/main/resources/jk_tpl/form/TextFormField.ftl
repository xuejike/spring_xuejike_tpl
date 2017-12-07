<#include "/libs.ftl"/>
    <label class="layui-form-label">${formField.title!}</label>
    <div class="layui-input-${layout!"block"}">
        <input type="${type!"text"}" name="${formField.name!}"
               autocomplete="off"

                ${disable!}
                value="${formField.val!}"
                <@verify verifyInfo=verifyInfo/>

                ${formField.getAttrsTpl()}
               placeholder="${formField.placeholder!}"
               class="layui-input ${formField.getCssClassTpl()}">
    </div>
