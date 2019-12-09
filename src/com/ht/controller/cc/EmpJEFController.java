package com.ht.controller.cc;

import com.ht.service.cc.EmpJefService;
import com.ht.vo.CollegeDeptVO;
import com.ht.vo.FamilyInfoVO;
import com.ht.vo.JobVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 工作经历（job）
 * 教育经历（education）
 * 家庭背景（family）
 */

@Controller
@RequestMapping("/CJEF")
public class EmpJEFController {
    @Resource
    private EmpJefService es;

    @RequestMapping("/tojob")
    public  String tolist(Model model, int empId){
        model.addAttribute("empId",empId);
        return "cc/joblist";
    }

    @RequestMapping("/toaddjob")
    public  String toaddjob(Model model, int empid){
        model.addAttribute("empId",empid);
        return "cc/addjob";
    }

    @ResponseBody
    @RequestMapping("/joblist")
    public Map collegelist(int page, int limit,int empId){
//        List list = cs.selSpage(page,rows);//每页数据
//        Integer totalRow = es.seltotalJob();//总行数
        Integer totalRow = es.seltotalJobByEmpId(empId);//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",es.selJobSpage(page,limit,empId));
        System.out.println("total "+totalRow);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/addjob")
    @ResponseBody
    public  String add(JobVO jobVO){
        es.AddJob(jobVO);
        return " ";
    }

    @RequestMapping("/deletejob")
    @ResponseBody
    public  String del(String jobId){
        System.out.println("这是删除方法");
        es.delJob(jobId);
        return "success";
    }

    @RequestMapping("updjob")
    @ResponseBody
    public String upd(JobVO jobVO){
        es.UpdJob(jobVO);
        return "success";
    }

    //家庭背景
    @RequestMapping("/tofam")
    public  String tofamlist(Model model,int empId){
        model.addAttribute("empId",empId);
        return "cc/famlist";
    }

    @RequestMapping("/toaddfam")
    public  String toaddfam(Model model, int empId){
        model.addAttribute("empId",empId);
        return "cc/addfam";
    }

    @ResponseBody
    @RequestMapping("/famlist")
    public Map famlist(int page, int limit,int empId){
//        List list = cs.selSpage(page,rows);//每页数据
        //Integer totalRow = es.seltotalFam();//总行数
        Integer totalRow = es.seltotalFamByEmpId(empId);//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",es.selFamSpage(page,limit,empId));
        System.out.println("total "+totalRow);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/addfam")
    @ResponseBody
    public  String addfan(FamilyInfoVO familyInfoVO){
        es.AddFam(familyInfoVO);
        return " ";
    }

    @RequestMapping("/deletefam")
    @ResponseBody
    public  String delfam(String famId){
        System.out.println("这是删除方法");
        es.delFam(famId);
        return "success";
    }

    @RequestMapping("updfam")
    @ResponseBody
    public String upd(FamilyInfoVO familyInfoVO){
        es.UpdFam(familyInfoVO);
        return "success";
    }
}
