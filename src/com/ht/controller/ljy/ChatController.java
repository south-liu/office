package com.ht.controller.ljy;

import com.ht.service.ljy.chatRecordService;
import com.ht.service.llb.IStudentService;
import com.ht.vo.ChatRecordVO;
import com.ht.vo.EmpVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JY on 2019/12/5.
 */

@Controller
@RequestMapping("/chatRecord")
public class ChatController {

    @Resource
    chatRecordService chatRecordService;

    @Resource
    IStudentService iStudentService;

    @RequestMapping("/gotochatRecordList")
    public String gotochatRecordList(){
        System.out.println("去查询谈心记录界面！！！");
        return "ljy/chatRecordList";
    }

    @RequestMapping("/gotochatRecord_add")
    public String gotochatRecordadd(Model model){
        model.addAttribute("allstu",iStudentService.allStu());
        System.out.println("去查询谈心记录界面！！！"+iStudentService.allStu());
        return "ljy/chatRecord_add";
    }

//    查询记录方法
@ResponseBody
@RequestMapping("/chatRecordlist")
    public Map chatRecordList(int page,int limit){
    Map map=new HashMap();
    Integer count=chatRecordService.chatrecord_count();
    map.put("code",0);
    map.put("msg",0);
    map.put("count",count);
    map.put("data",chatRecordService.chatrecord_list(page,limit));
    return map;
    }

//    删除记录
    @ResponseBody
    @RequestMapping("/chatRecorddelete")
    public String chatRecorddelete(ChatRecordVO chatRecordVO){
        chatRecordService.chatrecord_delete(chatRecordVO);
        System.out.println("删除成功");
        return " ";
    }

//    添加记录
    @ResponseBody
    @RequestMapping("/chatRecordadd")
    public String chatRecordadd(ChatRecordVO chatRecordVO, HttpSession session){
        System.out.println("添加记录！！");
        EmpVO empVO = (EmpVO) session.getAttribute("emp");
        chatRecordVO.setTeacher(empVO.getEmpId());
        chatRecordService.chatrecord_add(chatRecordVO);
        return " ";
    }
}
