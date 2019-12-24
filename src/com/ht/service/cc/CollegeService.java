package com.ht.service.cc;

import com.ht.vo.CollegeDeptVO;

import java.util.List;

public interface CollegeService {

    public List selSpage();
    //分页查询
    public List selSpage(int currPage, int pageSize);
    //查询总行数
    public Integer seltotalClege();
    //添加方法
    public void AddClege(CollegeDeptVO collegeDeptVO);
    //修改方法
    public void UpdClege(CollegeDeptVO collegeDeptVO);
    //删除方法
    public void delClege(String id);
}
