package com.ht.controller.cc;

import com.ht.service.cc.AngService;
import com.ht.vo.CollegeDeptVO;
import com.ht.vo.EmpVO;
import com.ht.vo.MajorVO;
import com.ht.vo.WeekArrangeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/weekang")
public class WeekAngController {
    @Resource
    AngService as;

    @RequestMapping("/tolist")
    public  String tolist(){
        return "cc/weekAng/ang_list";
    }

    @RequestMapping("/toaddang")
    public  String toaddjob(Model model){
        List<EmpVO> list = as.Allemp();
        model.addAttribute("empVO",list);
        return "cc/weekAng/ang_add";
    }

    @RequestMapping("/toupdang")
    public  String toupdang(Model model,int weekArrangeId){
        System.out.println("test"+as.AllAng(weekArrangeId));
        model.addAttribute("weekArrangeVO",as.AllAng(weekArrangeId));
        model.addAttribute("empVO",as.Allemp());
        System.out.println(as.Allemp());
        return "cc/weekAng/ang_upd";
    }

    @ResponseBody
    @RequestMapping("/ang_list")
    public Map collegelist(int page, int limit, Model model){
//        List list = cs.selSpage(page,rows);//每页数据
        Integer totalRow = as.seltotalAng();//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",as.selSpage(page,limit));
        model.addAttribute("empVO",as.Allemp());
        System.out.println("total "+totalRow);
        System.out.println(as.selSpage(page,limit));
        return map;
    }

    @RequestMapping("/addlist")
    @ResponseBody
    public  String add(WeekArrangeVO weekArrangeVO){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        weekArrangeVO.setStartTime(sim.format(new Date()));
        as.AddAng(weekArrangeVO);
        return " ";
    }

    @RequestMapping("/deletelist")
    @ResponseBody
    public  String del(String angId){
        as.delAng(angId);
        return "success";
    }

    @RequestMapping("updlist")
    @ResponseBody
    public String upd(WeekArrangeVO weekArrangeVO){
        as.UpdAng(weekArrangeVO);
        return "success";
    }

}
