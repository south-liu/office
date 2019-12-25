package com.ht.controller.llb;

import com.ht.service.llb.INoticeService;
import com.ht.vo.EmpVO;
import com.ht.vo.NoticeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private INoticeService noticeService;

    @RequestMapping("/toNoticeList")
    public String toFileList(){
        return "llb/notice/home";
    }

    @RequestMapping("/toAddNotice")
    public String toAddNotice(){
        return "llb/notice/notice_add";
    }

    //角色列表分页
    @RequestMapping("/pageList")
    @ResponseBody
    public Map pageList(int page, int limit){
        Map map = new HashMap();
        Integer count = noticeService.countNotice(1);
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",noticeService.pageList(1,page,limit));
        return map;
    }

    @RequestMapping("/addNotice")
    @ResponseBody
    public String addNotice(NoticeVO noticeVO, HttpSession session){
        EmpVO empVO = (EmpVO) session.getAttribute("emp");
        noticeVO.setEmpId(empVO.getEmpId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        noticeVO.setNoticeIime(simpleDateFormat.format(new Date()));
        noticeService.addNotice(noticeVO);
        return "success";
    }

    @RequestMapping("/viewNotice")
    public String viewNotice(Integer noticeId, Model model){
        /*NoticeVO noticeVO = noticeService.selNoticeById(noticeId);
        model.addAttribute("notice",noticeVO);*/
        Map map = noticeService.selNoticeByIdToMap(noticeId);
        model.addAttribute("notice",map);
        return "llb/notice/notice_view";
    }

    @RequestMapping("/toEditNotice")
    public String toEditNotice(Integer noticeId, Model model){
        Map map = noticeService.selNoticeByIdToMap(noticeId);
        model.addAttribute("notice",map);
        return "llb/notice/notice_edit";
    }

    @RequestMapping("/editNotice")
    @ResponseBody
    public String editNotice(NoticeVO noticeVO){
        NoticeVO db = noticeService.selNoticeById(noticeVO.getNoticeId());
        db.setTitle(noticeVO.getTitle());
        db.setNoticeType(noticeVO.getNoticeType());
        db.setContent(noticeVO.getContent());

        noticeService.updNotice(db);
        return "success";
    }

    @RequestMapping("/delNotice")
    @ResponseBody
    public String delNotice(NoticeVO noticeVO){
        noticeService.delNotice(noticeVO);
        return "success";
    }

}

