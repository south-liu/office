package com.ht.controller.cc;

import com.ht.service.cc.RepairService;
import com.ht.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/repair")
public class RepairsController {
    @Resource
    private RepairService rs;
    @RequestMapping("/tolist")
    public  String tolist(){
        return "cc/repair/repair_list";
    }
    @RequestMapping("/stutolist")
    public  String stutolist(){
        return "cc/repair/sturepair_list";
    }

    @RequestMapping("/toempadd")
   public String toaddlist(){
        return "cc/repair/emprepair_add";
    }

    @RequestMapping("/tostuadd")
    public String toaddstulist(){
        return "cc/repair/sturepair_add";
    }

    @ResponseBody
    @RequestMapping("/repair_list")
    public Map collegelist(int page, int limit,HttpSession session){
//        List list = cs.selSpage(page,rows);//每页数据
        Integer totalRow = rs.seltotalRepairemp();//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",rs.empselSpage(page,limit));
        System.out.println("total "+totalRow);
        return map;
    }


    @ResponseBody
    @RequestMapping("/sturepair_list")
    public Map sturepairlist(int page, int limit,HttpSession session){
//        List list = cs.selSpage(page,rows);//每页数据
        StudentVO student =  (StudentVO) session.getAttribute("student");
        String stuId = student.getStudId().toString();
        Integer totalRow = rs.seltotalRepair(stuId);//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",rs.selSpage(page,limit,stuId));
        System.out.println("total "+totalRow);
        return map;
    }
    @RequestMapping("/deletelist")
    @ResponseBody
    public  String del(String equipmentId){
        System.out.println("这是删除方法");
        rs.delRepair(equipmentId);
        return "success";
    }

    @RequestMapping("/updlist")
    @ResponseBody
    public String upd(int  equipmentId,int status){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rs.UpdRepair(equipmentId,status,sim.format(new Date()));
        return "success";
    }

    //员工提交申请
    @RequestMapping("/emprepairadd")
    @ResponseBody
    public String add(EquipmentRepairVO equipmentRepairVO, HttpSession session){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        EmpVO emp =  (EmpVO)session.getAttribute("emp");
        equipmentRepairVO.setEmpId(emp.getEmpId());
        equipmentRepairVO.setStartTime(sim.format(new Date()));
        equipmentRepairVO.setUserType(2);//设置申请人的类型  员工为 2
        equipmentRepairVO.setStatus(0);//设置设备的默认状态  0 未完成||1 已完成
        rs.AddRepair(equipmentRepairVO);
        return "";
    }

    //学生提交申请
    @RequestMapping("/sturepairadd")
    @ResponseBody
    public String stuadd(EquipmentRepairVO equipmentRepairVO, HttpSession session){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StudentVO student =  (StudentVO) session.getAttribute("student");
        equipmentRepairVO.setStudent(student.getStudId());
        equipmentRepairVO.setStartTime(sim.format(new Date()));
        equipmentRepairVO.setUserType(1);//设置申请人的类型  员工为 2
        equipmentRepairVO.setStatus(0);//设置设备的默认状态  0 未完成||1 已完成
        rs.AddRepair(equipmentRepairVO);
        return "";
    }
}
