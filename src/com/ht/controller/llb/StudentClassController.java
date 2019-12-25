package com.ht.controller.llb;

import com.alibaba.fastjson.JSONArray;
import com.ht.service.llb.IStudentService;
import com.ht.service.llb.OtherService;
import com.ht.service.zwj.StudentFallService;
import com.ht.vo.StudentClassVO;
import com.ht.vo.StudentFallVO;
import com.ht.vo.StudentVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student-class")
public class StudentClassController {

    @Resource
    private com.ht.service.ljy.studentclassService studentclassService;
    @Resource
    private IStudentService studentService;
    @Resource
    private StudentFallService studentFallService;
    @Resource
    private OtherService otherService;
    @Resource
    com.ht.service.ljy.classTypeService classTypeService;

    @RequestMapping("home")
    public String home(Model model){
        //班级类别
        List<Map> clist = classTypeService.classTypeList();
        model.addAttribute("classTypes",clist);
        return "llb/student-class/home";
    }

    @RequestMapping("/allClass")
    @ResponseBody
    public JSONArray allClass(){
        JSONArray resArr = new JSONArray();
        List<StudentFallVO> fallVOS = studentFallService.allData();
        List<StudentClassVO> classVOS = otherService.studentClassList();
        for (StudentFallVO fallVO : fallVOS) {
            Map fallMap = new HashMap();
            fallMap.put("title",fallVO.getLevel());
            fallMap.put("id","fall"+fallVO.getFallId());
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
       return resArr;
    }

    //所有没有班级的学生
    @RequestMapping("/allNoClassStu")
    @ResponseBody
    public Map allNoClassStu(){
        Map map = new HashMap();

        List<StudentVO> studentVOList = new ArrayList<>();
        List<StudentVO> all = studentService.allStu();
        for (StudentVO studentVO : all) {
            if (studentVO.getClazz() == null) {
                studentVOList.add(studentVO);
            }
        }
        map.put("code",0);
        map.put("msg","");
        map.put("count",studentVOList.size());
        map.put("data",studentVOList);
        return map;
    }

    //所有该班级的学生
    @RequestMapping("/allThisClassStu")
    @ResponseBody
    public Map allThisClassStu(Integer clazz){
        Map map = new HashMap();
        List<Map> studentVOList = new ArrayList<>();
        List<StudentVO> all = studentService.allStu();
        for (StudentVO studentVO : all) {
            if (studentVO.getClazz() == clazz) {
                Map stuMap = new HashMap();
                stuMap.put("clazz",studentclassService.studentclassbyid(studentVO.getClazz()).getClassName());
                stuMap.put("stuName",studentVO.getStuName());
                stuMap.put("sex",studentVO.getSex());
                stuMap.put("phone",studentVO.getPhone());
                studentVOList.add(stuMap);
            }
        }
        map.put("code",0);
        map.put("msg","");
        map.put("count",studentVOList.size());
        map.put("data",studentVOList);
        return map;
    }

    //调整班级
    @RequestMapping("/updClass")
    @ResponseBody
    public String updClass(@RequestParam(name = "stuIds[]") int[] stuIds, Integer classId){
        for (int stuId : stuIds) {
            //调整学生班级
            otherService.updStudentClass(stuId,classId);
        }
        //调整班级人数
        otherService.updClassCount(classId,stuIds.length);
        return "success";
    }
}
