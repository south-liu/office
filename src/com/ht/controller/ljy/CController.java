package com.ht.controller.ljy;

import com.ht.service.ljy.classTypeService;
import com.ht.vo.ClassTypeVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JY on 2019/12/4.
 */

//班级类别管理
@Controller
@RequestMapping("/classtype")
public class CController {
    @Resource
    classTypeService classTypeService;

    //去列表页面
    @RequestMapping("/gotoclasstypelist")
    public String gotoclasstypelist() {
        System.out.println("去列表页面");
        return "ljy/classtype";
    }


    //        查询列表显示
    @ResponseBody
    @RequestMapping("/classtypelist")
    public Map courselist(int page, int limit) {
        System.out.println("/班级类别管理进来了！！！");
        Map map = new HashMap();
        Integer count = classTypeService.classTypeCount();
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", classTypeService.classTypeList(page, limit));
        System.out.println("map集合：" + map.toString());
        return map;
    }


//    删除单个数据
    @ResponseBody
    @RequestMapping("/delclasstype")
    public String delclasstype(ClassTypeVO classTypeVO){
        System.out.println("删除成功！！！");
        classTypeService.deleteClassType(classTypeVO);
        return " ";
    }


//    添加班级类型
    @ResponseBody
    @RequestMapping("/addclasstype")
    public String toaddclasstype(ClassTypeVO classTypeVO){
        System.out.println("添加页面！！！");
        classTypeService.addClassType(classTypeVO);
        return "ljy/add_classtype";
    }

//    修改方法
    @ResponseBody
    @RequestMapping("/updateclasstype")
    public String updateclasstype(ClassTypeVO classTypeVO){
        System.out.println("进来修改方法！！");
        System.out.println("update table successful!");
        classTypeService.updateClassType(classTypeVO);
        return "ljy";
    }

}
