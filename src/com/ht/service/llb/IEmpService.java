package com.ht.service.llb;

import com.ht.vo.EmpVO;

import java.util.List;

public interface IEmpService {
    public void addEmp(EmpVO empVO);
    public EmpVO findEmpByPhone(String phone);
    public List<EmpVO> allEmp();
    public List<EmpVO> pageList(int page, int limit);
    public int countEmp();
    public void delEmp(String ids);
    public void updEmp(EmpVO empVO);
    public void updEmpStatus(EmpVO empVO);
    public EmpVO findEmpById(Integer empId);
}
