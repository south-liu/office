package com.ht.controller.llb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ht.service.llb.IDeptService;
import com.ht.service.llb.IEmpService;
import com.ht.service.llb.IRoleService;
import com.ht.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IRoleService roleService;
    @Resource
    private IDeptService deptService;
    @Resource
    private IEmpService empService;

    //角色列表界面
    @RequestMapping("/toRoleList")
    public String toRoleList(){
        return "llb/role/home";
    }

    //编辑权限界面
    @RequestMapping("/toEditPower")
    public String toEditPower(Model model,Integer characterId){
        List<ModuleVO> allmodule = roleService.allModule();
        JSONArray headMenuArr = new JSONArray();
        List<ModuleVO> headMenus = new ArrayList<>();
        for (ModuleVO moduleVO : allmodule) {
            if (moduleVO.getParentId() == 0) {
                headMenus.add(moduleVO);
            }
        }

        for (ModuleVO moduleVO : headMenus) {
            Map headMap = new HashMap();
            headMap.put("title",moduleVO.getModuleName());
            headMap.put("id",moduleVO.getModuleId());
            JSONArray childMoArr = new JSONArray();
            for (ModuleVO childModule : allmodule) {
                if (childModule.getParentId() == moduleVO.getModuleId()) {
                    Map childrenMap = new HashMap();
                    childrenMap.put("title", childModule.getModuleName());
                    childrenMap.put("id", childModule.getModuleId());
                    childMoArr.add(childrenMap);
                }
            }
            headMap.put("children",childMoArr);
            headMenuArr.add(headMap);
        }

        JSONArray ids = new JSONArray();

        //该角色的所有权限
        List<CharModuleVO> allPower = roleService.allCharModuleByCharacterId(characterId);
        for (CharModuleVO charModuleVO : allPower){
            if (roleService.selModuleByModuleId(charModuleVO.getModuleId()).getParentId()!=0) {
                ids.add(charModuleVO.getModuleId());
            }
        }
        model.addAttribute("allModule",headMenuArr);
        model.addAttribute("characterId",characterId);
        model.addAttribute("thisModule",ids);
        return "llb/role/edit_power";
    }

    //保存权限
    @RequestMapping("/updPower")
    @ResponseBody
    public String updPower(Integer characterId, @RequestParam("moduleIds[]") int[] moduleIds){

        //删除之前的权限
        roleService.delAllCharModule(characterId);

        if (moduleIds[0]!=0){
            //保存新的权限
            for (int moduleId : moduleIds) {
                CharModuleVO charModuleVO = new CharModuleVO();
                charModuleVO.setCharacterId(characterId);
                charModuleVO.setModuleId(moduleId);
                roleService.saveCharModule(charModuleVO);
            }
        }

        return "success";
    }


    //角色列表分页
    @RequestMapping("/pageList")
    @ResponseBody
    public Map pageList(int page, int limit){
        Map map = new HashMap();
        Integer count = roleService.countRole();
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",roleService.pageList(page,limit));
        return map;
    }

    //添加角色
    @RequestMapping("addRole")
    @ResponseBody
    public String addRole(CharactersVO charactersVO){
       roleService.addRole(charactersVO);
        return "success";
    }

    //删除角色
    @RequestMapping("/delRole")
    @ResponseBody
    public String delRole(CharactersVO charactersVO){
       roleService.deleteRole(charactersVO);
        return "success";
    }

    //编辑角色
    @RequestMapping("/editRole")
    @ResponseBody
    public String editRole(CharactersVO charactersVO){
        roleService.editRole(charactersVO.getCharacterId(),charactersVO.getCharacterName());
        return "success";
    }

    //授权界面
    @RequestMapping("/toGrant")
    public String toGrant(Model model,Integer characterId){
        List<DeptVO> addDept = deptService.allDept();
        List<EmpVO> allEmp = empService.allEmp();
        JSONArray deptArr = new JSONArray();
        for (DeptVO deptVO : addDept) {
            Map deptMap = new HashMap();
            deptMap.put("id",deptVO.getDeptId());
            deptMap.put("title",deptVO.getDeptName());
            deptMap.put("spread",true);
            JSONArray empArr = new JSONArray();
            for (EmpVO empVO : allEmp) {
                if (empVO.getDeptId() == deptVO.getDeptId()) {
                    Map empMap = new HashMap();
                    empMap.put("id",empVO.getEmpId());
                    empMap.put("title",empVO.getEmpName());
                    empArr.add(empMap);
                }
            }
            deptMap.put("children",empArr);
            deptArr.add(deptMap);
        }

        //该角色的所有用户
//        List<EmpVO> thisEmps = empService.byPostId(characterId);
        List<EmpRoleVO> thisEmps = roleService.allEmpByCharacterId(characterId);
        JSONArray ids = new JSONArray();
        for (EmpRoleVO empRoleVO : thisEmps) {
            ids.add(empRoleVO.getEmpId());
        }
        model.addAttribute("characterId",characterId);
        model.addAttribute("allEmp",deptArr);
        model.addAttribute("thisEmps",ids);
        return "llb/role/grant";
    }

    //保存用户授权
    @RequestMapping("/updGrant")
    @ResponseBody
    public String updGrant(Integer characterId, @RequestParam("empIds[]") int[] empIds){

        //删除之前该角色的员工
        roleService.delEmpRoleByCharacterId(characterId);

        if (empIds[0] != 0) {
            for (int empId : empIds) {

                //删除该员工之前的角色
                roleService.delEmpRoleByEmpId(empId);

                //保存新的员工角色
                EmpRoleVO empRoleVO = new EmpRoleVO();
                empRoleVO.setCharacterId(characterId);
                empRoleVO.setEmpId(empId);
                roleService.addEmpRole(empRoleVO);
            }
        }
        return "success";
    }

}
