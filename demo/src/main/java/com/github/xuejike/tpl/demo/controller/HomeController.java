package com.github.xuejike.tpl.demo.controller;

import com.bidanet.springmvc.demo.jkbuilder.JkTplBuilder;
import com.github.xuejike.tpl.demo.controller.view.FormView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        FormView formView = new FormView();
        formView.setTl("123456");
        formView.setDate(new Date());
        return JkTplBuilder.build(formView);
    }
}
