package com.ht.controller.ljy;

import com.ht.service.ljy.chatRecordService;
import com.ht.vo.EmpVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JY on 2019/12/23.
 */
@Controller
@RequestMapping("/mychat")
public class myChatController {
    @Resource
    chatRecordService chatRecordService;


    @RequestMapping("tomychatlist")
    public String tomychatlist(){
        System.out.println("去我的谈心页面！");
        return "ljy/my_chatrecord";
    }


    @ResponseBody
    @RequestMapping("my_chatrecordlist")
    public Map my_chatrecord(HttpSession session, int page, int limit){
      EmpVO empVO= (EmpVO) session.getAttribute("emp");
        Map map=new HashMap();
        map.put("code",0);
        map.put("msg",0);
        map.put("count",chatRecordService.mychatrecord_count(empVO.getEmpId()));
        map.put("data",chatRecordService.chatrecord_mylist(page,limit,empVO.getEmpId()));
        return map;
    }

}
