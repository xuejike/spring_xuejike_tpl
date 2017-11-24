package com.bidanet.springmvc.demo.jkbuilder.type;

/**
 * 表单元素输出
 * @author xuejike
 */
public interface FormFieldHtml {

    /**
     * 输出HTML
     * @param info 表单配置
     * @return
     */
    String html(FormFieldInfo info);
    String html(FormFieldInfo info, String layout);

}
