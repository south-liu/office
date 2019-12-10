package com.ht.controller.wzq;

import com.ht.service.wzq.HTService;
import com.ht.vo.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shkstart on 2019/12/4.
 */
@Controller
@RequestMapping("/MY")
public class HTController {

    @Resource
    HTService hts;

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
    //去添加周报页面
    @RequestMapping("/toadd")
    public String toadd(){
        return "wzq/weekly_add";
    }
    //添加周报
    @RequestMapping("/addwee")
    public String addwee(WeeklyVO weekly){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(new Date());
        weekly.setWorkDay(date);
        hts.addwee(weekly);
        return "wzq/weekly_list";
    }
    //根据条件查询
    @RequestMapping("/weelist2")
    @ResponseBody
    public Map weelist2(int page, int limit, String empName, String deptName, String date, String date1){
        Map map = new HashMap();
        if (empName != "" && deptName == "" && date == "" && date1 == ""){
            System.out.println("进入了if选择...");
            //查询所有empId为页面提交的员工
            List list = hts.seldeptwee(empName, page, limit);
            //查询总行数
            Integer count = hts.selcountemp(empName);
            map.put("code", 0);
            map.put("msg", "");
            map.put("count", count);
            map.put("data", list);
            System.out.println(map);
        }
        System.out.println(map);
        return map;

//        int time1 = Integer.parseInt("20191206")
//        int time2 = Integer.parseInt("20191207")
//        if (time1 < time2) {}
    }


    //------------------考试成绩---------------------

    @RequestMapping("/toscore_list")
    public String toscore_list(Model model){
        List<TermVO> list = hts.selxueqi();
        model.addAttribute("term", list);
        List<StudentClassVO> list1 = hts.selclass();
        model.addAttribute("sclass", list1);
        List<CourseVO> list2 = hts.selcourse();
        model.addAttribute("course", list2);
        return "wzq/studentscore_list";
    }
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




//    @Override
//    public JSONArray getWeekLogData(HttpServletRequest request, int page, int limit) {
//        JSONArray data = new JSONArray();
//        String empName = request.getParameter("empName");
//        String depIdStr = request.getParameter("depId");
//        int depId = 0;
//        if ("".equals(depIdStr) || null == depIdStr){
//            depId = 0;
//        }else {
//            depId = Integer.parseInt(depIdStr);
//        }
//        System.out.println(depId);
//        String startDay = request.getParameter("startDay");
//        String endDay = request.getParameter("endDay");
//        String hql = "FROM WeeklogVo where 1=1";
//        if (!("".equals(empName) || null == empName)){
//            int id = getEmpName(empName);
//            hql +=" and Empid ="+id;
//        }
//        if (depId!=0){
//            hql +=" and Empid in (SELECT empId FROM EmpVo where depId="+depId+")";
//        }
//        if (!("".equals(startDay) || null == startDay)){
//            hql +=" and Workday>='"+startDay+"'";
//        }
//        if (!("".equals(endDay) || null == endDay)){
//            hql +=" and Workday<='"+endDay+"'";
//        }
//        System.out.println(hql);
//        List<WeeklogVo> list = pageByHql(hql,page,limit);
//        for (WeeklogVo vo:list) {
//            //查询员工姓名
//            EmpVo emp = (EmpVo) getObject(EmpVo.class,vo.getEmpid());
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//            JSONObject wlJO = new JSONObject();
//            wlJO.put("worklogid",vo.getWeeklogid());
//            wlJO.put("empName",emp.getEmpName());
//            wlJO.put("weekDay",sdf.format(vo.getWorkday()));
//            wlJO.put("weekCur",vo.getWeekCur());
//            wlJO.put("studentQuestion",vo.getStudentQuestion());
//            wlJO.put("idea",vo.getIdea());
//            wlJO.put("weekNext",vo.getWeekNext());
//            System.out.println(wlJO);
//            data.add(wlJO);
//        }
//        return data;
//    }
//
//    @Override
//    public int getWeekLogSize(HttpServletRequest request) {
//        String empName = request.getParameter("empName");
//        String depIdStr = request.getParameter("depId");
//        int depId = 0;
//        if ("".equals(depIdStr) || null == depIdStr){
//            depId = 0;
//        }else {
//            depId = Integer.parseInt(depIdStr);
//        }
//        System.out.println(depId);
//        String startDay = request.getParameter("startDay");
//        String endDay = request.getParameter("endDay");
//        String hql = "select count(*) from WeeklogVo where 1=1";
//        if (!("".equals(empName) || null == empName)){
//            int id = getEmpName(empName);
//            hql +=" and Empid ="+id;
//        }
//        if (depId!=0){
//            hql +=" and Empid in (SELECT empId FROM EmpVo where depId="+depId+")";
//        }
//        if (!("".equals(startDay) || null == startDay)){
//            hql +=" and Workday>='"+startDay+"'";
//        }
//        if (!("".equals(endDay) || null == endDay)){
//            hql +=" and Workday<='"+endDay+"'";
//        }
//        System.out.println(hql);
//        return getCountByHql(hql);
//    }
}
