package com.ht.controller.llb;

import com.alibaba.fastjson.JSONArray;
import com.ht.service.llb.IDeptService;
import com.ht.service.llb.IEmailService;
import com.ht.service.llb.IEmpService;
import com.ht.utils.file.FileUpload;
import com.ht.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("email")
public class EmailController {
    @Resource
    private IEmailService emailService;
    @Resource
    private IDeptService deptService;
    @Resource
    private IEmpService empService;

    @RequestMapping("/home")
    public String home() {
        return "llb/email/home";
    }

    @RequestMapping("/toAddEmail")
    public String toAddEmail() {
        return "llb/email/email_add";
    }

    @RequestMapping("/allDept")
    @ResponseBody
    public Map allDept() {
        Map map = new HashMap();
        List<DeptVO> allDept = deptService.allDept();
        map.put("allDept", allDept);
        return map;
    }

    @RequestMapping("/allEmp")
    @ResponseBody
    public JSONArray allEmp() {
        Map map = new HashMap();
        List<DeptVO> addDept = deptService.allDept();
        List<EmpVO> allEmp = empService.allEmp();
        JSONArray deptArr = new JSONArray();
        for (DeptVO deptVO : addDept) {
            Map deptMap = new HashMap();
            deptMap.put("name", deptVO.getDeptName());
            deptMap.put("optgroup",true);
            JSONArray empArr = new JSONArray();
            for (EmpVO empVO : allEmp) {
                if (empVO.getDeptId() == deptVO.getDeptId()) {
                    Map empMap = new HashMap();
                    empMap.put("value", empVO.getEmpId());
                    empMap.put("name", empVO.getEmpName());
                    empArr.add(empMap);
                }
            }
            deptMap.put("children", empArr);
            deptArr.add(deptMap);
        }
        return deptArr;
    }

    @RequestMapping("/addEmail")
    @ResponseBody
    public String addEmail(EmailVO emailVO, MultipartFile file, String empIds, HttpServletRequest request){

        EmpVO empVO = (EmpVO) request.getSession().getAttribute("emp");

        if (file!=null) {
            String filename = file.getOriginalFilename();
            //保存邮件

            String realPath = request.getSession().getServletContext().getRealPath("");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String strDate = sdf.format(new Date());

            String dirPath = realPath+"WEB-INF"+File.separator+"static"+File.separator+"upload"+File.separator + strDate + File.separator;

            //上传文件
            String fileReName = FileUpload.uploadAndRename(dirPath,file);
            emailVO.setUrl(dirPath+fileReName);
            emailVO.setFileName(filename);
        }

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        emailVO.setEmpId(empVO.getEmpId());
        emailVO.setSendTime(sim.format(new Date()));
        emailService.addEmail(emailVO);

        //保存接收人
        String[] empIdstr = empIds.split(",");
        for (String s : empIdstr) {
            EmailReceiverVO emailReceiverVO = new EmailReceiverVO();
            emailReceiverVO.setEmailId(emailVO.getEmailId());
            emailReceiverVO.setReceiver(Integer.parseInt(s));
            emailReceiverVO.setIsRead(0);
            emailService.addEmailReceiver(emailReceiverVO);
        }
        return "success";
    }

    @RequestMapping("/getNoRead")
    @ResponseBody
    public Map getNoRead(HttpSession session){
        EmpVO empVO = (EmpVO) session.getAttribute("emp");

        //所有未读邮件
        List<EmailReceiverVO> allNoRead = new ArrayList<>();

        List<EmailReceiverVO> allReceiveEmail = emailService.allReceiveEmail(empVO.getEmpId());
        for (EmailReceiverVO emailReceiverVO : allReceiveEmail) {
            if (emailReceiverVO.getIsRead() == 0) {
                allNoRead.add(emailReceiverVO);
            }
        }
        Map map = new HashMap();
        map.put("allNoRead",allNoRead);
        return map;
    }

    @RequestMapping("/pageListReceiveEmail")
    @ResponseBody
    public Map pageListReceiveEmail(int page, int limit, HttpSession session){
        EmpVO empVO = (EmpVO) session.getAttribute("emp");
        List<Map> list = emailService.allReceiveEmail(empVO.getEmpId(),page,limit);
        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

    @RequestMapping("/pageListMySendEmail")
    @ResponseBody
    public Map pageListMySendEmail(int page, int limit, HttpSession session){
        EmpVO empVO = (EmpVO) session.getAttribute("emp");
        List<Map> list = emailService.allMySendEmail(empVO.getEmpId(),page,limit);
        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

    @RequestMapping("/viewEmail")
    public String viewEmail(Integer emailId, Model model,Integer emailReceiverId,HttpSession session){

        EmpVO empVO = (EmpVO) session.getAttribute("emp");

        EmailReceiverVO emailReceiverVO = emailService.selEmailReceiverById(emailReceiverId);

        //如果查看的人是该邮件的接收人
        if (emailReceiverVO.getReceiver().equals(empVO.getEmpId()) ) {
            emailService.updIsRead(emailReceiverId,1);
        }

        EmailVO emailVO = emailService.selEmailById(emailId);
        model.addAttribute("email",emailVO);
        return "llb/email/email_view";
    }

    @RequestMapping("/delEmail")
    @ResponseBody
    public String delEmail(Integer emailReceiverId){
        emailService.delEmail(emailReceiverId);
        return "success";
    }

    @RequestMapping("/download.do")
    public void download(Integer emailId, HttpServletRequest request, HttpServletResponse response) throws Exception{
        EmailVO emailVO = emailService.selEmailById(emailId);

        // 指定要下载的文件所在路径
        String path = emailVO.getUrl();
        // 创建该文件对象
        File file = new File(path);

        String fileName = this.getFilename(request,emailVO.getFileName());

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

        //打开本地文件流
        InputStream inputStream = new FileInputStream(file);
        //激活下载操作
        OutputStream os = response.getOutputStream();

        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b,0,length);
        }
        // 这里主要关闭。
        os.close();
        inputStream.close();
    }

    /**
     * 根据浏览器的不同进行编码设置，返回编码后的文件名
     */
    public String getFilename(HttpServletRequest request,
                              String filename) throws Exception {
        // IE不同版本User-Agent中出现的关键词
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        // 获取请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if (userAgent.contains(keyWord)) {
                //IE内核浏览器，统一为UTF-8编码显示
                return URLEncoder.encode(filename, "UTF-8");
            }
        }
        //火狐等其它浏览器统一为ISO-8859-1编码显示
        return new String(filename.getBytes("UTF-8"), "ISO-8859-1");
    }
}