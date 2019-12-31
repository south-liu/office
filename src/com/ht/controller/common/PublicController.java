package com.ht.controller.common;

import com.ht.service.ljy.checkworkService;
import com.ht.service.llb.INoticeService;
import com.ht.service.llb.OtherService;
import com.ht.utils.date.DatesUtil;
import com.ht.vo.EmpVO;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

// 公共控制器
@Controller
@RequestMapping("/public")
public class PublicController {

    @Resource
    private INoticeService noticeService;
    @Resource
    private TaskService taskService;
    @Resource
    private com.ht.service.ljy.checkworkService checkworkService;
    @Resource
    private OtherService otherService;

    /**
     * 首页入口
     * @author 钟文军
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "public/index";
    }

    /**
     * 登陆界面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "public/login";
    }

    /**
     * 欢迎界面
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(Model model, HttpSession session){

        EmpVO empVO = (EmpVO) session.getAttribute("emp");
        //我的员工请假审批
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(empVO.getEmpName()).list();

        //我的学生请假审批
        List<Task> shtaskList = taskService.createTaskQuery().taskAssignee(empVO.getEmpId().toString()).list();

        //我的未打卡审批
        int countCheckWork = checkworkService.checkwork_mycheckcountNo(empVO.getEmpId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取本周的开始时间
        String beginDayOfWeek = sdf.format(DatesUtil.getBeginDayOfWeek());
        //获取本周的结束时间
        String endDayOfWeek = sdf.format(DatesUtil.getEndDayOfWeek());
        //本周工作周报
        String myweekly = "";
        int mw = otherService.myWeeklyOfTime(empVO.getEmpId(),beginDayOfWeek,endDayOfWeek);
        if (mw == 0) {
            myweekly = "未完成";
        } else {
            myweekly = "已完成";
        }

        // 获取本月的开始时间
        String beginDayOfMonth = sdf.format(DatesUtil.getBeginDayOfMonth());
        // 获取本月的结束时间
        String endDayOfMonth = sdf.format(DatesUtil.getEndDayOfMonth());
        //本月谈心记录
        int mychat = otherService.mychatRecordOfTime(empVO.getEmpId(),beginDayOfMonth,endDayOfMonth);

        //所有员工公告
        List<Map> allNotice = noticeService.allNoticeBySql();
        //System.out.println("noticeId"+allNotice.get(0).get("noticeId"));



        List<Map> myNotice = new ArrayList<>();
        for (Map notice : allNotice) {
            Integer noticeType = (Integer) (notice.get("noticeType"));
            if (noticeType!=3) {
                Map map = new HashMap();
                map.put("noticeId",notice.get("noticeId"));
                map.put("title",notice.get("title"));
                map.put("noticeIime",notice.get("noticeIime"));
                map.put("empName",notice.get("empName"));
                myNotice.add(map);
            }
        }
        model.addAttribute("myNotice",myNotice);
        model.addAttribute("myEmpHolidayTask",taskList.size());
        model.addAttribute("myStuHolidayTask",shtaskList.size());
        model.addAttribute("countCheckWork",countCheckWork);
        model.addAttribute("myweekly",myweekly);
        model.addAttribute("mychat",mychat);
        return "public/welcome";
    }

    @RequestMapping("/error")
    public String error(){
        return "error/error";
    }

}
