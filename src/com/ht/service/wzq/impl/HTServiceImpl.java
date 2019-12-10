package com.ht.service.wzq.impl;

import com.ht.dao.wzq.BaseDao;
import com.ht.service.wzq.HTService;
import com.ht.vo.*;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by shkstart on 2019/12/4.
 */
@Service
public class HTServiceImpl extends BaseDao implements HTService{



    @Override
    public List selcemester() {
        return listBySql2("select * from term");
    }

    @Override
    public List selcemester(int page, int limit) {
        return pageBySql("select * from term", page, limit);
    }

    @Override
    public Integer selcount() {
        return selTotalRow("select count(*) from term");
    }

    @Override
    public void addterms(TermVO term) {
        addObject(term);
    }

    @Override
    public void updterm(Integer termId) {
        executeSQL("delete from term where termId in("+termId+")");
    }

    @Override
    public void delterm(TermVO term) {
        updObject(term);
    }




    @Override
    public List selfloor() {
        return listBySql2("select * from studentFloor");
    }

    @Override
    public List selfloor(int page, int limit) {
        return pageBySql("select * from studentFloor", page, limit);
    }

    @Override
    public Integer selcountfloor() {
        return selTotalRow("select count(*) from studentFloor");
    }

    @Override
    public void addfloor(StudentFloorVO studentFloor) {
        addObject(studentFloor);
    }

    @Override
    public void updfloor(Integer floorId) {
        executeSQL("delete from studentFloor where floorId in(" + floorId + ")");
    }

    @Override
    public void delfloor(StudentFloorVO studentFloor) {
        updObject(studentFloor);
    }




    @Override
    public List selwee() {
        return listBySql2("select * from weekly");
    }

    @Override
    public List selwee(int page, int limit) {
        return pageBySql("select w.*, e.empName from weekly w left join emp e on w.empId = e.empId", page, limit);
    }

    @Override
    public Integer selcountwee() {
        return selTotalRow("select count(*) from weekly");
    }

    @Override
    public List<DeptVO> seldept() {
        return listByHql("from DeptVO");  //使用hql对象
    }

    @Override
    public void addwee(WeeklyVO weekly) {
        addObject(weekly);
    }

    @Override
    public List seldeptwee(String empName, int page, int limit) {
        return pageBySql("select w.*, e.empName from weekly w left join emp e on w.empId = e.empId where e.empName = "+ empName +"", page, limit);
    }

    @Override
    public Integer selcountemp(String empName) {
        return selTotalRow("select count(*) from weekly w left join emp e on w.empId = e.empId where e.empName = " + empName+"");
    }




    @Override
    public List selscore() {
        return listBySql2("select * from studentScore");
    }

    @Override
    public List selscore(int page, int limit) {
        return pageBySql("select s.*, t.stuName, c.courseName, m.empName from studentScore s left join student t on s.stuId = t.studId left join course c on s.courseId = c.courseId left join term e on s.termId = e.termId left join emp m on s.empId = m.empId", page, limit);
    }

    @Override
    public Integer selcountscore() {
        return selTotalRow("select count(*) from studentScore");
    }

    @Override
    public List<TermVO> selxueqi() {
        return listByHql("from TermVO");
    }

    @Override
    public List<StudentClassVO> selclass() {
        return listByHql("from StudentClassVO");
    }

    @Override
    public List<CourseVO> selcourse() {
        return listByHql("from CourseVO");
    }




    @Override
    public List selcoursetype(int page, int limit) {
        return pageBySql("select * from courseType", page, limit);
    }

    @Override
    public Integer selcountcoursetype() {
        return selTotalRow("select count(*) from courseType");
    }

    @Override
    public void addtype(CourseTypeVO courseType) {
        addObject(courseType);
    }

    @Override
    public void deltype(Integer courseTypeId) {
        executeSQL("delete from courseType where courseTypeId =" + courseTypeId);
    }

    @Override
    public void updtype(CourseTypeVO courseTypeVO) {
        updObject(courseTypeVO);
    }




    @Override
    public List selcourse(int page, int litmit) {
        return pageBySql("select c.*, t.courseTypeName from course c left join courseType t on c.courseTypeId = t.courseTypeId", page, litmit);
    }

    @Override
    public Integer selcountcourse() {
        return selTotalRow("select count(*) from course");
    }

    @Override
    public List<CourseTypeVO> selscourseType() {
        return listByHql("from CourseTypeVO");
    }

    @Override
    public void addcourse(CourseVO course) {
        addObject(course);
    }

    @Override
    public void delcourse(Integer courseId) {
        executeSQL("delete from course where courseId =" + courseId);
    }

    @Override
    public void updcourse(CourseVO course) {
        updObject(course);
    }

    @Override
    public CourseVO selcourseid(Integer courseId) {
        return (CourseVO)getObject(CourseVO.class, courseId);
    }
}
