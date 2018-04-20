package com.example.demo;

import com.example.demo.view.TestFormView;
import com.example.demo.view.TestModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xuejike.ktpl.AdminJkKtView;

//@RequestMapping
@Controller
public class HomeController {
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        AdminJkKtView testView = new AdminJkKtView();


        testView.getInfo().setTitle("极速模板");

        return testView.toHtml();

    }
    @RequestMapping("/form")
    @ResponseBody
    public String form(){
//        FormJkKtView view = new FormJkKtView();
        TestFormView view = new TestFormView();
        view.setVo(new TestModel());
        return view.toHtml();
    }
}
