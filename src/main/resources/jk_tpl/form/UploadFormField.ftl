<#-- @ftlvariable name="upload" type="com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkUploadFormField" -->
<label class="layui-form-label">${formField.title!}</label>

<div class="layui-input-block" id="upload_img_${uuid!}">
    <div class="layui-upload">
        <#if !disable??>
            <button type="button" class="layui-btn" id="upload_img_btn_${uuid!}">
                ${upload.btn()}</button>
        </#if>

        <input type="hidden" v-model="filejson" name="${formField.name!}"/>
        <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
            <#if upload.type() =="images">
                预览：
                <div class="layui-upload-list" >
                    <span class="layui-upload-img " v-for="file in files">
                        <i class="layui-icon jk-remove" v-on:click="remove(index)">&#x1007;</i>

                        <i v-if="file.status =='loading'" class="layui-icon jk-upload layui-anim layui-anim-rotate layui-anim-loop">&#xe63d;</i>
                        <img v-if="file.status =='finish'"
                             style="width: 100px;height: 100px"
                             v-bind:src="file.url" v-bind:alt="file.filename"/>

                    </span>

                    <br style="clear:both;" />
                </div>
            </#if>
            <#if upload.type() == "file">
                <div class="layui-upload-list">
                    <table class="layui-table">
                        <thead>
                        <tr><th>文件名</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr></thead>
                        <tbody >
                            <tr v-for="file in files">
                                <td>{{file.filename}}</td>
                                <td>
                                    <i class="layui-icon" v-if="file.status=='finish'" style="color: #5FB878">&#xe616;</i>
                                    <i class="layui-icon" v-if="file.status=='error'" style="color: red" >&#x1007;</i>
                                    <i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop" v-if="file.status=='loading'">&#xe63e;</i>
                                </td>
                                <td>
                                    <a target="_blank" v-bind:href="file.url" class="layui-btn" >下载</a>
                                    <a class="layui-btn layui-btn-warm" v-on:click="remove(index)">删除</a>

                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </#if>

        </blockquote>
    </div>
</div>
<script>
    uploadComponent("${uuid!}","${upload.url()}","${upload.max()}",${formField.val!"[]"},"${upload.type()}","${upload.exts()}")
</script>
