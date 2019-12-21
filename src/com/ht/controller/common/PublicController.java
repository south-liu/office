package com.ht.controller.common;

import com.ht.service.llb.INoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 公共控制器
@Controller
@RequestMapping("/public")
public class PublicController {

    @Resource
    private INoticeService noticeService;

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
    public String welcome(Model model){
        List<Map> allNotice = noticeService.allNoticeBySql();
        //System.out.println("noticeId"+allNotice.get(0).get("noticeId"));
        List<Map> myNotice = new ArrayList<>();
        for (Map notice : allNotice) {
            Integer noticeType = (Integer) (notice.get("noticeType"));
            if (noticeType!=3) {
                Map map = new HashMap();
                map.put("noticeId",notice.get("noticeId"));
                map.put("title",notice.get("title"));
                map.put("noticeIime",notice.get("noticeIime"));
                map.put("empName",notice.get("empName"));
                myNotice.add(map);
            }
        }
        model.addAttribute("myNotice",myNotice);
        return "public/welcome";
    }

    @RequestMapping("/error")
    public String error(){
        return "error/error";
    }

}
