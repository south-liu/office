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
    public Map collegelist(int page, int limit){
//        List list = cs.selSpage(page,rows);//每页数据
        Integer totalRow = rs.seltotalRepair();//总行数
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",totalRow);
        map.put("data",rs.selSpage(page,limit));
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
        String data = DateFormat.getDateInstance().format(new Date());
        rs.UpdRepair(equipmentId,status,data);
        return "success";
    }

    //员工提交申请
    @RequestMapping("/emprepairadd")
    @ResponseBody
    public String add(EquipmentRepairVO equipmentRepairVO, HttpSession session){
        EmpVO emp =  (EmpVO)session.getAttribute("emp");
        equipmentRepairVO.setEmpId(emp.getEmpId());
        equipmentRepairVO.setStartTime(DateFormat.getDateInstance().format(new Date()));
        equipmentRepairVO.setUserType(2);//设置申请人的类型  员工为 2
        equipmentRepairVO.setStatus(0);//设置设备的默认状态  0 未完成||1 已完成
        rs.AddRepair(equipmentRepairVO);
        return "";
    }
}
