package com.ht.controller.llb;

import com.alibaba.fastjson.JSONArray;
import com.ht.service.cc.MajorService;
import com.ht.service.ljy.studentclassService;
import com.ht.service.llb.IStudentService;
import com.ht.service.llb.OtherService;
import com.ht.service.wzq.HTService;
import com.ht.service.zwj.StudentFallService;
import com.ht.service.zwj.StudentHuorService;
import com.ht.service.zwj.StudentReplyScoreService;
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
    private OtherService otherService;
    @Resource
    private StudentReplyScoreService studentReplyScoreService;

    @RequestMapping("/toStuList")
    public String toStuList(Model model){
        List<StudentClassVO> classVOS = otherService.studentClassList();
        List<StudentHuorVO> huorVOS = studentHuorService.allData();
        model.addAttribute("clazzs",classVOS);
        model.addAttribute("huors",huorVOS);
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

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Integer studId){
        model.addAttribute("student",studentService.findById(studId));
        return "llb/student/student_edit";
    }

    @RequestMapping("/toUpdHuor")
    public String toUpdHuor(Model model,Integer studId){
        JSONArray resArr = new JSONArray();
        List<StudentFloorVO> floorVOS = otherService.allFloor();
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

    @RequestMapping("/toUpdClass")
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

    @RequestMapping("/pageListWhere")
    @ResponseBody
    public Map pageListWhere(int page, int limit,String stuName, String phone, Integer clazz, Integer huor){
        Map map = new HashMap();
        Integer count = studentService.countStuWhere(stuName,phone,clazz,huor);
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",studentService.pageListWhere(page,limit,stuName,phone,clazz,huor));
        return map;
    }

    @RequestMapping("/delStu")
    @ResponseBody
    public String delStu(String idstr){
        studentService.delStu(idstr);
        return "success";
    }

    @RequestMapping("/addStu")
    @ResponseBody
    public String addStu(StudentVO studentVO){
        studentVO.setStat(1);
        studentService.addStu(studentVO);
        return "success";
    }

    @RequestMapping("/updStu")
    @ResponseBody
    public String editStu(StudentVO studentVO){
        StudentVO db = studentService.findById(studentVO.getStudId());
        db.setStuName(studentVO.getStuName());
        db.setCardId(studentVO.getCardId());
        db.setGuarantee(studentVO.getGuarantee());
        db.setMiddleSchool(studentVO.getMiddleSchool());
        db.setSex(studentVO.getSex());
        db.setAge(studentVO.getAge());
        db.setBirthDay(studentVO.getBirthDay());
        db.setPhone(studentVO.getPhone());
        db.setAddr(studentVO.getAddr());
        db.setEnterTime(studentVO.getEnterTime());
        db.setIntrodureTech(studentVO.getIntrodureTech());
        db.setResiDence(studentVO.getResiDence());
        db.setNaTion(studentVO.getNaTion());
        db.setNaTives(studentVO.getNaTives());
        db.setProLevel(studentVO.getProLevel());
        db.setStudyType(studentVO.getStudyType());
        db.setParents(studentVO.getParents());
        db.setParentsPhone(studentVO.getParentsPhone());
        db.setIntrPhone(studentVO.getIntrPhone());
        db.setAudiTion(studentVO.getAudiTion());
        db.setComputer(studentVO.getComputer());
        db.setCollar(studentVO.getCollar());
        db.setIsVocational(studentVO.getIsVocational());
        db.setVocationalsch(studentVO.getVocationalsch());
        db.setVocationalFlag(studentVO.getVocationalFlag());
        db.setEnrollno(studentVO.getEnrollno());
        db.setAuditionOption(studentVO.getAuditionOption());
        db.setStuno(studentVO.getStuno());
        db.setRegistration(studentVO.getRegistration());
        db.setDibao(studentVO.getDibao());
        db.setSourceType(studentVO.getSourceType());
        db.setRemark(studentVO.getRemark());
        studentService.updStu(db);
        return "success";
    }

    //调整班级
    @RequestMapping("/updClass")
    @ResponseBody
    public String updClass(Integer stuId,Integer classId){
        StudentVO studentVO = studentService.findById(stuId);
        //如果班级改变
        if (studentVO.getClazz() != classId) {
            //原班级人数-1
            otherService.updClassCount(studentVO.getClazz(),-1);
            //班级人数+1
            otherService.updClassCount(classId,1);

            otherService.updStudentClass(stuId,classId);
        }
        return "success";
    }

    //调整宿舍
    @RequestMapping("/updHuor")
    @ResponseBody
    public String updHuor(Integer stuId,Integer huorId){
        StudentVO studentVO = studentService.findById(stuId);
        //如果宿舍改变
        if (studentVO.getHuor() != huorId) {
            //原宿舍人数-1
            otherService.updHuorCount(studentVO.getHuor(),-1);
            //宿舍人数+1
            otherService.updHuorCount(huorId,1);

            otherService.updStudentHour(stuId,huorId);
        }
        return "success";
    }

    //调整宿舍
    @RequestMapping("/leaHuor")
    @ResponseBody
    public String leaHuor(Integer stuId){
        StudentVO studentVO = studentService.findById(stuId);
            //原宿舍人数-1
            otherService.updHuorCount(studentVO.getHuor(),-1);

            otherService.updStudentHour(stuId,0);
        return "success";
    }

    //查询学生是否有答辩成绩
    @RequestMapping("/selReply")
    @ResponseBody
    public Map selReply(Integer stuId){
        Map map = new HashMap();
        // 查询该学生所有的答辩项目
        List<ProjectVO> projectList = studentReplyScoreService.findGradedProjectByStudentId(stuId);
        if (projectList.size() == 0) {
            map.put("code",0);
        } else {
            map.put("code",1);
        }
        return map;
    }

    //查询学生信息
    @RequestMapping("/selStu")
    @ResponseBody
    public StudentVO selStu(Integer stuId){
        StudentVO studentVO = studentService.findById(stuId);
        return studentVO;
    }

    @RequestMapping("/repass")
    @ResponseBody
    public String repass(Integer stuId,String password){
        studentService.repass(stuId,password);
        return "success";
    }

    @RequestMapping("/updStatus")
    @ResponseBody
    public String updStatus(Integer stuId,Integer stat){
        StudentVO studentVO = studentService.findById(stuId);
        if (stat == 0) {//如果是退学
            //原宿舍人数-1
            otherService.updHuorCount(studentVO.getHuor(),-1);
            otherService.updStudentHour(stuId,0);

            //原班级人数-1
            otherService.updClassCount(studentVO.getClazz(),-1);
            otherService.updStudentClass(stuId,0);
        }

        studentService.updStuStatus(stuId,stat);
        return "success";
    }
}
