package com.ht.controller.llb;

import com.ht.service.llb.ISystemLogService;
import com.ht.vo.SystemLogVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/systemLog")
public class SystemLogController {

    @Resource
    private ISystemLogService systemLogService;

    @RequestMapping("/home")
    public String home(){
        return "llb/system-log/home";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map pageList(int page,int limit){
        Map map = new HashMap();
        List<Map> list = systemLogService.pageList(page,limit);
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

    @RequestMapping("/pageListWhere")
    @ResponseBody
    public Map pageListWhere(int page, int limit, String startTime, String endTime){
        Map map = new HashMap();
        List<Map> list = systemLogService.pageListWhere(page,limit,startTime,endTime);
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

}
