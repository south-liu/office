package com.ht.controller.zwj;

import com.ht.service.zwj.ProjectService;
import com.ht.vo.ProjectVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 答辩项目Controller
@Controller
@RequestMapping("/project")
public class ProjectController {
    private static final String ALREADY_EXIST_MSG = "项目名称已存在！";
    @Resource
    private ProjectService projectService;

    @RequestMapping("/home")
    public String home() {
        return "zwj/project/home";
    }

    // 通过分页获取数据返回json数据
    @ResponseBody
    @RequestMapping(value = "/allData", method = RequestMethod.GET)
    public Map<String, Object> allData(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        // 通过分页返回List数据
        List<ProjectVO> VOS = projectService.allData(page, limit);

        // leyui dataTable固定返回的json数据格式
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", 0);
        hashMap.put("msg", "");
        hashMap.put("count", projectService.getTotality());
        hashMap.put("data", VOS);

        return hashMap;
    }

    // 删除
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Integer delete(@RequestParam int projectId) {
        projectService.delete(projectId);
        return projectId;
    }

    // 添加
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> add(ProjectVO projectVO) {
        Map<String, Object> hashMap = new HashMap<>();

        if (projectVO == null || projectVO.getProjectName() == null) {
            hashMap.put("code", 1);
            hashMap.put("msg", "添加失败！");

            return hashMap;
        }

        // 通过届别名称查询
        ProjectVO project = projectService.getProjectByProjectName(projectVO.getProjectName());
        if (project != null) {// 数据库已存在对象
            hashMap.put("code", 2);
            hashMap.put("msg", ALREADY_EXIST_MSG);
        } else {// 不存在则添加
            hashMap.put("code", 0);
            hashMap.put("msg", "添加成功！");
            hashMap.put("result", projectService.add(projectVO));
        }
        return hashMap;
    }

    // 获取详细信息
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ProjectVO detail(@RequestParam int projectId) {
        return projectService.getProjectById(projectId);
    }

    // 获取详细信息
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Integer update(ProjectVO project) {
        projectService.updateProject(project);
        return project.getProjectId();
    }

    // 删除多个
    @ResponseBody
    @RequestMapping(value = "/deleteMulti", method = RequestMethod.POST)
    public int deleteMulti(@RequestParam(name = "projectIds[]") Integer[] projectIds) {
        projectService.deleteMultiProject(projectIds);
        return projectIds.length;
    }
}
