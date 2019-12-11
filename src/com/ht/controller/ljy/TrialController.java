package com.ht.controller.ljy;

import com.ht.service.ljy.classTypeService;
import com.ht.service.ljy.trialService;
import com.ht.service.llb.IEmpService;
import com.ht.service.wzq.HTService;
import com.ht.vo.TrialVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JY on 2019/12/9.
 */

@Controller
@RequestMapping("/trial")
public class TrialController {

    @Resource
    trialService trialService;

    @Resource
    IEmpService iEmpService;

    @Resource
    HTService htService;


    @ResponseBody
    @RequestMapping("triallist")
    public Map triallist(int page,int limit){
        System.out.println("试讲内容！！！");
        Map map= new HashMap();
        map.put("code",0);
        map.put("msg",0);
        map.put("count",trialService.trial_count());
        map.put("data",trialService.trial_list(page, limit));
        System.out.println(map);
        return map;
    }

//    去试讲内容页面
    @RequestMapping("gototriallist")
    public String gototriallist(){
        System.out.println("去查询页面！");
        return "ljy/trial_list";
    }


    @ResponseBody
    @RequestMapping("/trialdelete")
    public int  trialdelete(@RequestParam(name = "trialId[]")Integer[] ids){
        trialService.trial_detele(ids);
        System.out.println("删除试讲内容成功！");
        return ids.length;
    }


    @RequestMapping("/gototrialadd")
    public String gototrialadd(Model model){
       model.addAttribute("emplist",iEmpService.allEmp());
        System.out.println("allemp213131231231231231:"+iEmpService.allEmp());
       model.addAttribute("courselist",htService.selcourse());
       model.addAttribute("coursetypelist",htService.selcoursetype());
        System.out.println("去添加页面");
        return "ljy/trial_add";
    }



    @ResponseBody
    @RequestMapping("/trialadd")
    public String trialadd(TrialVO trialVO){
        System.out.println("添加试讲内容成功");
        trialService.trial_add(trialVO);
        return "success";
    }
}
