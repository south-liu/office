package com.ht.controller.llb;

import com.ht.service.cc.MajorService;
import com.ht.service.ljy.studentclassService;
import com.ht.service.llb.IStudentService;
import com.ht.service.zwj.StudentHuorService;
import com.ht.vo.MajorVO;
import com.ht.vo.StudentClassVO;
import com.ht.vo.StudentHuorVO;
import com.ht.vo.StudentVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentHuorService studentHuorService;
    @Resource
    private IStudentService studentService;
    @Resource
    private studentclassService studentclassService;
    @Resource
    private MajorService majorService;

    @RequestMapping("/toStuList")
    public String toStuList(){
        return "llb/student/student_list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        List<StudentHuorVO> sh = studentHuorService.allData();
        List sc = studentclassService.studentclass_list();
        List<MajorVO> ms = majorService.allMajor();
        model.addAttribute("huors",sh);
        model.addAttribute("clazzs",sc);
        model.addAttribute("majors",ms);
        return "llb/student/student_add";
    }

    @RequestMapping("toEdit")
    public String toEdit(Model model,Integer studId){
        model.addAttribute("student",studentService.findById(studId));
        return "llb/student/student_edit";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map pageList(int page, int limit){
        Map map = new HashMap();
        Integer count = studentService.countStudent();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",studentService.pageList(page,limit));
        return map;
    }

    @RequestMapping("delStu")
    @ResponseBody
    public String delStu(String idstr){
        studentService.delStu(idstr);
        return "success";
    }

    @RequestMapping("addStu")
    @ResponseBody
    public String addStu(StudentVO studentVO){
        studentService.addStu(studentVO);
        return "success";
    }

    @RequestMapping("updStu")
    @ResponseBody
    public String editStu(StudentVO studentVO){
        studentService.updStu(studentVO);
        return "success";
    }


}
