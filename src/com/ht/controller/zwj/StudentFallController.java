package com.ht.controller.zwj;

import com.ht.service.zwj.StudentFallService;
import com.ht.service.zwj.other.studentClass.OStudentClassService;
import com.ht.vo.StudentClassVO;
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
public class StudentFallController {// 学生届别
    @Resource
    private StudentFallService studentFallService;
    @Resource
    private OStudentClassService oStudentClassService;

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
    public Map<String, Object> delete(@RequestParam int fallId) {
        Map<String, Object> map = new HashMap<>();
        try {
            /*
             查询该届别下面的所有班级，有班级则不能删除
             */
            List<StudentClassVO> studentClassVOS = oStudentClassService.queryStudentClassByFallId(fallId);
            if (studentClassVOS != null && studentClassVOS.size() > 0) {
                map.put("code", 1);
                map.put("msg", "届别内存在班级，不得删除此届别！");
                return map;
            }

            // 删除届别
            studentFallService.delete(fallId);
            map.put("code", 0);
            map.put("msg", "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "服务器错误！");
            return map;
        }
        return map;
    }

    // 添加
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> add(StudentFallVO studentFallVO) {
        Map<String, Object> map = new HashMap<>();

        try {
            // 通过届别名称查询，是否有存在相同界别
            StudentFallVO studentFall = studentFallService.getStudentFallByLevel(studentFallVO.getLevel());
            if (studentFall != null) {
                map.put("code", 1);
                map.put("msg", "届别名称已存在！");
            } else {// 不存在则添加
                studentFallService.add(studentFallVO);
                map.put("code", 0);
                map.put("msg", "添加成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "服务器错误！");
        }

        return map;
    }

    // 获取详细信息
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Map<String, Object> detail(@RequestParam int fallId) {
        Map<String, Object> map = new HashMap<>();

        try {
            map.put("studentFall", studentFallService.getStudentFallById(fallId));
            map.put("code", 0);
            map.put("msg", "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "服务器错误！");
        }

        return map;
    }

    // 修改
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> update(StudentFallVO studentFall) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<StudentFallVO> studentFallByLevelNotFallid = studentFallService.queryStudentFallByLevelNotFallid(studentFall);
            if (studentFallByLevelNotFallid != null && !studentFallByLevelNotFallid.isEmpty()) {
                map.put("code", 1);
                map.put("msg", "界别名称已存在！");
                return map;
            }
            studentFallService.updateStudentFall(studentFall);
            map.put("code", 0);
            map.put("msg", "修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "服务器错误！");
        }

        return map;
    }

    // 删除多个
    @ResponseBody
    @RequestMapping(value = "/deleteMulti", method = RequestMethod.POST)
    public Map<String, Object> deleteMulti(@RequestParam(name = "fallIds[]") Integer[] fallIds) {
        Map<String, Object> map = new HashMap<>();

        int i = 0;
        try {
            studentFallService.deleteMultiStudentFall(fallIds);
            ++i;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (i > 0) {
            map.put("code", 0);
            map.put("msg", "删除成功！");
        } else {
            map.put("code", -1);
            map.put("msg", "服务器错误！");
        }
        return map;
    }
}
