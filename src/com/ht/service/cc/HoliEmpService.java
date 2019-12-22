package com.ht.service.cc;

import com.ht.vo.HolidayVO;

import java.util.List;

public interface HoliEmpService {
    //分页查询
    public List selSpageEmpholi(int currPage, int pageSize, int mo);
    //条件查询
    public List<HolidayVO> pageListEmpholiWhere(int currPage, int pageSize, String empName, String month);
    //根据id查询页面
    public List selSpageEmpholibyid(int currPage, int pageSize, Integer empId, Integer sum, String month);


    //根据条件查询总行数
    public Integer seltotalEmpholiWhere(String empName, String month);

}
