package com.ht.controller.llb;

import com.alibaba.fastjson.JSONArray;
import com.ht.service.llb.IDeptService;
import com.ht.service.llb.IEmpService;
import com.ht.vo.DeptVO;
import com.ht.vo.EmpVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private IDeptService deptService;
    @Resource
    private IEmpService empService;

    @RequestMapping("/toDeptList")
    public String toDeptList(){
        return "llb/dept/dept_list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        List<DeptVO> list = deptService.allHeadDept();
        List<EmpVO> allEmp = empService.allEmp();
        model.addAttribute("emps",allEmp);
        model.addAttribute("headDept",list);
        return "llb/dept/dept_add";
    }

    @RequestMapping("/toUpd")
    public String toUpd(Model model,Integer deptId){
        DeptVO d =  deptService.selById(deptId);
        List<DeptVO> headDept = deptService.allHeadDept();
        List<EmpVO> allEmp = empService.allEmp();
        model.addAttribute("headDept",headDept);
        model.addAttribute("dept",d);
        model.addAttribute("emps",allEmp);
        return "llb/dept/dept_upd";
    }

    @RequestMapping("/allDept")
    @ResponseBody
    public JSONArray allDept(){
        List<DeptVO> allDept = deptService.allDept();
        JSONArray headDeptArr = new JSONArray();
        List<DeptVO> headDepts = new ArrayList<>();
        for (DeptVO deptVO : allDept) {
            if (deptVO.getParentId()==0||deptVO.getParentId()==null) {
                headDepts.add(deptVO);
            }
        }

        for (DeptVO deptVO : headDepts) {
            Map headMap = new HashMap();
            headMap.put("title",deptVO.getDeptName());
            headMap.put("id",deptVO.getDeptId());
            JSONArray childrenDetpArr = new JSONArray();
            for (DeptVO childDept : allDept) {
                if (childDept.getParentId() == deptVO.getDeptId()) {
                    Map childrenMap = new HashMap();
                    childrenMap.put("title", childDept.getDeptName());
                    childrenMap.put("id", childDept.getDeptId());
                    childrenDetpArr.add(childrenMap);
                }
            }
            headMap.put("children",childrenDetpArr);
            headDeptArr.add(headMap);
        }
        return headDeptArr;
    }

    @RequestMapping("/addDept")
    @ResponseBody
    public String addDept(DeptVO deptVO){
        deptService.addDept(deptVO);
        return "success";
    }

    @RequestMapping("/delDept")
    @ResponseBody
    public String delDept(DeptVO deptVO){
        DeptVO d =  deptService.selById(deptVO.getDeptId());
        deptService.deleteDept(deptVO);
        if (d.getParentId()==0) {
            deptService.deleteChildDept(d.getDeptId());
        }
        return "success";
    }

    @RequestMapping("/updDept")
    @ResponseBody
    public String updDept(DeptVO deptVO){
        //DeptVO d =  deptService.selById(deptVO.getDeptId());
        //d.setDeptName(deptVO.getDeptName());
        deptService.updateDept(deptVO);
        return "success";
    }
}