package com.ht.controller.cc;

import com.ht.service.cc.HoliEmpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/empholi")
public class EmpHoliController {
    @Resource
    private HoliEmpService hsemp;

    @RequestMapping("/tolist")
    public  String tolist(){
        return "cc/empholiday/empholi_list";
    }

    @RequestMapping("/toptcls")
    public  String toptcls(Model model,Integer empId,Integer sum,int mon){
        String month = null;
        Calendar cal = Calendar.getInstance();
        int mo = cal.get(Calendar.MONTH) + 1;
        if (mon==1){
            month = ""+mo;
        }else if (mon==2){
            month="all";
        }else if (mon==3){
            month = ""+(mo-1);
        }
        model.addAttribute("month",month);
        model.addAttribute("sum",sum);
        model.addAttribute("empId",empId);
        return "cc/empholiday/empholi_ptcls";
    }

    @ResponseBody
    @RequestMapping("/holi_list")
    public Map holilist(int page, int limit, Model model){
        Calendar cal = Calendar.getInstance();
        int mo = cal.get(Calendar.MONTH) + 1;
//        List list = cs.selSpage(page,rows);//每页数据
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",0);
        map.put("data",hsemp.selSpageEmpholi(page,limit,mo));
        return map;
    }

    @RequestMapping("/pageListWhere")
    @ResponseBody
    public Map pageListWhere(int page,int limit,String empName,int sta){
        String month = null;
        Calendar cal = Calendar.getInstance();
        int mo = cal.get(Calendar.MONTH) + 1;
        if (sta==1){
            month = ""+mo;
        }else if (sta==2){
            month="all";
        }else if (sta==3){
            month = ""+(mo-1);
        }
        Map map = new HashMap();
        Integer count = hsemp.seltotalEmpholiWhere(empName,month);
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",hsemp.pageListEmpholiWhere(page,limit,empName,month));
        System.out.println("count "+count);
        return map;
    }


    @ResponseBody
    @RequestMapping("/holi_ptcls")
    public Map holi_ptcls(int page, int limit,Integer empId,Integer sum,String month){
//        List list = cs.selSpage(page,rows);//每页数据
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",sum);
        map.put("data",hsemp.selSpageEmpholibyid(page,limit,empId,sum,month));
        return map;
    }
}
