package com.ht.controller.zwj;

import com.ht.service.zwj.StudentFallService;
import com.ht.vo.StudentFallVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student-fall")
public class StudentFallController {
    @Resource
    private StudentFallService studentFallService;

    @RequestMapping("/home")
    public String home() {
        return "zwj/student-fall/home";
    }

    // 通过分页获取数据返回json数据
    @ResponseBody
    @RequestMapping(value = "/allData", method = RequestMethod.GET)
    public Map<String, Object> allData(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        // 通过分页返回List数据
        List<StudentFallVO> studentFallVOS = studentFallService.allData(page, limit);

        // leyui dataTable固定返回的json数据格式
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", studentFallService.getTotality());
        hashMap.put("data", studentFallVOS);

        return hashMap;
    }

    // 删除
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Integer delete(@RequestParam int fallId) {
        studentFallService.delete(fallId);
        return fallId;
    }

    // 添加
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> add(StudentFallVO studentFallVO) {
        Map<String, Object> hashMap = new HashMap<>();

        if (studentFallVO == null || studentFallVO.getLevel() == null) {
            hashMap.put("code", 1);
            hashMap.put("msg", "添加失败！");
            hashMap.put("result", studentFallService.add(studentFallVO));

            return hashMap;
        }

        // 通过届别名称查询
        StudentFallVO studentFall = studentFallService.getStudentFallByLevel(studentFallVO.getLevel());
        if (studentFall != null) {// 数据库已存在对象
            hashMap.put("code", 2);
            hashMap.put("msg", "届别名称已存在！");
        } else {// 不存在则添加
            hashMap.put("code", 0);
            hashMap.put("msg", "添加成功！");
            hashMap.put("result", studentFallService.add(studentFallVO));
        }
        return hashMap;
    }

    // 获取详细信息
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public StudentFallVO detail(@RequestParam int fallId) {
        StudentFallVO studentFall = studentFallService.getStudentFallById(fallId);
        return studentFall;
    }

    // 获取详细信息
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Integer update(StudentFallVO studentFall) {
        studentFallService.updateStudentFall(studentFall);
        return studentFall.getFallId();
    }

    // 删除多个
    @ResponseBody
    @RequestMapping(value = "/deleteMulti", method = RequestMethod.POST)
    public int deleteMulti(@RequestParam(name = "fallIds[]") Integer[] fallIds) {
        studentFallService.deleteMultiStudentFall(fallIds);
        return fallIds.length;
    }
}
