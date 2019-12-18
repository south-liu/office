package com.ht.service.wzq;

import com.ht.vo.EmpVO;
import com.ht.vo.HolidayVO;

import java.util.List;

/**
 * Created by shkstart on 2019/12/13.
 */
public interface TPService {

    //不带分页查询
    public List selholiday();

    //查看我的请假申请
    public List selholiday(Integer empId, int page, int limit);

    //查询我的申请总行数
    public Integer selcountholiday(Integer empId);

    //添加请假申请
    public void addholiday(HolidayVO holiday);

    //通过empId查询empName
    public EmpVO selempName(Integer empId);

    //通过empId查询上级办理人
    public List seldeptchairman(Integer empId);

    //根据单据ID查询对象
    public HolidayVO selholiday(Integer holidayId);

    //保存审核信息（修改申请）
    public void updholiday(HolidayVO holiday);



    //查询请假
    HolidayVO selHolidayById(Integer holidayId);

}
