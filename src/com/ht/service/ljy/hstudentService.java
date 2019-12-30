package com.ht.service.ljy;

import com.ht.vo.HolidayStudent;

import java.util.List;

/**
 * Created by JY on 2019/12/13.
 */
public interface hstudentService {

    public void hs_update(HolidayStudent holidayStudent);


    //    填写请假单
    public void hs_apply_add(HolidayStudent holidayStudent);

    //    查询我的申请
    public List<HolidayStudent> hs_apply_list(Integer studentId, int page, int limit);

    //    查询我的申请数量
    public int hs_apply_count(Integer studnetId);

    //查询执行人授课老师
    public Integer hs_assignee_teacher(int studId);

    //    查询执行人班主任
    public Integer hs_assignee_classTeacher(int studId);

    //查询校长
    public Integer hs_assignee_schoolmaster();

    //根据ID查询请假表和学生名字的内容
    public List hs_byid_lists(int holidayId);

    //根据ID查询个数
    public int hs_byid_lists_count(int holidayId);

//    根据ID查询请假表内容
    public HolidayStudent hs_byid_list(Integer holidayId);


//    根据ID查询到名字
    public String hs_assigneeName(String empId);


}
