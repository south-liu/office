package com.ht.controller.zwj;

import com.ht.service.llb.IEmpService;
import com.ht.service.llb.IStudentService;
import com.ht.service.zwj.ProjectService;
import com.ht.service.zwj.StudentReplyScoreService;
import com.ht.service.zwj.other.student.OStudentService;
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

// 学生答辩成绩Controller
@Controller
@RequestMapping("/student-reply-score")
public class StudentReplyScoreController {
    @Resource
    private StudentReplyScoreService studentReplyScoreService;
    @Resource
    private IStudentService studentService;
    @Resource
    private ProjectService projectService;
    @Resource
    private IEmpService empService;
    @Resource
    private com.ht.service.ljy.studentclassService studentclassService;
    @Resource
    private OStudentService ostudentService;

    @RequestMapping("/home")
    public String home(Model model) {
        List studentClassList = studentclassService.studentclass_list();// 所有学生班级
        List<ProjectVO> projectVOS = projectService.allData();// 所有的答辩项目

        model.addAttribute("studentClassList", studentClassList);
        model.addAttribute("projectList", projectVOS);
        return "zwj/student-reply-score/home";
    }

    @RequestMapping(value = "/preview", method = RequestMethod.GET)
    public String preview(@RequestParam Integer stuId, Model model) {
        System.out.println(stuId);

        // 查询该学生所有的答辩项目
        List<ProjectVO> projectList = studentReplyScoreService.findGradedProjectByStudentId(stuId);

        model.addAttribute("projectList", projectList);

        return "zwj/student-reply-score/preview";
    }

