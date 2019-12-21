package com.ht.controller.llb;

import com.ht.service.llb.INoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mynotice")
public class MyNoticeController {

    @Resource
    private INoticeService noticeService;

    @RequestMapping("/toNoticeList")
    public String toFileList(){
        return "llb/my-notice/home";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map pageList(int page, int limit){
        Map map = new HashMap();
        Integer count = noticeService.countNotice(2);
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",noticeService.pageList(2,page,limit));
        return map;
    }


    @RequestMapping("/viewNotice")
    public String viewNotice(Integer noticeId, Model model){
        /*NoticeVO noticeVO = noticeService.selNoticeById(noticeId);
        model.addAttribute("notice",noticeVO);*/
        Map map = noticeService.selNoticeByIdToMap(noticeId);
        model.addAttribute("notice",map);
        return "llb/my-notice/notice_view";
    }
}
