package com.ht.controller.wzq;

import com.alibaba.fastjson.JSONArray;
import com.ht.service.llb.IStudentService;
import com.ht.service.wzq.HTService;
import com.ht.vo.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;


import net.sf.ehcache.transaction.xa.commands.StorePutCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by shkstart on 2019/12/4.
 */
@Controller
@RequestMapping("/MY")
public class HTController {

    @Resource
    HTService hts;

    @Resource
    IStudentService iss;

    //------------------------------学期管理-----------------------------------------

    @RequestMapping("/toterm_list")
    public String toterm_list(){
        return "wzq/term_list";
    }
    //分页查询学期表
    @RequestMapping("/termlist")
    @ResponseBody
    public Map termlist(int page, int limit){
        List list = hts.selcemester(page, limit);
        Integer counts = hts.selcount();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", counts);
        map.put("data", list);
        return map;
    }
    //添加学期
    @RequestMapping("/addterm")
    public String addterm(TermVO term){
        hts.addterms(term);
        return "wzq/term_list";
    }
    //删除学期
    @RequestMapping("/updterm")
    public String updterm(Integer termId){
        hts.updterm(termId);
        return "wzq/term_list";
    }
    //修改学期信息
    @RequestMapping("/delterm")
    public String delterm(TermVO term){
        hts.delterm(term);
        return "wzq/term_list";
    }


    //--------------------------楼栋管理-----------------------------------

    @RequestMapping("/tofloor_list")
    public String tofloor_list(){
        return "wzq/floor_list";
    }
    //分页查询楼栋表
    @RequestMapping("/floorlist")
    @ResponseBody
    public Map floorlist(int page, int limit){
        List list = hts.selfloor(page, limit);
        Integer counts = hts.selcountfloor();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", counts);
        map.put("data", list);
        return map;
    }
    //添加楼栋
    @RequestMapping("/addfloor")
    public String addfloor(StudentFloorVO studentFloor){
        hts.addfloor(studentFloor);
        return "wzq/floor_list";
    }
    //删除楼栋
    @RequestMapping("/updfloor")
    public String updfloor(Integer floorId){
        hts.updfloor(floorId);
        return "wzq/floor_list";
    }
    //修改楼栋
    @RequestMapping("/delfloor")
    public String delfloor(StudentFloorVO studentFloor){
        hts.delfloor(studentFloor);
        return "wzq/floor_list";
    }




