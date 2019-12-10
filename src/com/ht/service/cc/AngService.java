package com.ht.service.cc;

import com.ht.vo.*;

import java.util.List;

public interface AngService {
    //分页查询
    public List selSpage(int currPage, int pageSize);
    //查询总行数
    public Integer seltotalAng();
    //添加方法
    public void AddAng(WeekArrangeVO weekArrangeVO);
    //修改方法
    public void UpdAng(WeekArrangeVO weekArrangeVO);
    //删除方法
    public void delAng(String id);
    //查询所有员工
    public List<EmpVO> Allemp();
    //所有值班
    public WeekArrangeVO AllAng(int angId);
}
