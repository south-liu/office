package com.ht.controller.ljy;

import com.ht.service.ljy.studentclassService;
import com.ht.service.zwj.StudentFallService;
import com.ht.vo.StudentClassVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JY on 2019/12/6.
 */
@Controller
@RequestMapping("/studentclass")
public class StuClsController {

    @Resource
   private  studentclassService studentclassService;

    @Resource
    private StudentFallService studentFallService;

//    去查询班级页面
    @RequestMapping("/gotostudentclasslist")
    public String gotostudentclasslist(Model model){
        System.out.println("查询班级管理！！！");
         model.addAttribute("studfall",studentFallService.allData());
        return "ljy/studentclass_list";
    }

//去班级添加页面
    @RequestMapping("/gotostudentclassadd")
    public String gotostudentclassadd(Model model){
        System.out.println("添加班级管理！！！");
        model.addAttribute("studfall",studentFallService.allData());
        return "ljy/studentclass_add";
    }


//查询返回数据
    @ResponseBody
    @RequestMapping("/studentclasslist")
    public Map studentclasslist(int page,int limit,Model model){
        Map map=new HashMap();
        Integer count=studentclassService.studentclass_count();
        map.put("code",0);
        map.put("msg",0);
        map.put("count",count);
        map.put("data",studentclassService.studentclass_list(page, limit));
        System.out.println("查询班级管理！！！");
        return map;
    }

    @ResponseBody
    @RequestMapping("/studentclassdelete")
    public String stdentclassdelete(StudentClassVO studentClassVO){
        studentclassService.studentclass_delete(studentClassVO);
        System.out.println("删除成功！！！");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/studentclassadd")
    public String  studentclassadd(StudentClassVO studentClassVO){
        studentclassService.studentclass_add(studentClassVO);
        System.out.println("添加成功！！！");
        return "success";
    }
}
