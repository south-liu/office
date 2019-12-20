package com.ht.controller.ljy;

import com.ht.service.ljy.bedroomService;
import com.ht.service.ljy.studentclassService;
import com.ht.service.wzq.HTService;
import com.ht.service.zwj.StudentHuorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JY on 2019/12/19.
 */
@Controller
@RequestMapping("bedroom")
public class BedRoomController {

    @Resource
    private bedroomService bedroomService;

    @Resource
    private studentclassService studentclassService;

    @Resource
    private HTService htService;

    @Resource
    private StudentHuorService studentHuorService;

    @RequestMapping("/goto_bedroom_list")
    public String goto_bedroom_list(Model model) {
        model.addAttribute("clazzs", studentclassService.studentclass_list());
        model.addAttribute("floor", htService.selfloor());
        System.out.println("查询出的数据" + htService.selfloor());
        model.addAttribute("huors", studentHuorService.allData());
        System.out.println("去查询列表界面！");
        return "ljy/bedroom_list";
    }

    @ResponseBody
    @RequestMapping("/bedroom_list")
    public Map bedroom_list(int page, int limit) {
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", " ");
        map.put("count", bedroomService.bedroom_count());
        map.put("data", bedroomService.bedroom_list(page, limit));
//        System.out.println("map中的数据："+map.toString());
        System.out.println("查询列表界面！");
        return map;
    }
//带条件查询
    @ResponseBody
    @RequestMapping("/bedroom_search_list")
    public Map bedroom_search_list(int page,int limit,Integer clazz,Integer floor,Integer huor){
        Map map=new HashMap();
        map.put("code",0);
        map.put("msg","");
        map.put("count",bedroomService.bedroom_search_count(clazz, floor, huor));
        map.put("data",bedroomService.bedroom_search_list(page, limit, clazz, floor, huor));
        return map;
    }



    @RequestMapping("/goto_huor_studName")
    public String goto_huor_studName(Integer huor,Model model){
        model.addAttribute("huor",huor);
        System.out.println("进入宿舍学生详情界面");
        return "ljy/huor_stuName_list";
    }

    @ResponseBody
    @RequestMapping("/huor_student")
    public Map huor_studName(int page,int limit,Integer huor){
        System.out.println("huor:"+huor);
        Map map=new HashMap();
        map.put("code",0);
        map.put("msg","");
        map.put("data",bedroomService.huor_stuName(page,limit,huor));
        map.put("count",bedroomService.huor_stuName_count(huor));
        return map;
    }

}
