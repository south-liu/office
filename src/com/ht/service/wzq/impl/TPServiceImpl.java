package com.ht.service.wzq.impl;

import com.ht.dao.wzq.BaseDao;
import com.ht.service.wzq.TPService;
import com.ht.vo.EmpVO;
import com.ht.vo.HolidayVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shkstart on 2019/12/13.
 */
@Service
public class TPServiceImpl extends BaseDao implements TPService {

    @Override
    public List selholiday() {
        return listBySql("select * from holiday");
    }

    @Override
    public List selholiday(Integer empId, int page, int limit) {
        return pageBySql("select h.*, e.empName from holiday h left join emp e on h.empId = e.empId where h.empId =" + empId, page, limit);
    }

    @Override
    public Integer selcountholiday(Integer empId) {
        return selTotalRow("select count(*) from holiday h left join emp e on h.empId = e.empId where h.empId =" + empId);
    }

    @Override
    public void addholiday(HolidayVO holiday) {
        addObject(holiday);
    }

    @Override
    public EmpVO selempName(Integer empId) {
        return (EmpVO) getObject(EmpVO.class, empId);
    }

    @Override
    public List seldeptchairman(Integer empId) {
        return listBySql2("select  d.chairman from emp e left join dept d on e.deptId = d.deptId where e.empId =" + empId);
    }

    @Override
    public HolidayVO selholiday(Integer holidayId) {
        return (HolidayVO) getObject(HolidayVO.class, holidayId);
    }

    @Override
    public void updholiday(HolidayVO holiday) {
        updObject(holiday);
    }

    @Override
    public HolidayVO selHolidayById(Integer holidayId) {
        return (HolidayVO) getObject(HolidayVO.class,holidayId);
    }

}
