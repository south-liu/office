package com.ht.service.cc;

import com.ht.vo.CollegeDeptVO;
import com.ht.vo.MajorVO;
import com.ht.vo.StudentFallVO;

import java.util.List;

public interface MajorService {
    //分页查询
    public List selSpage(int currPage, int pageSize);
    //查询总行数
    public Integer seltotalMajor();
    //添加方法
    public void AddMajor(MajorVO majorVO);
    //修改方法
    public void UpdMajor(MajorVO majorVO);
    //删除方法
    public void delMajor(String id);
    //查询系名称
    public List<CollegeDeptVO> AllCollege();
    //查询系名称
    public MajorVO AllMajor(int majorid);
}
