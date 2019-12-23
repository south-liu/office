package com.ht.controller.zwj;

import com.ht.service.zwj.EvaluationStandardService;
import com.ht.vo.EvaluationVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/evaluation-standard")
public class EvaluationStandardController {
    @Resource
    private EvaluationStandardService evaluationStandardService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "zwj/evaluation-standard/home";
    }

    @ResponseBody
    @RequestMapping(value = "/allData", method = RequestMethod.GET)
    public Map<String, Object> allData(@RequestParam(defaultValue = "1", required = false) int page, @RequestParam(defaultValue = "10", required = false) int limit) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", evaluationStandardService.queryAllTotalNumber());
        map.put("data", evaluationStandardService.queryAllData(page, limit));

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> add(EvaluationVO evaluation) {
        Map<String, Object> map = new HashMap<>();

        if (evaluation != null && evaluation.getEvaluationName() != null && !evaluation.getEvaluationName().isEmpty()) {
            EvaluationVO evaluationByName = evaluationStandardService.queryEvaluationStandardByName(evaluation.getEvaluationName());
            if (evaluationByName != null) {
                map.put("code", 2);
                map.put("msg", "考评名称已存在！");
                return map;
            }
            evaluation.setScore(5);// 所有考评内容分值 5

            try {
                long id = evaluationStandardService.addEvaluationStandard(evaluation);
                if (id > 0) {
                    map.put("code", 0);
                    map.put("msg", "添加成功！");
                    map.put("result", id);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                map.put("code", 1);
                map.put("msg", "添加失败！");
            }
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public EvaluationVO detail(@RequestParam int evaluationId) {
        return evaluationStandardService.queryEvaluationStandardById(evaluationId);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> update(EvaluationVO evaluation) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (evaluation.getEvaluationId() == null) {
                throw new Exception("考评ID不能为空！");
            }
            if (evaluation.getEvaluationName() == null || evaluation.getEvaluationName().isEmpty()) {
                throw new Exception("考评名称不能为空！");
            }

            evaluation.setScore(5);// 所有考评内容分值 5
            evaluationStandardService.updateEvaluationStandard(evaluation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 1);
            map.put("msg", "服务器错误！");
            return map;
        }
        map.put("code", 0);
        map.put("msg", "修改成功！");
        map.put("result", evaluation.getEvaluationId());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, Object> delete(@RequestParam int evaluationId) {
        Map<String, Object> map = new HashMap<>();
        try {
            evaluationStandardService.deleteEvaluationStandard(evaluationId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 1);
            map.put("msg", "服务器错误！");
            return map;
        }
        map.put("code", 0);
        map.put("msg", "删除成功！");
        map.put("result", evaluationId);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteMulti", method = RequestMethod.POST)
    public Map<String, Object> deleteMulti(@RequestParam(name = "evaluationIds[]") int[] evaluationIds) {
        Map<String, Object> map = new HashMap<>();

        int i = 0;
        try {
            if (evaluationIds == null) {
                throw new Exception("ID数组不能为空！");
            }
            for (int evaluationId : evaluationIds) {
                evaluationStandardService.deleteEvaluationStandard(evaluationId);
                ++i;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            map.put("code", 1);
            map.put("msg", "服务器错误！");
            return map;
        }

        map.put("code", 0);
        map.put("msg", "删除成功！");
        map.put("result", i);
        return map;
    }
}
