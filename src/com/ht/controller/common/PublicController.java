package com.ht.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 公共控制器
@Controller
@RequestMapping("/public")
public class PublicController {

    /**
     * 首页入口
     * @author 钟文军
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "public/index";
    }

    /**
     * 登陆界面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "public/login";
    }

    /**
     * 欢迎界面
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(){
        return "public/welcome";
    }

    /**
     * 首页入口
     * @author 钟文军
     * @return
     */
    @RequestMapping("/student/index")
    public String sindex(){
        return "public/student/index";
    }

    /**
     * 登陆界面
     * @return
     */
    @RequestMapping("/student/login")
    public String slogin(){
        return "public/student/login";
    }

    /**
     * 欢迎界面
     * @return
     */
    @RequestMapping("/student/welcome")
    public String swelcome(){
        return "public/student/welcome";
    }

}
