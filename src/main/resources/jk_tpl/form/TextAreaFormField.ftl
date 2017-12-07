<#include "/libs.ftl"/>
    <label class="layui-form-label">${formField.title!}</label>
    <div class="layui-input-${layout!"block"}">
        <textarea name="${formField.name!}"
                <@verify verifyInfo=verifyInfo/>
                ${formField.getAttrsTpl()}
                    ${disable!}
                ${formField.getAttrsTpl()}
                  placeholder="${formField.placeholder!}"
                  class="layui-textarea ${formField.getCssClassTpl()} ">${formField.val!}</textarea>

    </div>
