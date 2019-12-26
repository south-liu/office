package com.ht.controller.ljy;

import com.ht.service.cc.CollegeService;
import com.ht.service.cc.MajorService;
import com.ht.service.ljy.classTypeService;
import com.ht.service.ljy.studentclassService;
import com.ht.service.llb.IDeptService;
import com.ht.service.llb.IEmpService;
import com.ht.service.zwj.StudentFallService;
import com.ht.vo.StudentClassVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JY on 2019/12/6.
 */
@Controller
@RequestMapping("/studentclass")
public class StuClsController {

    @Resource
    private  studentclassService studentclassService;

    @Resource
    private IEmpService iEmpService;


    @Resource
    private classTypeService classTypeService;

    @Resource
    private StudentFallService studentFallService;

    @Resource
    private CollegeService collegeService;

    @Resource
    private MajorService majorService;

    //    去查询班级页面
    @RequestMapping("/gotostudentclasslist")
    public String gotostudentclasslist(Model model){
        System.out.println("查询班级管理！！！");
        model.addAttribute("studfall",studentFallService.allData());
        return "ljy/studentclass_list";
    }

    //去班级添加页面
    @RequestMapping("/gotostudentclassadd")
    public String gotostudentclassadd(Model model){
        System.out.println("添加班级管理！！！");
        model.addAttribute("studfall",studentFallService.allData());
        model.addAttribute("emplist",iEmpService.allEmp());
        model.addAttribute("cltylist",classTypeService.classTypeList());
        model.addAttribute("majorlist",majorService.allMajor());
        model.addAttribute("deptlist",collegeService.selSpage());
        return "ljy/studentclass_add";
    }


    //查询返回数据
    @ResponseBody
    @RequestMapping("/studentclasslist")
    public Map studentclasslist(int page,int limit,Model model){
        Map map=new HashMap();
        Integer count=studentclassService.studentclass_count();
        map.put("code",0);
        map.put("msg",0);
        map.put("count",count);
        map.put("data",studentclassService.studentclass_list(page, limit));
        System.out.println("查询班级管理！！！");
        return map;
    }

    @ResponseBody
    @RequestMapping("/studentclassdelete")
    public String stdentclassdelete(StudentClassVO studentClassVO){
        studentclassService.studentclass_delete(studentClassVO);
        System.out.println("删除成功！！！");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/studentclassadd")
    public Map  studentclassadd(StudentClassVO studentClassVO){
        Map map = new HashMap();
//通过班级ID查询
        StudentClassVO classVObyno = studentclassService.studentclass_byClassNo(studentClassVO.getClassNo());
        if (classVObyno != null) {
            map.put("code", 2);
            map.put("msg", "已存在相同班级编号！");
            return map;
        } else {
            StudentClassVO classVObyname = studentclassService.studentclass_byClassName(studentClassVO.getClassName());
            if (classVObyname != null) {
                map.put("code", 3);
                map.put("msg", "已存在相同班级名称");
                return map;
            } else {
                map.put("code", 0);
                map.put("msg", "添加成功！");
                studentclassService.studentclass_add(studentClassVO);
            }
        }
        return map;
    }


    @RequestMapping("/gotostudentclassupd")
    public String gotostudentclassupd(Model model,int classId){
        System.out.println("去修改界面！！！");
        model.addAttribute("studfall",studentFallService.allData());
        model.addAttribute("emplist",iEmpService.allEmp());
        model.addAttribute("cltylist",classTypeService.classTypeList());
        model.addAttribute("majorlist",majorService.allMajor());
        model.addAttribute("deptlist",collegeService.selSpage());
        model.addAttribute("stucla",studentclassService.studentclassbyid(classId));
        return "ljy/studentclass_upd";
    }

    @ResponseBody
    @RequestMapping("/studentclassupd")
    public String studentclassupd(StudentClassVO studentClassVO){
        System.out.println("修改界面！！");
        studentclassService.student_update(studentClassVO);
        return "success";
    }


    @ResponseBody
    @RequestMapping("/studentclasschoose")
    public Map studentclasschoose(int falled,int page,int limit){
        Map map=new HashMap();
        map.put("code",0);
        map.put("msg",0);
        map.put("count",studentclassService.studentclasschoose_count(falled));
        map.put("data",studentclassService.studentclass_choose(falled,page,limit));
        System.out.println("带条件查询！！！");
        return map;
    }
}
