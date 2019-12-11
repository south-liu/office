package com.ht.dao.llb;

import com.ht.vo.EmpVO;

import java.util.List;

public interface IEmpDao {
    public void addEmp(EmpVO empVO);
    public EmpVO findEmpById(Integer empId);
    public EmpVO findEmpByPhone(String phone);

    public int countEmp();
    public int countEmpWhere(Integer deptId,String empName,String phone);

    public List<EmpVO> allEmp();

    public List<EmpVO> pageList(int page, int limit);
    public List<EmpVO> pageListWhere(int page, int limit,Integer deptId,String empName,String phone);

    public void delEmp(String ids);
    public void updEmp(EmpVO empVO);
    public void updEmpStatus(Integer empId,Integer status);
    public void repass(Integer empId);
}
