package com.ht.controller.cc;

import com.ht.service.cc.EmpJefService;
import com.ht.service.cc.StujefService;
import com.ht.vo.*;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 教育经历（education）
 * 家庭背景（family）
 */

@Controller
@RequestMapping("/stujef")
public class StujefController {
    @Resource
    private StujefService stus;

    @RequestMapping("/toedu")
    public  String tolist(Model model){
        model.addAttribute("stuId",2);
        return "cc/stuedu_list";
    }

    @RequestMapping("/toaddedu")
    public  String toaddjob(Model model){
        model.addAttribute("stuId",2);
        return "cc/stuedu_add";
    }

    @ResponseBody
    @RequestMapping("/edulist")
    public Map collegelist(int page, int limit,int stuId){
        System.out.println(stuId);
//        List list = cs.selSpage(page,rows);//每页数据
//        Integer totalRow = stus.seltotalStu();//总行数
        Integer totalRow = stus.seltotalEduByStuId(stuId);//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",stus.selEduSpage(page,limit,stuId));
        System.out.println("total "+totalRow);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/addedu")
    @ResponseBody
    public  String add(StudentEduVO studentEduVO){
        stus.AddEdu(studentEduVO);
        return " ";
    }

    @RequestMapping("/deleteedu")
    @ResponseBody
    public  String del(String eduId){
        System.out.println("这是删除方法");
        stus.delEdu(eduId);
        return "success";
    }

    @RequestMapping("updedu")
    @ResponseBody
    public String upd(StudentEduVO studentEduVO){
        stus.UpdEdu(studentEduVO);
        return "success";
    }

    //家庭背景
    @RequestMapping("/tofam")
    public  String tofamlist(Model model){
        model.addAttribute("stuId",2);
        return "cc/stufam_list";
    }

    @RequestMapping("/toaddfam")
    public  String toaddfam(Model model){
        model.addAttribute("stuId",2);
        return "cc/stufam_add";
    }

    @ResponseBody
    @RequestMapping("/famlist")
    public Map famlist(int page, int limit,int stuId){
//        List list = cs.selSpage(page,rows);//每页数据
        //Integer totalRow = es.seltotalFam();//总行数
        Integer totalRow = stus.seltotalFamByStuId(stuId);//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",stus.selFamSpage(page,limit,stuId));
        System.out.println("total "+totalRow);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/addfam")
    @ResponseBody
    public  String addfan(StudentFamilyVO studentFamilyVO){
        stus.AddFam(studentFamilyVO);
        return " ";
    }

    @RequestMapping("/deletefam")
    @ResponseBody
    public  String delfam(String familyId){
        System.out.println("这是删除方法");
        stus.delFam(familyId);
        return "success";
    }

    @RequestMapping("updfam")
    @ResponseBody
    public String upd(StudentFamilyVO studentFamilyVO){
        stus.UpdFam(studentFamilyVO);
        return "success";
    }


    //在校情况
    //去页面
    @RequestMapping("/tozx")
    public  String tozxlist(Model model){
        model.addAttribute("stuId",2);
        return "cc/stuzx_list";
    }
    //去添加
    @RequestMapping("/toaddzx")
    public  String toaddzx(Model model , HttpSession session){
        EmpVO emp = (EmpVO) session.getAttribute("emp");
        model.addAttribute("empId",emp.getEmpId());
        model.addAttribute("stuId",2);
        return "cc/stuzx_add";
    }

    //去修改
    @RequestMapping("/toupdzx")
    public  String toupdang(Model model,int happenId){
        model.addAttribute("studentHappeningVO",stus.happlist(happenId));
        return "cc/stuzx_upd";
    }
    @ResponseBody
    @RequestMapping("/zxlist")
    public Map zxlist(int page, int limit,int stuId){
//        List list = cs.selSpage(page,rows);//每页数据
        //Integer totalRow = es.seltotalFam();//总行数
        Integer totalRow = stus.seltotalZxByStuId(stuId);//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",stus.selZxSpage(page,limit,stuId));
        System.out.println("total "+totalRow);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/addzx")
    @ResponseBody
    public  String addzx(StudentHappeningVO studentHappeningVO){
        studentHappeningVO.setOpTime(DateFormat.getDateInstance().format(new Date()));
        stus.AddZx(studentHappeningVO);
        return " ";
    }

    @RequestMapping("/deletezx")
    @ResponseBody
    public  String delzx(String happenId){
        System.out.println("这是删除方法");
        stus.delZx(happenId);
        return "success";
    }

    @RequestMapping("updzx")
    @ResponseBody
    public String updzx(StudentHappeningVO studentHappeningVO){
        stus.UpdZx(studentHappeningVO);
        return "success";
    }
}
