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

}
