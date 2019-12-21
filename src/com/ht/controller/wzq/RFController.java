package com.ht.controller.wzq;

import com.alibaba.fastjson.JSONArray;
import com.ht.service.wzq.HTService;
import com.ht.service.wzq.RFSerivce;
import com.alibaba.fastjson.JSONObject;
import com.ht.vo.AduitLogVO;
import com.ht.vo.DeptVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by shkstart on 2019/12/17.
 */
@Controller
@RequestMapping("/RF")
public class RFController {

    @Resource
    RFSerivce rfs;

    @Resource
    HTService hts;

    @RequestMapping("/tocheckwork_rf")
    public String tocheckwork_rf(){
        return "wzq/checkwork_rf";
    }
    //未打卡时间段月览
    @RequestMapping("/checkwork_list_rf")
    @ResponseBody
    public JSONObject checkwork_list_rf(HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");

        List zong = new ArrayList();

        //查询有多少个年份
        List list = rfs.selzong();

        //拼接成第一行（加一个名称再带年份）
        List list1 = new ArrayList();

        //有多少个年份就有多少个系列
        List xilie = new ArrayList();
        list1.add("product");
        for (int i = 0; i < list.size(); i++){
            list1.add(list.get(i));  //添加示例

            //单个系列用Map装，再用list装全部系列
            Map map = new HashMap();
            map.put("type", "bar");
            xilie.add(map);
        }

        //拼接sql语句(用于查询对应列的值)
        String sql = "select date_format (noCardTime,'%H:00') name,";
        for (int i = 0; i < list.size(); i++){
            sql += "sum(case when date_format(noCardTime,'%Y年%m月')='"+ list.get(i) +"' then date_format (noCardTime,'%H:00') else 0 end)/hour(noCardTime),";
        }

        //去掉末尾的逗号
        sql = sql.substring(0, sql.length() - 1);

        //加上最后的结尾
        sql += " from checkwork group by name";

        //查询对应时间迟到的次数
        List list2 = rfs.selcount(sql);

        //合成第一个数组（数据数组）
        zong.add(list1);

        for (int j = 0; j < list2.size(); j++){
            zong.add(list2.get(j));
        }

        JSONObject json = new JSONObject();
        json.put("zong", zong);
        json.put("xilie", xilie);
        return json;
    }
    //未打卡年度总览
    @RequestMapping("/checkwork_list_rf_zong")
    @ResponseBody
    public JSONObject checkwork_list_rf_zong(HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");

        List zong = new ArrayList();

        //查询有多少个年份
        List list = rfs.selzong2();

        //拼接成第一行（加一个名称再带年份）
        List list1 = new ArrayList();

        //有多少个年份就有多少个系列
        List xilie = new ArrayList();
        list1.add("product");


        for (int i = 0; i < list.size(); i++){
            list1.add(list.get(i));  //添加示例

            //单个系列用Map装，再用list装全部系列
            Map map = new HashMap();
            //添加示例
            map.put("type", "line");
            xilie.add(map);
        }

        //拼接sql语句(用于查询对应列的值)
        String sql = "select date_format(noCardTime,'%m月') name,";
        for (int i = 0; i < list.size(); i++){
            sql += "sum(case when date_format (noCardTime,'%Y年')='"+ list.get(i) +"' then date_format (noCardTime,'%m月') else 0 end)/month(noCardTime),";
        }

        //去掉末尾的逗号
        sql = sql.substring(0, sql.length() - 1);

        //加上最后的结尾
        sql += " from checkwork group by name";

        //查询对应时间迟到的次数
        List list2 = rfs.selcount(sql);

        //合成第一个数组（数据数组）
        zong.add(list1);

        for (int j = 0; j < list2.size(); j++){
            zong.add(list2.get(j));
        }

        JSONObject json = new JSONObject();
        json.put("zong", zong);
        System.out.println(JSONArray.toJSON(zong).toString());
        System.out.println(JSONArray.toJSON(xilie).toString());
        json.put("xilie", xilie);
        return json;
    }




