package com.ht.dao.llb;

import com.ht.vo.*;

import java.util.List;

public interface IRoleDao {
    public void addRole(CharactersVO charactersVO);
    public int countRole();
    public List<CharactersVO> pageList(int page, int limit);
    public CharactersVO findById(Integer characterId);
    //删除
    public void deleteRole(CharactersVO charactersVO);

    public void editRole(Integer characterId,String characterName);

    //所有权限
    public List<ModuleVO> allModule();
    //查询权限
    public ModuleVO selModuleByModuleId(Integer moduleId);

    //查询角色权限
    public List<CharModuleVO> allCharModuleByCharacterId(Integer characterId);
    //保存角色权限
    public void saveCharModule(CharModuleVO charModuleVO);
    //删除角色权限
    public void delCharModule(Integer charModuleId);
    //删除该角色所有权限
    public void delAllCharModule(Integer characterId);


    //查询该员工的角色
    public EmpRoleVO selEmpRoleByEmpId(Integer empId);
    //查询该角色下的所有员工
    public List<EmpRoleVO> allEmpByCharacterId(Integer characterId);
    //添加员工角色
    public void addEmpRole(EmpRoleVO empRoleVO);
    //删除员工角色
    public void delEmpRoleByEmpId(Integer empId);
    //删除该角色下的所有员工
    public void delEmpRoleByCharacterId(Integer characterId);

}
