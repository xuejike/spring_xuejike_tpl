<#-- @ftlvariable name="btn" type="com.bidanet.springmvc.demo.jkbuilder.annotation.JkButton" -->
<#-- @ftlvariable name="group" type="com.bidanet.springmvc.demo.jkbuilder.type.FormFieldGroup" -->
<#if form.title()!="">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>${form.title()!}</legend>
</fieldset>
</#if>

<form class="layui-form" action="${url!}" id="${form.id()}" method="post" enctype="multipart/form-data">
    <#list groupList as group>
             <div class="layui-form-item ${group.getCssClass()}" ${group.getAttrs()} id="${group.id!}">

                 <#list group.items as formField>
                     <#if group.checkBlock()>
                         ${formField.type.html(formField)}
                        <#else>
                           <div class="layui-inline">
                            ${formField.type.html(formField,"inline")}
                           </div>

                     </#if>

                 </#list>
             </div>

    </#list>



        <div class="layui-form-item">
            <div class="layui-input-block">
                <#list btns as btn>

                    <#switch btn.type()>
                        <#case "submit">
                            <button class="layui-btn ${btn.cssClass()}" lay-submit="" lay-filter="ajax_submit">${btn.value()!}</button>
                        <#break>
                        <#case "reset">
                            <button type="reset" class="layui-btn ${btn.cssClass()}">${btn.value()!}</button>
                        <#break >
                        <#default>
                           <button class="layui-btn  ${btn.cssClass()}" lay-submit="" lay-filter="<#if btn.type()=="diy">${btn.event()}<#else>${btn.type()}</#if>"
                           data-event="<#if btn.type()=="diy">${btn.event()}<#else>${btn.type()}@${btn.url()}@${btn.option()}</#if>"
                           >${btn.value()!}</button>
                    </#switch>
                </#list>


            </div>
        </div>

    <script>

    </script>
</form>