    //-----------------员工考核报表------------------------------

    //去员工考核报表
    @RequestMapping("/toempassessment")
    public String toempassessment(Model model){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sf.format(new Date());
        String a = date.substring(0, date.length() - 6);
        String b = date.substring(date.length() - 5, date.length() - 3);
        date = a + "年" + b + "月";
        //给查询考核用
        model.addAttribute("date", date);
        List<Map> lsit2 = rfs.seladuitlog();
        model.addAttribute("aduitlog", lsit2);
        List<DeptVO> list = hts.seldept();
        model.addAttribute("emp", list);
        return "wzq/empassessment_list";
    }
    //返回考核报表
    @RequestMapping("/toempassessmentlist")
    public String toempassessmentlist(Model model, String date){
        model.addAttribute("date", date);
        List<Map> lsit2 = rfs.seladuitlog();
        model.addAttribute("aduitlog", lsit2);
        List<DeptVO> list = hts.seldept();
        model.addAttribute("emp", list);
        return "wzq/empassessment_list";
    }
    @RequestMapping("/empassessment_list")
    @ResponseBody
    public Map empassessment_list(String date, int page, int limit){
        List list = rfs.selempassessment(date, page, limit);
        Integer count = rfs.selcountempassessment();
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }
    //搜索框搜索
    @RequestMapping("/empassessment_list_sousuo")
    @ResponseBody
    public Map empassessment_list_sousuo(int page, int limit, String empName, String deptName, String date1){
        String sql = "";
        if (!"".equals(date1) && date1 != null){
            sql = "select e.*, d.deptName, (a.totalScores + score) zongscore from emp e left join dept d on e.deptId = d.deptId left join empAssessmentTotal a on e.empId = a.empId left join (select m.empId, sum(case when date_format(m.auditDate,'%Y年%m月')='" + date1 + "' then m.score else 0 end) as score from aduitLog m group by m.empId) m on a.empId = m.empId where 1 = 1";
        }else {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sf.format(new Date());
            String a = date.substring(0, date.length() - 6);
            String b = date.substring(date.length() - 5, date.length() - 3);
            date = a + "年" + b + "月";
            sql = "select e.*, d.deptName, (a.totalScores + score) zongscore from emp e left join dept d on e.deptId = d.deptId left join empAssessmentTotal a on e.empId = a.empId left join (select m.empId, sum(case when date_format(m.auditDate,'%Y年%m月')='" + date + "' then m.score else 0 end) as score from aduitLog m group by m.empId) m on a.empId = m.empId where 1 = 1";
        }
        String sql1 = "select count(*) from emp e left join dept d on e.deptId = d.deptId left join empAssessmentTotal a on e.empId = a.empId left join (select m.empId, sum(case when date_format(m.auditDate,'%Y%m')=(select date_format(m.auditDate,'%Y%m') from aduitLog group by date_format(m.auditDate,'%Y%m')) then m.score else 0 end) as score from aduitLog m group by m.empId) m on a.empId = m.empId where 1 = 1";
        if (!"".equals(empName) && empName != null){
            sql += " and e.empName like '%"+ empName +"%'";
            sql1 += " and e.empName like '%"+ empName +"%'";
        }
        if (!"".equals(deptName) && deptName != null){
            sql += " and d.deptName = '"+ deptName +"'";
            sql1 += " and d.deptName = '"+ deptName +"'";
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
    //去查询员工考核
    @RequestMapping("/toempassessment_aduitlog_list")
    public String toempassessment_aduitlog_list(Integer empId, String date, Model model){
        model.addAttribute("empId", empId);
        model.addAttribute("date", date);
        return "wzq/empassessment_aduitlog_list";
    }
    //查询员工考核
    @RequestMapping("/empassessment_aduitlog_list")
    @ResponseBody
    public Map empassessment_aduitlog_list(Integer empId, String date, int page, int limit){
        List list = rfs.selempassessmentaduitlog(empId, date, page, limit);
        Integer count = rfs.selcountempassessmentaduitlog(empId, date);
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", list);
        return map;
    }




}
