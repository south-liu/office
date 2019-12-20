package com.ht.service.cc;

import com.ht.vo.HolidayStudent;
import com.ht.vo.StudentVO;

import java.util.List;

public interface HoliStuService {
    //分页查询
    public List selSpage(int currPage, int pageSize);
    //条件查询
    public List<HolidayStudent> pageListWhere(int currPage, int pageSize,String stuName,String month);
    //根据id查询页面
    public List selSpageholi(int currPage, int pageSize,Integer studentId,Integer sum);

    //根据条件查询总行数
    public Integer seltotalholWhere(String stuName,String month);

}
