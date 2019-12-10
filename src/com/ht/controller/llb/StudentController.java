package com.ht.controller.llb;

import com.alibaba.fastjson.JSONArray;
import com.ht.service.cc.MajorService;
import com.ht.service.ljy.studentclassService;
import com.ht.service.llb.IStudentService;
import com.ht.service.llb.OtherService;
import com.ht.service.wzq.HTService;
import com.ht.service.zwj.StudentFallService;
import com.ht.service.zwj.StudentHuorService;
import com.ht.vo.*;
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
    private StudentFallService studentFallService;
    @Resource
    private MajorService majorService;
    @Resource
    private HTService htService;
    @Resource
    private OtherService otherService;

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

    @RequestMapping("toUpdHuor")
    public String toUpdHuor(Model model,Integer studId){
        JSONArray resArr = new JSONArray();
        List<StudentFloorVO> floorVOS = htService.selfloor();
        for (StudentFloorVO floorVO : floorVOS) {
            Map floorMap = new HashMap();
            floorMap.put("title",floorVO.getFloorName());
            floorMap.put("id",floorVO.getFloorId());
            JSONArray huorArr = new JSONArray();
            for (StudentHuorVO huorVO : studentHuorService.allDataByFloorId(floorVO.getFloorId())) {
                Map childrenMap = new HashMap();
                childrenMap.put("title", huorVO.getHuorName());
                childrenMap.put("id", huorVO.getHourId());
                huorArr.add(childrenMap);
            }
            floorMap.put("children",huorArr);
            resArr.add(floorMap);
        }
        model.addAttribute("student",studentService.findById(studId));
        model.addAttribute("huors",resArr.toJSONString());
        return "llb/student/upd_huor";
    }

    @RequestMapping("toUpdClass")
    public String toUpdClass(Model model,Integer studId){
        JSONArray resArr = new JSONArray();
        List<StudentFallVO> fallVOS = studentFallService.allData();
        List<StudentClassVO> classVOS = otherService.studentClassList();
        for (StudentFallVO fallVO : fallVOS) {
            Map fallMap = new HashMap();
            fallMap.put("title",fallVO.getLevel());
            fallMap.put("id",fallVO.getFallId());
            JSONArray classArr = new JSONArray();
            for (StudentClassVO classVO : classVOS) {
                if (fallVO.getFallId() == classVO.getFalled()) {
                    Map childrenMap = new HashMap();
                    childrenMap.put("title", classVO.getClassName());
                    childrenMap.put("id", classVO.getClassId());
                    classArr.add(childrenMap);
                }
            }
            fallMap.put("children",classArr);
            resArr.add(fallMap);
        }
        model.addAttribute("student",studentService.findById(studId));
        model.addAttribute("stuClass",resArr.toJSONString());
        return "llb/student/upd_class";
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
