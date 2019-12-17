package com.ht.dao.llb.impl;

import com.ht.dao.BaseDao;
import com.ht.dao.llb.IRoleDao;
import com.ht.vo.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl extends BaseDao implements IRoleDao {
    @Override
    public void addRole(CharactersVO charactersVO) {
        saveObject(charactersVO);
    }

    @Override
    public int countRole() {
        return totalRowByHql("select count(*) from CharactersVO");
    }

    @Override
    public List<CharactersVO> pageList(int page, int limit) {
        return pageListByHql("from CharactersVO",page,limit);
    }

    @Override
    public CharactersVO findById(Integer characterId) {
        return (CharactersVO) findOneByHql("from CharactersVO where characterId = "+characterId);
    }

    @Override
    public void deleteRole(CharactersVO charactersVO) {
        delete(charactersVO);
    }

    @Override
    public void editRole(Integer characterId, String characterName) {
        executeSQL("update characters set characterName = '"+characterName+"' where characterId = "+characterId);
    }

    @Override
    public List<ModuleVO> allModule() {
        return findAllByHql("from ModuleVO");
    }

    @Override
    public ModuleVO selModuleByModuleId(Integer moduleId) {
        return (ModuleVO) findOneByHql("from ModuleVO where moduleId = "+moduleId);
    }

    @Override
    public List<CharModuleVO> allCharModuleByCharacterId(Integer characterId) {
        return findAllByHql("from CharModuleVO where characterId = "+characterId);
    }

    @Override
    public void saveCharModule(CharModuleVO charModuleVO) {
        saveObject(charModuleVO);
    }

    @Override
    public void delCharModule(Integer charModuleId) {
        executeSQL("delete from charModule where charModuleId = "+charModuleId);
    }

    @Override
    public void delAllCharModule(Integer characterId) {
        executeSQL("delete from charModule where characterId = "+characterId);
    }

    @Override
    public EmpRoleVO selEmpRoleByEmpId(Integer empId) {
        return (EmpRoleVO) findOneByHql("from EmpRoleVO where empId = "+ empId);
    }

    @Override
    public List<EmpRoleVO> allEmpByCharacterId(Integer characterId) {
        return findAllByHql("from EmpRoleVO where characterId = "+characterId);
    }

    @Override
    public void addEmpRole(EmpRoleVO empRoleVO) {
        saveObject(empRoleVO);
    }

    @Override
    public void delEmpRoleByEmpId(Integer empId) {
        executeSQL("delete from empRole where empId = "+empId);
    }

    @Override
    public void delEmpRoleByCharacterId(Integer characterId) {
        executeSQL("delete from empRole where characterId = "+characterId);
    }
}
