package com.ht.controller.zwj;

import com.ht.service.llb.IEmpService;
import com.ht.service.llb.IStudentService;
import com.ht.service.zwj.AssessmentService;
import com.ht.service.zwj.EvaluationStandardService;
import com.ht.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/s/assessment")
public class SAssessmentController {
    @Resource
    private AssessmentService assessmentService;
    @Resource
    private IEmpService empService;
    @Resource
    private EvaluationStandardService evaluationStandardService;
    @Resource
    private IStudentService studentService;

    // 填写考评
    @RequestMapping(value = "/writerAssessment", method = RequestMethod.GET)
    public String writerAssessment(HttpSession session, @RequestParam int assessmentId, Model model) {
        StudentVO student = (StudentVO) session.getAttribute("student");// 获取session中的学生信息
        if (student == null) {
            session.setAttribute("student", studentService.findById(4));
            student = (StudentVO) session.getAttribute("student");
        }

        // 如果session中没有值，则未登录
        if (student == null) {
            model.addAttribute("errorState", true);
            model.addAttribute("msg", "你还未登录，请登录！");
            return "zwj/assessment/writerAssessment";
        } else {
            // 登录后是否参加过考评
            Map<String, Object> assessmentInformationMap = assessmentService.queryAssessmentInformation(assessmentId, student.getStudId());
            if (assessmentInformationMap != null) {
                model.addAttribute("errorState", true);
                model.addAttribute("msg", "你已经参加过这次考评！");
                return "zwj/assessment/writerAssessment";
            }
        }

        try {
            AssessmentVO assessment = assessmentService.queryAssessmentById(assessmentId);// 当前考评信息
            EmpVO emp = empService.findEmpById(assessment.getEmpId());// 当前考评的教师
            // 当前考评的类型
            List<EvaluationVO> evaluationStandardList = evaluationStandardService.queryAllDataByType(emp.getPostName().equals("授课老师") ? 1 : 2);

            model.addAttribute("assessment", assessment);
            model.addAttribute("assessmentEmp", emp);
            model.addAttribute("evaluationStandardList", evaluationStandardList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "zwj/assessment/writerAssessment";
    }

    // 提交考评
    @ResponseBody
    @RequestMapping(value = "/insertAssessmentInformation", method = RequestMethod.POST)
    public Map<String, Object> insertAssessmentInformation(HttpSession session, @RequestParam int assessmentId,
                                                           AssessmentInformationVO assessmentInformationVO) {
        Map<String, Object> map = new HashMap<>();

        // 检查是否登录
        StudentVO student = (StudentVO) session.getAttribute("student");
        if (student == null) {
            map.put("code", 3);
            map.put("msg", "你还未登录，请登录！");
            return map;
        }

        try {
            // 未参加过考评，则插入本次考评信息
            assessmentInformationVO.setStudentId(student.getStudId());// 设置当前学生id
            long result = assessmentService.insertAssessmentInformation(assessmentInformationVO);

            map.put("code", 0);
            map.put("result", result);
            map.put("msg", "考评成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "服务器错误！");
        }

        return map;
    }
}
