package com.ht.controller.cc;

import com.ht.service.cc.CollegeService;
import com.ht.vo.CollegeDeptVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Chengcollege")
public class CollegeController {

    @Resource
    private CollegeService cs;
    
    @RequestMapping("/tolist")
    public  String tolist(){
        return "cc/colege/collegelist";
    }
    @ResponseBody
    @RequestMapping("/collegelist")
    public Map collegelist(int page,int limit){
//        List list = cs.selSpage(page,rows);//每页数据
        Integer totalRow = cs.seltotalClege();//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",cs.selSpage(page,limit));
        System.out.println("total "+totalRow);
        return map;
    }
    
    @RequestMapping("/addlist")
    @ResponseBody
    public  String add(CollegeDeptVO collegeDeptVO){
        cs.AddClege(collegeDeptVO);
        return " ";
    }

    @RequestMapping("/deletelist")
    @ResponseBody
    public  String del(String collegeDeptId){
        System.out.println("这是删除方法");
        cs.delClege(collegeDeptId);
        return "success";
    }
    
    @RequestMapping("updlist")
    @ResponseBody
    public String upd(CollegeDeptVO collegeDeptVO){
        cs.UpdClege(collegeDeptVO);
        return "success";  
    }
}
