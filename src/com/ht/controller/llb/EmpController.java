package com.ht.controller.llb;

import com.ht.service.llb.IDeptService;
import com.ht.service.llb.IEmpService;
import com.ht.vo.DeptVO;
import com.ht.vo.EmpVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Resource
    private IDeptService deptService;
    @Resource
    private IEmpService empService;

    @RequestMapping("/toEmpList")
    public String toDeptList(){
        return "llb/emp_list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        List<DeptVO> list = deptService.allDept();
        model.addAttribute("depts",list);
        return "llb/emp_add";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Integer empId,Model model){
        List<DeptVO> list = deptService.allDept();
        EmpVO empVO = empService.findEmpById(empId);
        model.addAttribute("depts",list);
        model.addAttribute("emp",empVO);
        return "llb/emp_edit";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map pageList(int page, int limit){
        Map map = new HashMap();
        Integer count = empService.countEmp();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",empService.pageList(page,limit));
        return map;
    }

    @RequestMapping("addEmp")
    @ResponseBody
    public String addEmp(EmpVO empVO,String P1,String C1,String A1){
        empVO.setAddress(P1+C1+A1);
        empService.addEmp(empVO);
        return "success";
    }

    @RequestMapping("delEmp")
    @ResponseBody
    public String delEmp(String idstr){
        empService.delEmp(idstr);
        return "success";
    }

    @RequestMapping("updEmp")
    @ResponseBody
    public String updEmp(EmpVO empVO,String P1,String C1,String A1){
        empVO.setAddress(P1+C1+A1);
        empService.updEmp(empVO);
        return "success";
    }
}
