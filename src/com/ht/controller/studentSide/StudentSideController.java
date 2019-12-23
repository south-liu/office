package com.ht.controller.studentSide;

import com.ht.service.llb.IStudentService;
import com.ht.service.zwj.SAssessmentService;
import com.ht.utils.gee.GeeConfig;
import com.ht.utils.gee.GeeLib;
import com.ht.vo.StudentVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

// 公共控制器
@Controller
@RequestMapping("/studentSide")
public class StudentSideController {
    @Resource
    private IStudentService studentService;
    @Resource
    private SAssessmentService sAssessmentService;

    /**
     * 学生端首页入口
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "public/student/index";
    }

    /**
     * 学生端登陆界面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "public/student/login";
    }

    /**
     * 学生端欢迎界面
     *
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome(Model model, HttpSession session) {
        StudentVO student = (StudentVO) session.getAttribute("student");
        if (student != null) {
            // 已登录，查询该学生的所有考评
            try {
                model.addAttribute("assessmentList", sAssessmentService.queryAssessmentByStudent(student));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "public/student/welcome";
    }


    /**
     * 获取极简验证码
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/geeCheck", produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String geeCheck(HttpSession session) {
        GeeLib geeLib = new GeeLib(GeeConfig.getGee_id(), GeeConfig.getGee_key(), GeeConfig.isnewfailback());
        String resStr = "{}";
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP
        //进行验证预处理
        int gtServerStatus = geeLib.preProcess(param);
        session.setAttribute(geeLib.gtServerStatusSessionKey, gtServerStatus);
        resStr = geeLib.getResponseStr();
        return resStr;
    }

    //        学生端
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map loginCheck(StudentVO studentVO, HttpSession session,
                          String challenge, String validate, String seccode) {
        GeeLib geeLib = new GeeLib(GeeConfig.getGee_id(), GeeConfig.getGee_key(), GeeConfig.isnewfailback());
        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) session.getAttribute(geeLib.gtServerStatusSessionKey);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP
        int gtResult = 0;
        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            try {
                gtResult = geeLib.enhencedValidateRequest(challenge, validate, seccode, param);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = geeLib.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        Map map = new HashMap();

        StudentVO dbStudent = studentService.findByPhone(studentVO.getPhone());
        if (dbStudent == null) {
            map.put("type", "error");
            map.put("msg", "用户不存在");
        } else if (!studentVO.getPassWord().equals(dbStudent.getPassWord())) {
            map.put("type", "error");
            map.put("msg", "密码错误");
        } else if (dbStudent.getStat() == 0) {
            map.put("type", "error");
            map.put("msg", "该账户已被停用");
        } else {
            if (gtResult == 1) {
                //验证成功
                map.put("type", "success");
                map.put("msg", "登陆成功");
                session.setAttribute("student", dbStudent);
            } else {
                map.put("type", "geeError");
                map.put("msg", "验证失败");
            }
        }
        return map;
    }

    //查询学生信息
    @RequestMapping("/selStu")
    @ResponseBody
    public StudentVO selStu(Integer stuId){
        StudentVO studentVO = studentService.findById(stuId);
        return studentVO;
    }

    @RequestMapping("/repass")
    @ResponseBody
    public String repass(Integer stuId,String password){
        studentService.repass(stuId,password);
        return "success";
    }

    @RequestMapping("/exit")
    @ResponseBody
    public String exit(HttpSession session) {
        session.invalidate();
        return "success";
    }

}