    @ResponseBody
    @RequestMapping(value = "/findDataToPreview", method = RequestMethod.GET)
    public Map<String, Object> findDataToPreview(@RequestParam Integer stuId, @RequestParam Integer projectId) {
        // 查询该学生所有的答辩项目
        Map<String, Object> map = studentReplyScoreService.findProjectScoreByOptions(stuId, projectId);
        System.out.println(map);

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/allData", method = RequestMethod.GET)
    public Map<String, Object> allData(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        // 通过分页返回List数据
        List<Map<String, Object>> VOS = studentReplyScoreService.allData(page, limit);

        // leyui dataTable固定返回的json数据格式
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", studentReplyScoreService.getTotality());
        hashMap.put("data", VOS);

        return hashMap;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> allStudent(StudentReplyScoreVO studentReplyScore, @RequestParam(name = "studentIds") String studentIdsString, HttpSession session) {
        String[] studentIdsArr = studentIdsString.split(",");

        EmpVO emp = (EmpVO) session.getAttribute("emp");// 获取存在session中的当前员工教师
        // 计算答辩成绩 插入多人时的分数都是一样的
        float totalPoints = studentReplyScore.getScore1() + studentReplyScore.getScore2() + studentReplyScore.getScore3() + studentReplyScore.getScore4() + studentReplyScore.getScore5() + studentReplyScore.getScore6();

        Map<String, Object> hashMap = new HashMap<>();
        try {
            int successCount = 0;// 插入成功次数
            for (String s : studentIdsArr) {
                studentReplyScore.setScore7(totalPoints);
                studentReplyScore.setStudentId(Integer.parseInt(s));// 为每条记录设置学生id
                studentReplyScore.setGradeEmpId(emp.getEmpId());// 将session中的当前教师设置为打分老师

                if (studentReplyScoreService.addStudentReplyScore(studentReplyScore) > 0) {
                    ++successCount;// 插入成功加一
                }
            }

            hashMap.put("code", 0);
            hashMap.put("msg", "添加成功！");
            hashMap.put("result", successCount);
            return hashMap;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            hashMap.put("code", 1);
            hashMap.put("msg", "服务器错误！");
            return hashMap;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> updateStudent(StudentReplyScoreVO studentReplyScore, @RequestParam(name = "studentIds") Integer studentIdsString, HttpSession session) {
        EmpVO emp = (EmpVO) session.getAttribute("emp");// 当前打分老师
        studentReplyScore.setGradeEmpId(emp.getEmpId());

        // 计算答辩成绩
        studentReplyScore.setScore7(studentReplyScore.getScore1() + studentReplyScore.getScore2() + studentReplyScore.getScore3() + studentReplyScore.getScore4() + studentReplyScore.getScore5() + studentReplyScore.getScore6());
        studentReplyScore.setStudentId(studentIdsString);
        Map<String, Object> map = new HashMap<>();
        try {
            studentReplyScoreService.updateStudentReplyScore(studentReplyScore);

            map.put("code", 0);
            map.put("msg", "修改成功！");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 2);
            map.put("msg", "服务器错误！");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, Object> deleteStudent(Integer replyId) {
        Map<String, Object> map = new HashMap<>();
        try {
            studentReplyScoreService.deleteStudentReplyScore(replyId);

            map.put("code", 0);
            map.put("msg", "删除成功！");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 2);
            map.put("msg", "服务器错误！");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteMulti", method = RequestMethod.POST)
    public Map<String, Object> deleteMulti(@RequestParam(name = "replyIds[]") Integer[] replyIds) {
        Map<String, Object> map = new HashMap<>();
        try {
            studentReplyScoreService.deleteMultiStudentReplyScore(replyIds);

            map.put("code", 0);
            map.put("msg", "删除成功！");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 2);
            map.put("msg", "服务器错误！");
        }
        return map;
    }

    /**
     * 查询所有学生、答辩项目、打分老师
     * 用户前台数据展示
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addTheNeed", method = RequestMethod.GET)
    public Map<String, Object> addTheNeed() {
        List<StudentVO> studentVOS = studentService.allStu();
        List<ProjectVO> projectVOS = projectService.allData();
        List<EmpVO> empVOS = empService.allEmp();
        List studentClassList = studentclassService.studentclass_list();

        Map<String, Object> map = new HashMap<>();
        map.put("studentList", studentVOS);
        map.put("projectList", projectVOS);
        map.put("empList", empVOS);
        map.put("studentClassList", studentClassList);

        return map;
    }

    /**
     * 通过班级id查询所有的学生
     *
     * @param studentClassId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/allStudentByStudentClassId", method = RequestMethod.GET)
    public List<Map<String, Object>> allStudentByStudentClassId(Integer studentClassId) {
        return ostudentService.findStudentByClassId(studentClassId);
    }

    /**
     * 查询所有学生、答辩项目、打分老师
     * 用户前台数据展示
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editTheNeed", method = RequestMethod.GET)
    public Map<String, Object> editTheNeed(@RequestParam Integer replyId) {
        List<StudentVO> studentVOS = studentService.allStu();
        List<ProjectVO> projectVOS = projectService.allData();
        List<EmpVO> empVOS = empService.allEmp();
        List studentClassList = studentclassService.studentclass_list();
        StudentReplyScoreVO studentReplyScore = studentReplyScoreService.getStudentReplyScoreById(replyId);
        StudentClassVO currentStudentClass = studentclassService.studentclassbyid(studentService.findById(studentReplyScore.getStudentId()).getClazz());

        Map<String, Object> map = new HashMap<>();
        map.put("studentList", studentVOS);
        map.put("projectList", projectVOS);
        map.put("empList", empVOS);
        map.put("studentClassList", studentClassList);
        map.put("studentReplyScore", studentReplyScore);// 当前答辩成绩的记录信息
        map.put("currentStudentClass", currentStudentClass);// 当前学生的班级信息

        return map;
    }

    /**
     * 列表的搜索：需要通过项目id，班级id，评分老师id
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Map<String, Object> search(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit, @RequestParam int projectId, @RequestParam int studentClassId) {
        List<Map<String, Object>> list = studentReplyScoreService.findStudentReplyScoreByOptions(projectId, studentClassId, page, limit);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", studentReplyScoreService.findStudentReplyScoreCountByOptions(projectId, studentClassId));
        map.put("data", list);

        return map;
    }
}
