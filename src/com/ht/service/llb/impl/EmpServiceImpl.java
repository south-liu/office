package com.ht.service.llb.impl;

import com.ht.dao.llb.IEmpDao;
import com.ht.service.llb.IEmpService;
import com.ht.vo.EmpAssessmentTotalVO;
import com.ht.vo.EmpVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmpServiceImpl implements IEmpService {

    @Resource
    private IEmpDao empDao;

    @Override
    public void addEmp(EmpVO empVO) {
        empDao.addEmp(empVO);
    }

    @Override
    public EmpVO findEmpByPhone(String phone) {
        return empDao.findEmpByPhone(phone);
    }

    @Override
    public List<EmpVO> allEmp() {
        return empDao.allEmp();
    }

    @Override
    public List<EmpVO> pageList(int page, int limit) {
        return empDao.pageList(page,limit);
    }

    @Override
    public List<EmpVO> pageListWhere(int page, int limit, Integer deptId, String empName, String phone) {
        return empDao.pageListWhere(page,limit,deptId,empName,phone);
    }

    @Override
    public int countEmp() {
        return empDao.countEmp();
    }

    @Override
    public int countEmpWhere(Integer deptId, String empName, String phone) {
        return empDao.countEmpWhere(deptId,empName,phone);
    }

    @Override
    public void delEmp(String ids) {
        empDao.delEmp(ids);
    }

    @Override
    public void updEmp(EmpVO empVO) {
        empDao.updEmp(empVO);
    }

    @Override
    public void updEmpStatus(Integer empId,Integer status) {
        empDao.updEmpStatus(empId,status);
    }

    @Override
    public EmpVO findEmpById(Integer empId) {
        return empDao.findEmpById(empId);
    }

    @Override
    public void repass(Integer empId,String password) {
        empDao.repass(empId,password);
    }

    @Override
    public void addEmpAssessmentTotal(EmpAssessmentTotalVO empAssessmentTotalVO) {
        empDao.addEmpAssessmentTotal(empAssessmentTotalVO);
    }
}