    //----------------------------我的周报--------------------------------
    @RequestMapping("/tomyweekly_list")
    public String tomyweekly_list(){
        return "wzq/myweekly_list";
    }
    @RequestMapping("/myweeklylist")
    @ResponseBody
    public Map myweeklylist(Integer empId, int page, int limit){
        List list = hts.selmyweekly(empId, page, limit);
        Integer count = hts.selcountmyweekly(empId);
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //去添加周报页面
    @RequestMapping("/toaddmyweekly")
    public String toaddmyweekly(){
        return "wzq/myweekly_add";
    }
    //添加我的周报
    @RequestMapping("/addmyweekly")
    public String addmyweekly(WeeklyVO weekly){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(new Date());
        weekly.setWorkDay(date);
        hts.addwee(weekly);
        return "wzq/myweekly_list";
    }
    //删除我的周报
    @RequestMapping("/delmyweekly")
    public String delmyweekly(Integer weeklyId){
        hts.delweekly(weeklyId);
        return "wzq/myweekly_list";
    }
    //去修改周报
    @RequestMapping("/toupdmyweekly")
    public String toupdmyweekly(Integer weeklyId, Model model){
        WeeklyVO weekly = hts.selmyupd(weeklyId);
        model.addAttribute("weekly", weekly);
        return "wzq/myweekly_upd";
    }
    //修改我的周报(点击编辑按钮修改)
    @RequestMapping("/updmyweekly")
    public String updmyweekly(WeeklyVO weekly){
        /*SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(new Date());
        weekly.setWorkDay(date);*/
        hts.updmyweekly(weekly);
        return "wzq/myweekly_list";
    }
    //按搜索框条件查询
    @RequestMapping("/searchmyweeklylist")
    @ResponseBody
    public Map searchmyweeklylist(int page, int limit, Integer empId, String date, String date1){
        String sql = "select w.*, e.empName from weekly w left join emp e on w.empId = e.empId where w.empId =" + empId;
        String sql1 = "select count(*) from weekly  where empId =" + empId;
        if (!"".equals(date) && date != null){
            sql += " and w.workDay >= '"+ date +"'";
            sql1 += " and workDay >= '"+ date +"'";
        }
        if (!"".equals(date1) && date1 != null){
            sql += " and w.workDay <= '"+ date1 +"'";
            sql1 += " and workDay <= '"+ date1 +"'";
        }
        List list = hts.searchaduitlog(sql, page, limit);
        Integer count = hts.selcountad(sql1);
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }



    //--------------------------周报管理-----------------------------------

    @RequestMapping("/toweekly_list")
    public String toweekly_list(Model model){
        List<DeptVO> list = hts.seldept();
        model.addAttribute("list", list);
        return "wzq/weekly_list";
    }
    //查询周报表
    @RequestMapping("weelist")
    @ResponseBody
    public Map weelist(int page, int limit){
        //分页查询周报表
        List list = hts.selwee(page, limit);
        Integer count = hts.selcountwee();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //条件搜索
    @RequestMapping("/weelist2")
    @ResponseBody
    public Map weelist2(int page, int limit, String empName, String deptName, String date, String date1){
        String sql = "select w.*, e.empName from weekly w left join emp e on w.empId = e.empId where 1=1";
        String sql1 = "select count(*) from weekly  where 1=1";
        if (!"".equals(empName) && empName != null){
            sql += " and w.empId in (select empId from emp where empName like '%"+ empName +"%')";
            sql1 += " and empId in (select empId from emp where empName like '%"+ empName +"%')";
        }
        if (!"".equals(deptName) && deptName != null){
            sql += " and w.empId in (select empId from emp where  deptId in (select deptId from dept where deptName = '"+ deptName +"'))";
            sql1 += " and empId in (select empId from emp where  deptId in (select deptId from dept where deptName = '"+ deptName +"'))";
        }
        if (!"".equals(date) && date != null){
            sql += " and w.workDay >= '"+ date +"'";
            sql1 += " and workDay >= '"+ date +"'";
        }
        if (!"".equals(date1) && date1 != null){
            sql += " and w.workDay <= '"+ date1 +"'";
            sql1 += " and workDay <= '"+ date1 +"'";
        }
        List list = hts.searchaduitlog(sql, page, limit);
        Integer count = hts.selcountad(sql1);
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //删除周报
    @RequestMapping("/delweekly")
    public String delweekly(Integer weeklyId){
        hts.delweekly(weeklyId);
        return "wzq/weekly_list";
    }
    //查看周报
    @RequestMapping("/toweekly_list_table")
    public String toweekly_list_table(Model model, Integer weeklyId, Integer empId){
        WeeklyVO weekly = hts.selmyupd(weeklyId);
        model.addAttribute("weekly", weekly);
        Map dept = hts.selweeklydept(empId);
        model.addAttribute("depts", dept);
        return "wzq/weekly_table";
    }


    //------------------查看考试成绩---------------------

    @RequestMapping("/toscore_list")
    public String toscore_list(Model model){
        List<TermVO> list = hts.selxueqi();
        model.addAttribute("term", list);
        List<StudentClassVO> list1 = hts.selclass();
        model.addAttribute("sclass", list1);
        List<CourseVO> list2 = hts.selcourse();
        model.addAttribute("course", list2);
        return "wzq/score_list";
    }
    //分页查询
    @RequestMapping("/scorelist")
    @ResponseBody
    public Map score_list(int page, int limit){
        List list = hts.selscore(page, limit);
        Integer count = hts.selcountscore();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //搜索条件查询
    @RequestMapping("/selscorelist")
    @ResponseBody
    public Map selscorelist(String term, String sclass, Integer leibie, String course, int page, int limit){
        String sql = "select s.*, t.termName, c.courseName from studentScore s left join term t on s.termId = t.termId left join course c on s.courseId = c.courseId where 1 = 1";
        String sql1 = "select count(*) from studentScore where 1 = 1";
        if(term != null && !"".equals(term)){
            sql += " and s.termId in (select termId from term where termName = '" + term +"')";
            sql1 += " and termId in (select termId from term where termName = '" + term +"')";
        }
        if(sclass != null && !"".equals(sclass)){
            sql += " and s.stuId in (select stuId from student where clazz in (select classId from studentClass where className = '" + sclass +"'))";
            sql1 += " and stuId in (select stuId from student where clazz in (select classId from studentClass where className = '" + sclass +"'))";
        }
        if(leibie != null && !"".equals(leibie)){
            sql += " and s.testType = " + leibie;
            sql1 += " and testType = " + leibie;
        }
        if(course != null && !"".equals(course)){
            sql += " and s.courseId in (select courseId from course where courseName = '" + course +"')";
            sql1 += " and courseId in (select courseId from course where courseName = '" + course +"')";
        }
        List list = hts.searchaduitlog(sql, page, limit);
        Integer count = hts.selcountad(sql1);
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }


    //---------------课程类别-------------------

    @RequestMapping("/totype_list")
    public String tocoursetype_list(){
        return "wzq/coursetype_list";
    }
    //查询课程类别
    @RequestMapping("/coursetypelist")
    @ResponseBody
    public Map coursetypelist(int page, int limit){
        List list = hts.selcoursetype(page, limit);
        Integer count = hts.selcountcoursetype();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //添加课程类别
    @RequestMapping("/addtype")
    public String addtype(CourseTypeVO courseType){
        hts.addtype(courseType);
        return "wzq/coursetype_list";
    }
    //删除课程类别
    @RequestMapping("/deltype")
    public String deltype(Integer courseTypeId){
        hts.deltype(courseTypeId);
        return "wzq/coursetype_list";
    }
    //修改课程类别
    @RequestMapping("/updtype")
    public String updtype(CourseTypeVO courseType){
        hts.updtype(courseType);
        return "wzq/coursetype_list";
    }


    //-----------课程管理---------------

    @RequestMapping("/tocourse_list")
    public String tocourse(){
        return "wzq/course_list";
    }
    //查询课程
    @RequestMapping("/courselist")
    @ResponseBody
    public Map courselist(int page, int limit){
        List list = hts.selcourse(page, limit);
        Integer count = hts.selcountcourse();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //去添加页面（直接在页面上开一个小窗口）
    @RequestMapping("/toaddcourse")
    public String toaddcourse(Model model){
        List<CourseTypeVO> list = hts.selscourseType();
        model.addAttribute("course", list);
        return "wzq/course_add";
    }
    //添加课程
    @RequestMapping("/addcourse")
    @ResponseBody
    public String addcourse(CourseVO course){
        hts.addcourse(course);
        return "success";
    }
    //删除课程
    @RequestMapping("/delcourse")
    public String delcourse(Integer courseId){
        hts.delcourse(courseId);
        return "wzq/course_list";
    }
    //修改课程(页面数据格直接修改)
    @RequestMapping("/updcourse")
    public String updcourse(CourseVO course){
        hts.updcourse(course);
        return "wzq/course_list";
    }
    //去修改课程（点击编辑按钮修改）
    @RequestMapping("/toupdcourse")
    public String toupdcourse(Model model, Integer courseId){
        List<CourseTypeVO> list = hts.selscourseType();
        model.addAttribute("list", list);
        CourseVO course = hts.selcourseid(courseId);
        model.addAttribute("course", course);
        return "wzq/course_upd";
    }
    //修改课程(点击编辑按钮修改)
    @RequestMapping("/updcourse2")
    @ResponseBody
    public String updcourse2(CourseVO course){
        hts.updcourse(course);
        return "success";
    }


    //----------操作指定学生学生成绩-----------------------

    @RequestMapping("/tostudentscore_list")
    public String toscore_list(Model model, Integer studId){
        StudentVO student = iss.findById(studId);
        model.addAttribute("student", student);
        return "wzq/studentscore_list";
    }
    //查询学生成绩
    @RequestMapping("/studentscorelist")
    @ResponseBody
    public Map studentscorelist(int page, int limit, Integer stuId){
        System.out.println("student的值：" + stuId);
        List list = hts.selstudentscore(page, limit, stuId);
        Integer count = hts.selcountstudentscore(stuId);
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //去添加学生成绩
    @RequestMapping("/toaddstudentscore")
    public String toaddstudentscore(Model model, Integer stuId){
        List<CourseVO> list = hts.selcourse();
        model.addAttribute("course", list);
        List<TermVO>  list1 = hts.selxueqi();
        model.addAttribute("xueqi", list1);
        model.addAttribute("stuId", stuId);
        return "wzq/studentscore_add";
    }
    //添加学生成绩
    @RequestMapping("/addstudentscore")
    @ResponseBody
    public String addstudentscore(StudentScoreVO studentScore){
        hts.addstudentscore(studentScore);
        return "success";
    }
    //去修改学生成绩
    @RequestMapping("/toupdstudentscoure")
    public String toupdstudentscoure(Integer scoreId, Model model){
        StudentScoreVO score = hts.selstudentscore(scoreId);
        model.addAttribute("score", score);
        List<CourseVO> list = hts.selcourse();
        model.addAttribute("course", list);
        List<TermVO>  list1 = hts.selxueqi();
        model.addAttribute("xueqi", list1);
        return "wzq/studentscore_upd";
    }
    //修改学生成绩(点击编辑按钮修改)
    @RequestMapping("/updstudentscore")
    @ResponseBody
    public String updstudentscore(StudentScoreVO studentScore){
        hts.updstudentscore(studentScore);
        return "success";
    }
    //修改学生成绩(点击页面数据网格直接修改)
    @RequestMapping("/updstudentscore2")
    public String updstudentscore2(StudentScoreVO studentScore){
        hts.updstudentscore(studentScore);
        return "wzq/studentscore_list";
    }
    //删除学生成绩
    @RequestMapping("/delstudentscore")
    public String delstudentscore(Integer scoreId){
        hts.delstudentscore(scoreId);
        return "wzq/studentscore_list";
    }


    //--------------考核指标--------------
    @RequestMapping("/toaduitmodel")
    public String toaduitmodel(){
        return "wzq/aduitmodel_list";
    }
    //查询考核指标
    @RequestMapping("/aduitmodel_list")
    @ResponseBody
    public Map aduitmodel_list(int page, int limit){
        List list = hts.seladuitmodel(page, limit);
        Integer count = hts.selcountaduitmodel();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //去添加考核指标
    @RequestMapping("/toaddaduitmodel")
    public String toaddaduitmodel(Model model){
        List<DeptVO> list = hts.seldept();
        model.addAttribute("dept", list);
        return "wzq/aduitmodel_add";
    }
    //添加考核指标
    @RequestMapping("/addaduitmodel")
    @ResponseBody
    public String addaduitmodel(AduitModelVO aduitModel){
        hts.addaduitmodel(aduitModel);
        return "success";
    }
    //删除考核指标
    @RequestMapping("/deladuitmodel")
    public String delauitmodel(Integer aduitModelId){
        hts.deladuitmodel(aduitModelId);
        return "wzq/aduitmodel_list";
    }


    //---------------员工考核----------------------

    @RequestMapping("/toaduitlog")
    public String toaduitlog(Model model){
        List<DeptVO> list = hts.seldept();
        model.addAttribute("dept", list);
        return "wzq/aduitlog_list";
    }
    //查询员工考核
    @RequestMapping("/aduitloglist")
    @ResponseBody
    public Map aduitloglist(int page, int limit){
        List list = hts.seladuitlog(page, limit);
        Integer count = hts.selcountaduitlog();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //去添加员工考核
    @RequestMapping("/toaddaduitlog")
    public String toaddauitlog(Model model){
        List<AduitModelVO> list = hts.seladuitm();
        model.addAttribute("aduitm", list);
        List<EmpVO> list1 = hts.selemp();
        model.addAttribute("emps", list1);
        return "wzq/aduitlog_add";
    }
    //添加员工考核时先上传图片
    @RequestMapping("/addimgs")
    @ResponseBody
    public Map addimgs(MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("file的值：" + file);
        //全局唯一标识：用于设置上传文件的新名称
        String xinName = UUID.randomUUID().toString();

        //获取上传时的文件名
        String oneName = file.getOriginalFilename();

        //截取后缀名
        String suffixName = oneName.substring(oneName.lastIndexOf("."));

        //获取项目路径
        String path = request.getSession().getServletContext().getRealPath("");

        //使用实时时间生成文件夹
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String strDate = sdf.format(new Date());

        //上传文件存放在项目下的路径
        String dirName = "\\WEB-INF\\static\\upload\\" + strDate + "\\";

        //总路径
        File dirFile = new File(path + dirName);
        if (!dirFile.exists()){  //dirFile不存在时

            //创建多级目录
            dirFile.mkdirs();
        }

        //拼接成新文件名 = 全局唯一标识 + 截取的后缀
        String newName = xinName + suffixName;

        //保存文件
        File newFile = new File(path + dirName + newName);
        file.transferTo(newFile);

        //赋值给数据库对应的列
        String a = "\\upload\\" + strDate + "\\" + newName;

        Map res = new HashMap();
        res.put("date", a);

        return res;
    }
    //添加员工考核
    @RequestMapping("/addaduitlog")
    @ResponseBody
    public String addaduitlog(AduitLogVO aduitLog){
        hts.addaduitlog(aduitLog);
        return "success";
    }
    //删除员工考核
    @RequestMapping("/deladuitlog")
    public String deladuitlog(Integer aduitLogId){
        hts.deladuitlog(aduitLogId);
        return "wzq/aduitlog_list";
    }
    //搜索员工考核
    @RequestMapping("/searchaduitlog")
    @ResponseBody
    public Map searchaduitlog(String empName, String deptName, String date, String date1, int page, int limit){
        String sql = "select a.*, d.aduitName, e.empName from aduitLog a left join aduitModel d on a.aduitModelId = d.aduitModelId left join emp e on a.empId = e.empId where 1=1";
        String sql1 = "select count(*) from aduitLog  where 1=1";
        if (!"".equals(empName) && empName != null){
            sql += " and a.empId in (select empId from emp where empName like '%"+ empName +"%')";
            sql1 += " and empId in (select empId from emp where empName like '%"+ empName +"%')";
        }
        if (!"".equals(deptName) && deptName != null){
            sql += " and a.empId in (select empId from emp where  deptId in (select deptId from dept where deptName = '"+ deptName +"'))";
            sql1 += " and empId in (select empId from emp where  deptId in (select deptId from dept where deptName = '"+ deptName +"'))";
        }
        if (!"".equals(date) && date != null){
            sql += " and a.auditDate >= '"+ date +"'";
            sql1 += " and auditDate >= '"+ date +"'";
        }
        if (!"".equals(date1) && date1 != null){
            sql += " and a.auditDate <= '"+ date1 +"'";
            sql1 += " and auditDate <= '"+ date1 +"'";
        }
        List list = hts.searchaduitlog(sql, page, limit);
        Integer count = hts.selcountad(sql1);
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //查看图片
    @RequestMapping("/toaduitlog_list_id")
    public String toaduitlog_list_id(Integer aduitLogId, Integer aduitModelId, Integer empId, Model model){
        AduitLogVO aduitLog = hts.seladuitlog(aduitLogId);
        model.addAttribute("aduitLog", aduitLog);
        AduitModelVO aduitModel = hts.seladuitModel(aduitModelId);
        model.addAttribute("aduitModel", aduitModel);
        EmpVO emp = hts.selemp(empId);
        model.addAttribute("emp", emp);
        return "wzq/aduitlog_list_id";
    }
}
