package com.ht.controller.cc;

import com.ht.service.cc.FeedBackService;
import com.ht.utils.file.FileUpload;
import com.ht.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/feedback")
public class FeedBackController {
    @Resource
    private FeedBackService fs;

    @RequestMapping("/tolist")
    public String list() {
        return "cc/feedback/feedback_list";
    }

    @RequestMapping("/toemplist")
    public String emplist() {
        return "cc/feedback/empfeedback_list";
    }


    @RequestMapping("/toadd")
    public String toadd(Model model) {
        model.addAttribute("deptVO", fs.AllDept());
        return "cc/feedback/feedback_add";
    }


    @RequestMapping("/toMsg")
    public String tofeedbackMsg(int feedbackId,Model model) {
        model.addAttribute("feedBackMsgVO",fs.AllMsg(feedbackId));
        model.addAttribute("feedbackVO",fs.feedbackbyId(feedbackId));
        return "cc/feedback/feedback_msg";
    }


    @ResponseBody
    @RequestMapping("/allfeed_list")
    public Map allfeedbacklist(int page, int limit, Model model) {
//        List list = cs.selSpage(page,rows);//每页数据
        Integer totalRow = fs.seltotalFeed();//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", totalRow);
        map.put("data", fs.selSpage(page, limit));
        System.out.println("total " + totalRow);
        return map;
    }

    @ResponseBody
    @RequestMapping("/stufeed_list")
    public Map stufeedbacklist(int page, int limit, Model model,HttpSession session) {
        StudentVO stu = (StudentVO) session.getAttribute("student");
        String stuId = stu.getStudId().toString();
//        List list = cs.selSpage(page,rows);//每页数据
        Integer totalRow = fs.seltotalFeedbyId(stuId);//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", totalRow);
        map.put("data", fs.stuselSpage(page, limit,stuId));
        System.out.println("total " + totalRow);
        return map;
    }

    //学生反馈问题
    @RequestMapping("/addlist")
    @ResponseBody
    public String add(FeedbackVO feedbackVO, HttpSession session, MultipartFile file) {
        StudentVO stu = (StudentVO) session.getAttribute("student");
        String data = DateFormat.getDateInstance().format(new Date());
        feedbackVO.setEmpId(stu.getStudId().toString());
        feedbackVO.setEmpname(stu.getStuName());
        feedbackVO.setStatus(1);//1为未处理
        feedbackVO.setFeedbackTime(data);//提交时间

        //图片上传
        String filename = file.getOriginalFilename();
        String realPath = session.getServletContext().getRealPath("");
        String dirPath = realPath + "WEB-INF\\static\\feedBackImg\\";
        feedbackVO.setImage("feedBackImg\\" + filename);

        fs.AddFeed(feedbackVO);
        //上传文件
        FileUpload.upload(dirPath, file);
        return "success";
    }

    @RequestMapping("/deletelist")
    @ResponseBody
    public String del(String feedbackId) {
        fs.delFeed(feedbackId);
        return "success";
    }

    @RequestMapping("/addmsg")
    @ResponseBody
    public String addmsg(FeedBackMsgVO feedBackMsgVO,HttpSession session,int feedbackId) {
        StudentVO stu = (StudentVO) session.getAttribute("student");
        EmpVO emp = (EmpVO) session.getAttribute("emp");

        if (stu!=null){
            feedBackMsgVO.setUserName(stu.getStuName());
            feedBackMsgVO.setUserId(stu.getStudId());
        }else {
            feedBackMsgVO.setUserName(emp.getEmpName());
            feedBackMsgVO.setUserId(emp.getEmpId());
        }

        Date newdata = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = simpleDateFormat.format(newdata);

        feedBackMsgVO.setSingDate(data);
        fs.AddMsg(feedBackMsgVO,feedbackId);
        return "success";
    }
}
