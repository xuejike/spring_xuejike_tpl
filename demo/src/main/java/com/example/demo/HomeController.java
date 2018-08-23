package com.example.demo;

import com.bidanet.springmvc.demo.jkbuilder.data.JkMenu;
import com.example.demo.view.TestFormView;
import com.example.demo.view.TestModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xuejike.ktpl.AdminJkKtView;
import vip.xuejike.ktpl.AdminLoginKtView;

//@RequestMapping
@Controller
public class HomeController {
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        AdminJkKtView testView = new AdminJkKtView();
        testView.getInfo().setTitle("极速模板");
        //首页地址
        testView.getInfo().setIndexUrl("/index");
        //定义左菜单加载的URL
        testView.getInfo().setLeft("/left");
        //定义头部菜单加载的URL
        testView.getInfo().setTop("/top");
        //定义 右菜单内容
        testView.getInfo().getRightMenu()
                .add(new JkMenu("修改密码","","http://www.baidu.com"));
        //定义首页 初始化界面地址
        testView.getInfo().setWelcomeUrl("/welcome");
        //退出登录地址
        testView.getInfo().setLogoutUrl("/public/logout");
        //定义 当前登录的用户昵称
        testView.getInfo().setUsername("超级管理员");
        return testView.toHtml();

    }
    @RequestMapping("/form")
    @ResponseBody
    public String form(){
        TestFormView view = new TestFormView();
        view.setVo(new TestModel());
        return view.toHtml();
    }

    public String login(){
        AdminLoginKtView adminLoginKtView = new AdminLoginKtView();
        adminLoginKtView.getInfo().setActionUrl("/public/login");
        adminLoginKtView.getInfo().setTitle("登录页");
        adminLoginKtView.getInfo().setUserName("用户名");
        adminLoginKtView.getInfo().setPwd("密码");
        adminLoginKtView.getInfo().setMsg("登录失败的错误消息");
        return adminLoginKtView.toHtml();
    }
}
