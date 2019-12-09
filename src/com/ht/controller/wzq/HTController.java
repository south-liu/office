package com.ht.controller.wzq;

import com.alibaba.fastjson.JSONObject;
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


}
