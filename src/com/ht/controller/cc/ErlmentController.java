package com.ht.controller.cc;

import com.ht.service.cc.ErlmentService;
import com.ht.vo.EmpVO;
import com.ht.vo.EnrollmentVO;
import com.ht.vo.StudentVO;
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

@Controller
@RequestMapping("/erlment")
public class ErlmentController {
    @Resource
    private ErlmentService es;

    @RequestMapping("/tolist")
    private String tolist(){
        return "cc/studErlment/studentErlm_list";
    }



    @RequestMapping("/toadderlm")
    private String toadderlm(Model model){
        model.addAttribute("classtype",es.seltAllclass());
        model.addAttribute("major",es.seltAllmajor());
        return "cc/studErlment/studentErlm_add";
    }
    @RequestMapping("/toupderlm")
    private String toupderlm(Model model,int enrollmentId){
        model.addAttribute("classtype",es.seltAllclass());
        model.addAttribute("major",es.seltAllmajor());
        model.addAttribute("enrollment",es.seltAllenrollment(enrollmentId));
        return "cc/studErlment/studentErlm_upd";
    }

    //预定报名
    @RequestMapping("/toyudin")
    private String toyudin(Model model){
        return "cc/studErlment/studentErlm_toyudin";
    }
    @ResponseBody
    @RequestMapping("/erlmentlist")
    public Map collegelist(int page, int limit, Model model){
        model.addAttribute("major",es.seltAllmajor());
//        List list = cs.selSpage(page,rows);//每页数据
        Integer totalRow = es.seltotalAng();//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",es.selSpage(page,limit));
        return map;
    }

    @RequestMapping("/addlist")
    @ResponseBody
    public  String add(EnrollmentVO enrollmentVO, HttpSession session){
        EmpVO emp = (EmpVO) session.getAttribute("emp");
        String data = DateFormat.getDateInstance().format(new Date());
        enrollmentVO.setStatus(1);
        enrollmentVO.setSignDate(data);
        enrollmentVO.setLuruId(emp.getEmpName());
        es.AddErlment(enrollmentVO);
        return "success";
    }

    @RequestMapping("/deletelist")
    @ResponseBody
    public  String del(String enrollmentId){
        es.delErlment(enrollmentId);
        return "success";
    }

    //修改学生
    @RequestMapping("/updlist")
    @ResponseBody
    public String upd(EnrollmentVO enrollmentVO){
        EnrollmentVO enrVO = es.seltAllenrollment(enrollmentVO.getEnrollmentId());
        enrollmentVO.setAmount(enrVO.getAmount());
        enrollmentVO.setEnrollMoney(enrVO.getEnrollMoney());
        enrollmentVO.setStatus(enrVO.getStatus());
        enrollmentVO.setSignDate(enrVO.getSignDate());
        enrollmentVO.setStartTime(enrVO.getStartTime());
        enrollmentVO.setNegativeName(enrVO.getNegativeName());
        enrollmentVO.setLuruId(enrVO.getLuruId());
        enrollmentVO.setReviewStatus(enrVO.getReviewStatus());
        enrollmentVO.setReviewer(enrVO.getReviewer());
        enrollmentVO.setReviewerTime(enrVO.getReviewerTime());
        es.UpdErlment(enrollmentVO);
        return "success";
    }

    //修改学生入学
    @RequestMapping("/addstulist")
    @ResponseBody
    public String addstu(StudentVO studentVO,Integer enrollmentId){
        Integer stats = 4;
        String data = DateFormat.getDateInstance().format(new Date());
        es.updErlmdate(enrollmentId,data);
        es.updErlmStats(enrollmentId,stats);
        es.AddStudent(studentVO);
        return "success";
    }

    @RequestMapping("/stushixue")
    @ResponseBody
    public String addshixue(Integer enrollmentId){
        Integer stats = 3;
        es.updErlmStats(enrollmentId,stats);
        return "success";
    }

    @RequestMapping("/stuwshixue")
    @ResponseBody
    public String addwshixue(Integer enrollmentId){
        Integer stats = 6;
        es.updErlmStats(enrollmentId,stats);
        return "success";
    }

    @RequestMapping("/addamount")
    @ResponseBody
    public String yudingfei(Integer enrollmentId,float amount){
        es.updErlmamount(enrollmentId,amount);
        return "success";
    }

    @RequestMapping("/addreviewer")
    @ResponseBody
    public String addreviewer(Integer enrollmentId,String reviewer){
        String data = DateFormat.getDateInstance().format(new Date());
        es.updErlreviewer(enrollmentId,reviewer,data);
        return "success";
    }

    @RequestMapping("/addenrollMoney")
    @ResponseBody
    public String addenrollMoney(Integer enrollmentId,float enrollMoney){
        es.updErlenrollMoney(enrollmentId,enrollMoney);
        return "success";
    }
}
