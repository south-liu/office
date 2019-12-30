package com.ht.service.ljy.impl;

import com.ht.dao.ljy.ljyDao;
import com.ht.service.ljy.hstudentService;
import com.ht.vo.HolidayStudent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JY on 2019/12/13.
 */
@Service
public class hstudentimpl extends ljyDao implements hstudentService {


    //    修改请假表
    @Override
    public void hs_update(HolidayStudent holidayStudent) {
        updateobject(holidayStudent);
    }

    @Override
    public void hs_apply_add(HolidayStudent holidayStudent) {
        addobject(holidayStudent);
    }

    //    查询我的申请界面
    @Override
    public List hs_apply_list(Integer studentId, int page, int limit) {
        return pagelistbysql("select hstu.*,stu.stuName from holidayStudent hstu left join student stu on hstu.studentId=stu.studId where hstu.studentId=" + studentId, page, limit);
    }

    //    查询我的申请
    @Override
    public int hs_apply_count(Integer studentId) {
        return selTotalRow("select count(*) from holidayStudent where studentId=" + studentId);
    }

    //    查询授课老师的
    @Override
    public Integer hs_assignee_teacher(int studId) {
        return (Integer) selOneValue("select emp.empId from student stu left join studentClass stucls on stu.clazz=stucls.classId left join emp on emp.empId=stucls.teacher where studId=" + studId);
    }

    //    查询班主任
    @Override
    public Integer hs_assignee_classTeacher(int studId) {
        return (Integer) selOneValue("select emp.empId from student stu left join studentClass stucls on stu.clazz=stucls.classId left join emp on emp.empId=stucls.classTeacher where studId=" + studId);
    }

    //    查询校长
    @Override
    public Integer hs_assignee_schoolmaster() {
        return (Integer) selOneValue("select empId from emp where postName='校长'");
    }

    //    根据ID查询表内容
    @Override
    public HolidayStudent hs_byid_list(Integer holidayId) {
        return (HolidayStudent) dataById(HolidayStudent.class, holidayId);
    }

//    根据ID查询学生的姓名
    @Override
    public String hs_assigneeName(String  empId) {
        return (String) selOneValue("select empName from emp where empId=" + empId);
    }

    //    根据ID查询表和学生姓名
    @Override
    public List hs_byid_lists(int holidayId) {
        return listbysql("select hs.*,stu.stuName from holidayStudent hs left join student stu on hs.studentId=stu.studId where hs.holidayId=" + holidayId);
    }

    @Override
    public int hs_byid_lists_count(int holidayId) {
        return 0;
    }
}
