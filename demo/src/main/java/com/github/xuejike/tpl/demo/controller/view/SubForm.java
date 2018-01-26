package com.github.xuejike.tpl.demo.controller.view;

import com.bidanet.springmvc.demo.jkbuilder.annotation.JkCssClass;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkForm;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkPlaceholder;
import com.bidanet.springmvc.demo.jkbuilder.annotation.JkTitle;
import com.bidanet.springmvc.demo.jkbuilder.annotation.ext.JkTextFormField;
import lombok.Data;

import java.util.Date;

@Data
public class SubForm {
    @JkTextFormField
    @JkTitle("sss")
    @JkCssClass({"l1","l2","l3"})
    @JkPlaceholder("ss")
    private String tl;
    @JkTitle("sss11")
    @JkTextFormField
    private Date date;
}
