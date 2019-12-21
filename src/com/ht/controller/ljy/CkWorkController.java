package com.ht.controller.ljy;

import com.ht.service.ljy.checkworkService;
import com.ht.vo.ChatRecordVO;
import com.ht.vo.CheckWorkVO;
import com.ht.vo.EmpVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JY on 2019/12/11.
 */

@Controller
@RequestMapping("/checkwork")
public class CkWorkController {

    @Resource
    private checkworkService checkworkService;


    @RequestMapping("/gotocheckworklist")
    public String gotocheckworklist(){
        //System.out.println("进去查询页面！");
        return "ljy/checkwork_list";
    }


    @RequestMapping("/gotocheckworkadd")
    public String gotocheckworkadd(){
        //System.out.println("进去添加页面！");
        return "ljy/checkwork_add";
    }


    @RequestMapping("/gotomycheck")
    public String gotomycheck(){
        //System.out.println("进去添加页面！");
        return "ljy/checkwork_mycheck";
    }

//    我的审批
    @ResponseBody
    @RequestMapping("/mychecklist")
    public Map mychecklist(int page,int limit,HttpSession session){
        EmpVO empVO= (EmpVO) session.getAttribute("emp");
        Map map=new HashMap();
        map.put("code",0);
        map.put("msg",0);
        map.put("count",checkworkService.checkwork_mycheckcount(empVO.getEmpId()));
        map.put("data",checkworkService.checkwork_mychecklist(page,limit,empVO.getEmpId()));
        return map;
    }

    @ResponseBody
    @RequestMapping("/checkworkadd")
    public String checkworkadd(CheckWorkVO checkWorkVO,HttpSession session){
       EmpVO empVO= (EmpVO) session.getAttribute("emp");
//       根据session中的id查询到领导的id
      Map map= (Map) checkworkService.checkwork_chairmanId(empVO.getEmpId()).get(0);
      checkWorkVO.setDeptHeadId((Integer) map.get("empId"));
//      设置员工id
      checkWorkVO.setEmpId(empVO.getEmpId());
      checkWorkVO.setStatus(0);
        checkworkService.checkwork_add(checkWorkVO);
        //System.out.println("添加未打卡说明！");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/checkworklist")
    public Map checkworklist(int page,int limit,HttpSession session){
        EmpVO empVO= (EmpVO) session.getAttribute("emp");
        Map map=new HashMap<>();
        map.put("code",0);
        map.put("msg",0);
        map.put("count",checkworkService.checkwork_count(empVO.getEmpId()));
        map.put("data",checkworkService.checkwork_list(page,limit,empVO.getEmpId()));
        return map;
    }


    @ResponseBody
    @RequestMapping("/updStatus")
    public String updatestatus(int checkworkId,int status,String remark){
       String date= DateFormat.getDateInstance().format(new Date());
        //System.out.println("修改状态成功！");
        checkworkService.updatestatus(checkworkId,status,date,remark);
        return "success";
    }


    @ResponseBody
    @RequestMapping("/checkwork_update")
    public String checkwork_update(int checkworkId,String remark){
        //System.out.println("修改未打卡说明！！！");
        checkworkService.checkwork_update(checkworkId,remark);
        return "success";
    }

}
