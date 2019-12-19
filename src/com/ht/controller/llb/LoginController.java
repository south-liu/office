package com.ht.controller.llb;

import com.ht.dao.llb.IStudentDao;
import com.ht.service.llb.IEmpService;
import com.ht.service.llb.IRoleService;
import com.ht.service.llb.IStudentService;
import com.ht.utils.gee.GeeConfig;
import com.ht.utils.gee.GeeLib;
import com.ht.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class LoginController {

    @Resource
    private IEmpService empService;
    @Resource
    private IRoleService roleService;

    /**
     * 获取极简验证码
     * @param session
     * @return
     */
    @RequestMapping(value = "/geeCheck",produces = "text/plain; charset=utf-8")
    @ResponseBody
    public String geeCheck(HttpSession session){
        GeeLib geeLib = new GeeLib(GeeConfig.getGee_id(),GeeConfig.getGee_key(),GeeConfig.isnewfailback());
        String resStr = "{}";
        HashMap<String,String> param = new HashMap<String, String>();
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP
        //进行验证预处理
        int gtServerStatus = geeLib.preProcess(param);
        session.setAttribute(geeLib.gtServerStatusSessionKey,gtServerStatus);
        resStr = geeLib.getResponseStr();
        return resStr;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map loginCheck(EmpVO empVO,HttpSession session,
                         String challenge, String validate, String seccode){
        GeeLib geeLib = new GeeLib(GeeConfig.getGee_id(),GeeConfig.getGee_key(),GeeConfig.isnewfailback());
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
//            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
//            System.out.println("failback:use your own server captcha validate");
            gtResult = geeLib.failbackValidateRequest(challenge, validate, seccode);
//            System.out.println(gtResult);
        }

        Map map = new HashMap();

        EmpVO dbEmp = empService.findEmpByPhone(empVO.getPhone());
        if (dbEmp == null) {
            map.put("type","error");
            map.put("msg","用户不存在");
        } else if (!empVO.getPassword().equals(dbEmp.getPassword())) {
            map.put("type","error");
            map.put("msg","密码错误");
        } else if (dbEmp.getStatus() == 0){
            map.put("type","error");
            map.put("msg","该账户已被停用");
        } else {
            if (gtResult == 1) {
                //验证成功

                //查询出该员工的角色
                EmpRoleVO empRoleVO = roleService.selEmpRoleByEmpId(dbEmp.getEmpId());

                //如果没有授权角色
                if (empRoleVO==null) {
                    map.put("type","error");
                    map.put("msg","权限不足，请联系管理员");
                } else {
                    map.put("type","success");
                    map.put("msg","登陆成功");

                    //查询出该角色的所有权限
                    List<CharModuleVO> allCharModule = roleService.allCharModuleByCharacterId(empRoleVO.getCharacterId());
                    List<ModuleVO> moduleList = new ArrayList<>();
                    for (CharModuleVO charModuleVO : allCharModule) {
                        moduleList.add(roleService.selModuleByModuleId(charModuleVO.getModuleId()));
                    }

                    //所有根目录
                    List<ModuleVO> allRootModule = new ArrayList<>();
                    for (ModuleVO moduleVO : moduleList) {
                        if (moduleVO.getParentId() == 0) {
                            allRootModule.add(moduleVO);
                        }
                    }
                    session.setAttribute("allRootModule",allRootModule);
                    session.setAttribute("moduleList",moduleList);

                }

                session.setAttribute("emp",dbEmp);
            } else {
                map.put("type","geeError");
                map.put("msg","验证失败");
            }
        }
        return map;
    }

    //修改密码
    @RequestMapping("repass")
    @ResponseBody
    public String repass(Integer empId,String password){
        empService.repass(empId,password);
        return "success";
    }

    //查询员工信息
    @RequestMapping("/selEmp")
    @ResponseBody
    public EmpVO selEmp(Integer empId){
        EmpVO empVO = empService.findEmpById(empId);
        return empVO;
    }

    @RequestMapping("/exit")
    @ResponseBody
    public String exit(HttpSession session){
        session.invalidate();
        return "success";
    }

}
