package com.ht.service.llb;

import com.ht.vo.EmpAssessmentTotalVO;
import com.ht.vo.EmpVO;

import java.util.List;

public interface IEmpService {
    public void addEmp(EmpVO empVO);
    public EmpVO findEmpByPhone(String phone);
    public List<EmpVO> allEmp();
    public List<EmpVO> pageList(int page, int limit);

    public List<EmpVO> pageListWhere(int page, int limit,Integer deptId,String empName,String phone);

    public int countEmp();
    public int countEmpWhere(Integer deptId,String empName,String phone);

    public void delEmp(String ids);
    public void updEmp(EmpVO empVO);
    public void updEmpStatus(Integer empId,Integer status);
    public EmpVO findEmpById(Integer empId);
    public void repass(Integer empId,String password);

    //添加员工考评总表
    public void addEmpAssessmentTotal(EmpAssessmentTotalVO empAssessmentTotalVO);
}
