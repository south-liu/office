package com.ht.service.llb;

import com.ht.vo.DeptVO;

import java.util.List;

public interface IDeptService {
    public List<DeptVO> allDept();
    public List<DeptVO> allHeadDept();
    //根据id查询
    public DeptVO selById(Integer deptId);
    //添加
    public void addDept(DeptVO deptVO);
    //修改
    public void updateDept(DeptVO deptVO);
    //删除
    public void deleteDept(DeptVO deptVO);

    //删除子部门
    public void deleteChildDept(Integer pid);
}
