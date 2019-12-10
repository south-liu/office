package com.ht.controller.zwj;

import com.ht.service.zwj.StudentHuorService;
import com.ht.vo.StudentFloorVO;
import com.ht.vo.StudentHuorVO;
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
@RequestMapping("/student-huor")
public class StudentHuorController {// 学生宿舍管理
    @Resource
    private StudentHuorService studentHuorService;

    // 当前模块的vo
    private Class objectClass = StudentHuorVO.class;

    @RequestMapping("/home")
    public String hello() {
        return "zwj/student-huor/home";
    }

    // 通过分页获取数据返回json数据
    @ResponseBody
    @RequestMapping(value = "/allData", method = RequestMethod.GET)
    public Map<String, Object> allData(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        // 通过分页返回List数据
        List<Map<String, Object>> VOS = studentHuorService.allData(page, limit);

        // leyui dataTable固定返回的json数据格式
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", studentHuorService.getTotality());
        hashMap.put("data", VOS);

        return hashMap;
    }

    // 添加
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> add(StudentHuorVO studentHuorVO) {
        Map<String, Object> hashMap = new HashMap<>();

        if (studentHuorVO == null || studentHuorVO.getHuorName() == null) {
            hashMap.put("code", 1);
            hashMap.put("msg", "添加失败！");
            hashMap.put("result", studentHuorService.add(studentHuorVO));

            return hashMap;
        }

        // 通过届别名称查询
        StudentHuorVO studentHuor = studentHuorService.getStudentHuorByHuorName(studentHuorVO.getHuorName());
        if (studentHuor != null) {// 数据库已存在对象
            hashMap.put("code", 2);
            hashMap.put("msg", "宿舍房号已存在！");
        } else {// 不存在则添加
            hashMap.put("code", 0);
            hashMap.put("msg", "添加成功！");
            hashMap.put("result", studentHuorService.add(studentHuorVO));
        }
        return hashMap;
    }

    // 查询所有楼栋
    @ResponseBody
    @RequestMapping(value = "/allFloorData", method = RequestMethod.GET)
    public List<StudentFloorVO> allFloorData() {
        List<StudentFloorVO> list = studentHuorService.allFloorData();
        return list;
    }


    // 获取详细信息
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public StudentHuorVO detail(@RequestParam int hourId) {
        StudentHuorVO studentHuor = studentHuorService.getStudentHuorById(hourId);
        return studentHuor;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Integer update(StudentHuorVO studentHuor) {
        studentHuorService.updateStudentHuor(studentHuor);
        return studentHuor.getHourId();
    }

    // 删除
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Integer delete(@RequestParam int hourId) {
        studentHuorService.delete(hourId);
        return hourId;
    }

    // 删除多个
    @ResponseBody
    @RequestMapping(value = "/deleteMulti", method = RequestMethod.POST)
    public int deleteMulti(@RequestParam(name = "hourId[]") Integer[] ids) {
        studentHuorService.deleteMultiStudentHuor(ids);
        return ids.length;
    }
}