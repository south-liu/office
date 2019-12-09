package com.ht.controller.ljy;
import com.ht.service.ljy.educationService;
import com.ht.vo.EducationVO;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JY on 2019/12/5.
 */

@Controller
@RequestMapping("/education")
public class EduController {

    @Resource
    educationService educationService;

    @ResponseBody
    @RequestMapping("/educationlist")
    public Map educationlist(int page, int limit,String empId) {
        System.out.println("查询教育背景！！！");
        Map map = new HashMap();
        //Integer count = educationService.education_count();
        Integer count = educationService.education_countByEmpId(Integer.parseInt(empId));
        map.put("code", 0);
        map.put("msg", 0);
        map.put("count", count);
        map.put("data", educationService.education_list(page, limit,empId));
//        System.out.println("查询界面："+educationService.education_list(page,limit));
        return map;
    }

    @RequestMapping("/gotoeducationlist")
    public String gotoeducationlist(String empId, Model model) {
        model.addAttribute("empId",empId);
        System.out.println("去查询教育页面！！！");
        return "ljy/education";
    }

    @ResponseBody
    @RequestMapping("/educationdelete")
    public String educationdelete(EducationVO educationVO) {
        System.out.println("删除进来了！！！");
        educationService.education_delete(educationVO);
        return "success";
    }


    @ResponseBody
    @RequestMapping("/educationadd")
    public String educationadd(EducationVO educationVO) {
        System.out.println("添加进来了！！！");
        educationService.education_add(educationVO);
        return "success";
    }


    @ResponseBody
    @RequestMapping("/educationupdate")
    public String educationupdate(EducationVO educationVO) {
        System.out.println("修改成功！");
        System.out.println("sdasdadassaasdasdasdsada");
        educationService.education_update(educationVO);
        return "success";
    }
}
