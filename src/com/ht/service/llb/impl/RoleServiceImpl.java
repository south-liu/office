package com.ht.service.llb.impl;

import com.ht.dao.llb.IRoleDao;
import com.ht.service.llb.IRoleService;
import com.ht.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private IRoleDao roleDao;

    @Override
    public void addRole(CharactersVO charactersVO) {
        roleDao.addRole(charactersVO);
    }

    @Override
    public int countRole() {
        return roleDao.countRole();
    }

    @Override
    public List<CharactersVO> pageList(int page, int limit) {
        return roleDao.pageList(page,limit);
    }

    @Override
    public CharactersVO findById(Integer characterId) {
        return roleDao.findById(characterId);
    }

    @Override
    public void deleteRole(CharactersVO charactersVO) {
        roleDao.deleteRole(charactersVO);
    }

    @Override
    public void editRole(Integer characterId, String characterName) {
        roleDao.editRole(characterId,characterName);
    }

    @Override
    public List<ModuleVO> allModule() {
        return roleDao.allModule();
    }

    @Override
    public ModuleVO selModuleByModuleId(Integer moduleId) {
        return roleDao.selModuleByModuleId(moduleId);
    }

    @Override
    public List<CharModuleVO> allCharModuleByCharacterId(Integer characterId) {
        return roleDao.allCharModuleByCharacterId(characterId);
    }

    @Override
    public void saveCharModule(CharModuleVO charModuleVO) {
        roleDao.saveCharModule(charModuleVO);
    }

    @Override
    public void delCharModule(Integer charModuleId) {
        roleDao.delCharModule(charModuleId);
    }

    @Override
    public void delAllCharModule(Integer characterId) {
        roleDao.delAllCharModule(characterId);
    }

    @Override
    public EmpRoleVO selEmpRoleByEmpId(Integer empId) {
        return roleDao.selEmpRoleByEmpId(empId);
    }

    @Override
    public List<EmpRoleVO> allEmpByCharacterId(Integer characterId) {
        return roleDao.allEmpByCharacterId(characterId);
    }

    @Override
    public void addEmpRole(EmpRoleVO empRoleVO) {
        roleDao.addEmpRole(empRoleVO);
    }

    @Override
    public void delEmpRoleByEmpId(Integer empId) {
        roleDao.delEmpRoleByEmpId(empId);
    }

    @Override
    public void delEmpRoleByCharacterId(Integer characterId) {
        roleDao.delEmpRoleByCharacterId(characterId);
    }
}
