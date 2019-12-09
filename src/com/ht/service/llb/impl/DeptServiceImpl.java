package com.ht.service.llb.impl;

import com.ht.dao.llb.IDeptDao;
import com.ht.service.llb.IDeptService;
import com.ht.vo.DeptVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements IDeptService {

    @Resource
    private IDeptDao deptDao;

    @Override
    public List<DeptVO> allDept() {
        return deptDao.allDept();
    }

    @Override
    public List<DeptVO> allHeadDept() {
        return deptDao.allHeadDept();
    }

    @Override
    public DeptVO selById(Integer deptId) {
        return deptDao.selById(deptId);
    }

    @Override
    public void addDept(DeptVO deptVO) {
        deptDao.addDept(deptVO);
    }

    @Override
    public void updateDept(DeptVO deptVO) {
        deptDao.updateDept(deptVO);
    }

    @Override
    public void deleteDept(DeptVO deptVO) {
        deptDao.deleteDept(deptVO);
    }

    @Override
    public void deleteChildDept(Integer pid) {
        deptDao.deleteChildDept(pid);
    }
}
