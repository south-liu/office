package com.ht.controller.zwj;

import com.ht.service.llb.IEmpService;
import com.ht.service.zwj.AssessmentService;
import com.ht.service.zwj.other.emp.OEmpService;
import com.ht.service.zwj.other.student.OStudentService;
import com.ht.service.zwj.other.studentClass.OStudentClassService;
import com.ht.vo.AssessmentVO;
import com.ht.vo.StudentClassVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/assessment")
public class AssessmentController {
    @Resource
    private AssessmentService assessmentService;
    @Resource
    private IEmpService empService;
    @Resource
    private OEmpService oEmpService;
    @Resource
    private OStudentClassService oStudentClassService;
    @Resource
    private OStudentService oStudentService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        // 查询所有（班主任、任课老师）
        List<Map<String, Object>> maps = oEmpService.queryPortionEmp();
        model.addAttribute("classTeacherEmp", maps);

        return "zwj/assessment/home";
    }

    @ResponseBody
    @RequestMapping(value = "/allData", method = RequestMethod.GET)
    public Map<String, Object> allData(@RequestParam(defaultValue = "1", required = false) int page, @RequestParam(defaultValue = "10", required = false) int limit) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", assessmentService.queryAllTotalNumber());
        map.put("data", assessmentService.queryAllData(page, limit));

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> add(AssessmentVO assessment) {
        Map<String, Object> map = new HashMap<>();

        assessment.setState(0);// 新添加的考评状态为0：待考评
        try {
            long id = assessmentService.addAssessment(assessment);
            if (id > 0) {
                map.put("code", 0);
                map.put("msg", "添加成功！");
                map.put("result", id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 1);
            map.put("msg", "添加失败！");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public AssessmentVO detail(@RequestParam int assessmentId) {
        return assessmentId > 0 ? assessmentService.queryAssessmentById(assessmentId) : null;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> update(AssessmentVO assessment) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (assessment.getAssessmentId() == null) {
                throw new Exception("考评ID不能为空！");
            }

            assessmentService.updateAssessment(assessment);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 1);
            map.put("msg", "服务器错误！");
            return map;
        }
        map.put("code", 0);
        map.put("msg", "修改成功！");
        map.put("result", assessment.getAssessmentId());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam int assessmentId) {
        Map<String, Object> map = new HashMap<>();
        try {
            assessmentService.deleteAssessment(assessmentId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 1);
            map.put("msg", "服务器错误！");
            return map;
        }
        map.put("code", 0);
        map.put("msg", "删除成功！");
        map.put("result", assessmentId);
        return map;
    }

    /*@ResponseBody
    @RequestMapping(value = "/deleteMulti", method = RequestMethod.POST)
    public Map<String, Object> deleteMulti(@RequestParam(name = "assessmentIds[]") int[] assessmentIds) {
        Map<String, Object> map = new HashMap<>();

        int i = 0;
        try {
            if (assessmentIds == null) {
                throw new Exception("ID数组不能为空！");
            }
            for (int assessmentId : assessmentIds) {
                assessmentService.deleteAssessment(assessmentId);
                ++i;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 1);
            map.put("msg", "服务器错误！");
            return map;
        }

        map.put("code", 0);
        map.put("msg", "删除成功！");
        map.put("result", i);
        return map;
    }*/

    /**
     * 通过教师id查询该教师所在任职的班级
     *
     * @param empId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryStudentClassByEmpId", method = RequestMethod.GET)
    public List<StudentClassVO> queryStudentClassByEmpId(@RequestParam int empId) {
        System.out.println(empId);
        return empId > 0 ? oStudentClassService.queryStudentClassByEmpId(empId) : null;
    }

    /**
     * 提前结束考评
     *
     * @param assessmentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/endAssessment", method = RequestMethod.GET)
    public Map<String, Object> endAssessment(@RequestParam int assessmentId) {
        Map<String, Object> map = new HashMap<>();

        // 通过id查询该次考评 更改考评状态
        try {
            int id = assessmentService.changeAssessmentState(assessmentId, 1);
            if (id > 0) {
                map.put("code", 0);
                map.put("result", id);
                map.put("msg", "结束成功！");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 1);
            map.put("msg", "服务器错误！");
        }

        return map;
    }

    /**
     * 通过考评id查询该次考评的考评班级下面的所有学生
     *
     * @param assessmentId
     * @param model
     * @return
     */
    @RequestMapping(value = "/lookDetailList", method = RequestMethod.GET)
    public String lookDetailList(@RequestParam int assessmentId, Model model) {
        AssessmentVO assessment = assessmentService.queryAssessmentById(assessmentId);
        if (assessment == null || assessment.getAssessmentId() == null || assessment.getAssessmentId() < 0) {
            return null;
        }
        if (assessment.getStudentClassId() == null || assessment.getStudentClassId() < 0) {
            return null;
        }

        List<Map<String, Object>> studentList;
        try {
            // 通过考评中的学生班级查询班级下面所有的学生
            studentList = assessmentService.queryStudentByAssessmentId(assessment.getAssessmentId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            studentList = Collections.emptyList();
        }
        model.addAttribute("assessment", assessment);
        model.addAttribute("studentList", studentList);

        return "zwj/assessment/assessmentInformation";
    }

    /**
     * 查看考评的学生填入的信息详细
     *
     * @param assessmentId
     * @param studentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryAssessmentInformation", method = RequestMethod.GET)
    public Map<String, Object> queryAssessmentInformation(@RequestParam int assessmentId, @RequestParam int studentId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> assessmentInformation = assessmentService.queryAssessmentInformation(assessmentId, studentId);

            map.put("code", 0);
            map.put("result", assessmentInformation);
            map.put("msg", "查询成功！");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            map.put("code", 1);
            map.put("msg", "服务器错误！");
        }

        return map;
    }

    /**
     * 通过考评id查询这次进入这次考评的总人数
     *
     * @param assessmentId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/assessmentInformationTotality", method = RequestMethod.GET)
    public Map<String, Object> assessmentInformationTotality(@RequestParam int assessmentId) {
        Map<String, Object> map = new HashMap<>();
        try {
            long totality = assessmentService.assessmentInformationTotality(assessmentId);

            map.put("code", 0);
            map.put("result", totality);
            map.put("msg", "查询成功！");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            map.put("code", 1);
            map.put("msg", "服务区错误！");
        }

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/changeEndTime", method = RequestMethod.GET)
    public Map<String, Object> changeEndTime(@RequestParam int assessmentId, @RequestParam int time) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("code", 0);
            map.put("result", assessmentService.changeAssessmentEndTime(assessmentId, time));
            map.put("msg", "修改成功！");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            map.put("code", 1);
            map.put("msg", "服务器错误！");
        }

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/searchByOptions", method = RequestMethod.GET)
    public Map<String, Object> searchByOptions(@RequestParam(defaultValue = "1", required = false) int page, @RequestParam(defaultValue = "10", required =
            false) int limit, @RequestParam Integer empId, @RequestParam String startTime, @RequestParam String endTime) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Map<String, Object>> list = assessmentService.searchAssessmentByOptions(page, limit, empId, startTime, endTime);

            map.put("code", 0);
            map.put("msg", "查询成功！");
            map.put("data", list);
            map.put("count", assessmentService.searchAssessmentTotalityByOptions(empId, startTime, endTime));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "服务器错误！");
        }

        return map;
    }
}
