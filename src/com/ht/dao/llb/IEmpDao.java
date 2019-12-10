package com.ht.dao.llb;

import com.ht.vo.EmpVO;

import java.util.List;

public interface IEmpDao {
    public void addEmp(EmpVO empVO);
    public EmpVO findEmpById(Integer empId);
    public EmpVO findEmpByPhone(String phone);
    public int countEmp();
    public List<EmpVO> allEmp();
    public List<EmpVO> pageList(int page, int limit);
    public void delEmp(String ids);
    public void updEmp(EmpVO empVO);
    public void updEmpStatus(Integer empId,Integer status);
    public void repass(Integer empId);
}
