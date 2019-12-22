package com.ht.controller.cc;

import com.ht.service.cc.HoliStuService;
import com.ht.vo.HolidayStudent;
import com.ht.vo.StudentVO;
import groovy.ui.SystemOutputInterceptor;
import org.activiti.engine.impl.bpmn.data.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.*;

@Controller
@RequestMapping("/stuholi")
public class StuHoliController {
    @Resource
    private HoliStuService hs;

    @RequestMapping("/tolist")
    public  String tolist(){
        return "cc/stuholiday/holi_list";
    }

    @RequestMapping("/toptcls")
    public  String toptcls(Model model,Integer stuId,Integer sum,int mon){
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
        model.addAttribute("stuId",stuId);
        return "cc/stuholiday/stuholi_ptcls";
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
        map.put("data",hs.selSpage(page,limit,mo));

        return map;
    }


    @ResponseBody
    @RequestMapping("/holi_ptcls")
    public Map holi_ptcls(int page, int limit,Integer studentId,Integer sum,String month){
//        List list = cs.selSpage(page,rows);//每页数据
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count",sum);
        map.put("data",hs.selSpageholi(page,limit,studentId,sum,month));

        return map;
    }

    @RequestMapping("/pageListWhere")
    @ResponseBody
    public Map pageListWhere(int page,int limit,String stuName,int sta){
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
        Integer count = hs.seltotalholWhere(stuName,month);
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",hs.pageListWhere(page,limit,stuName,month));
        return map;
    }
}
