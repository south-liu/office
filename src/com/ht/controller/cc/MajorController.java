package com.ht.controller.cc;

import com.ht.service.cc.CollegeService;
import com.ht.service.cc.MajorService;
import com.ht.vo.CollegeDeptVO;
import com.ht.vo.MajorVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/major")
public class MajorController {
    @Resource
    private MajorService ms;

    @RequestMapping("/tolist")
    public  String tolist(){
        return "cc/major/major_list";
    }

    @RequestMapping("/toaddmajor")
    public  String toaddjob(Model model){
        List<CollegeDeptVO> list = ms.AllCollege();
        model.addAttribute("collegeDeptVO",list);
        return "cc/major/major_add";
    }

    @RequestMapping("/toupdmajor")
    public  String toupdjob(Model model,int majorId){
        System.out.println("test"+ms.AllMajor(majorId));
        model.addAttribute("majorVO",ms.AllMajor(majorId));
        model.addAttribute("collegeDeptVO",ms.AllCollege());
        return "cc/major/major_upd";
    }



    @ResponseBody
    @RequestMapping("/majorlist")
    public Map collegelist(int page, int limit,Model model){
//        List list = cs.selSpage(page,rows);//每页数据
        Integer totalRow = ms.seltotalMajor();//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",ms.selSpage(page,limit));
        model.addAttribute("collegeDeptVO",ms.AllCollege());
        System.out.println("total "+totalRow);
        return map;
    }

    @RequestMapping("/addlist")
    @ResponseBody
    public  String add(MajorVO majorVO){
        ms.AddMajor(majorVO);
        return " ";
    }

    @RequestMapping("/deletelist")
    @ResponseBody
    public  String del(String majorId){
        System.out.println("这是删除方法");
        ms.delMajor(majorId);
        return "success";
    }

    @RequestMapping("updlist")
    @ResponseBody
    public String upd(MajorVO majorVO){
        ms.UpdMajor(majorVO);
        return "success";
    }
}
