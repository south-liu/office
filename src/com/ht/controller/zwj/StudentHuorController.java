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

    /*
     添加所需要显示的数据：
        1.allFloor：所有的楼栋
     */
    @ResponseBody
    @RequestMapping(value = "/addTheRequiredData", method = RequestMethod.GET)
    public Map<String, Object> addTheRequiredData() {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> requiredData = new HashMap<>();
            List<StudentFloorVO> studentFloorVOS = studentHuorService.allFloorData();// 查询所有的楼栋
            requiredData.put("allFloor", studentFloorVOS);

            map.put("requiredData", requiredData);
            map.put("code", 0);
            map.put("msg", "查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "服务器错误！");
        }
        return map;
    }

    // 添加
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> add(StudentHuorVO studentHuorVO) {
        Map<String, Object> map = new HashMap<>();

        try {
            // 通过宿舍房号查询是否已存在相同房号
            StudentHuorVO studentHuor = studentHuorService.getStudentHuorByHuorName(studentHuorVO.getHuorName());
            if (studentHuor != null) {
                map.put("code", 1);
                map.put("msg", "宿舍房号已存在！");
            } else {
                studentHuorService.add(studentHuorVO);
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
    public Map<String, Object> detail(@RequestParam int hourId) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("floorList", studentHuorService.allFloorData());// 所有的楼栋，用于下拉显示
            map.put("studentHuor", studentHuorService.getStudentHuorById(hourId));// 通过ID查询宿舍详细信息
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
    public Map<String, Object> update(StudentHuorVO studentHuor) {
        Map<String, Object> map = new HashMap<>();

        /*
        修改之前，判断是否已存在相同的宿舍房号
        1.通过宿舍房号ID查询：除去该ID以外的所有宿舍，宿舍房号与接收的宿舍房号相同的
        2.通过上方查询，查询出了数据，则证明存在相同宿舍房号
         */
        List<StudentHuorVO> studentHuors = studentHuorService.queryStudentHuorNotHuorid(studentHuor);
        if (studentHuors != null && !studentHuors.isEmpty()) {
            map.put("code", 1);
            map.put("msg", "宿舍房号已存在！");
            return map;
        }

        try {
            studentHuorService.updateStudentHuor(studentHuor);

            map.put("code", 0);
            map.put("msg", "修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", -1);
            map.put("msg", "服务器错误！");
        }

        return map;
    }

    // 删除
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam int hourId) {
        Map<String, Object> map = new HashMap<>();

        /*
        删除宿舍房号，将移除该宿舍内的所有学生
         */
        try {
            studentHuorService.delete(hourId);

            map.put("code", 0);
            map.put("msg", "删除成功！");
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
    public int deleteMulti(@RequestParam(name = "hourId[]") Integer[] ids) {
        studentHuorService.deleteMultiStudentHuor(ids);
        return ids.length;
    }
}
