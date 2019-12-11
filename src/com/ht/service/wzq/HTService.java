package com.ht.service.wzq;

import com.ht.vo.*;

import java.util.List;

/**
 * Created by shkstart on 2019/12/4.
 */
public interface HTService {
    //不带分页
    public List selcemester();
    //学期管理分页查询（page、limit为layui中页面传回的属性名）
    public List selcemester(int page, int limit);
    //学期管理查询总行数
    public Integer selcount();
    //添加学期
    public void addterms(TermVO term);
    //删除学期
    public void updterm(Integer termId);
    //修改学期
    public void delterm(TermVO term);


    //不带分页
    public List selfloor();
    //楼栋管理分页查询（page、limit为layui中页面传回的属性名）
    public List selfloor(int page, int limit);
    //楼栋管理查询总行数
    public Integer selcountfloor();
    //添加楼栋
    public void addfloor(StudentFloorVO studentFloor);
    //删除楼栋
    public void updfloor(Integer floorId);
    //修改楼栋
    public void delfloor(StudentFloorVO studentFloor);


    //不带分页
    public List selwee();
    //周报管理分页查询（page、limit为layui中页面传回的属性名）
    public List selwee(int page, int limit);
    //查询周报管理总行数
    public Integer selcountwee();
    //查询部门
    public List<DeptVO> seldept();
    //添加周报
    public void addwee(WeeklyVO weekly);
    //连接查询（通过员工ID查员工姓名）
    public List seldeptwee(String empName, int page, int limit);
    //查询通过员工ID查员工姓名的总行数
    public Integer selcountemp(String empName);


    //不带分页
    public List selscore();
    //查询学生成绩（首次查询）
    public List selscore(int page, int limit);
    //查询总行数
    public Integer selcountscore();
    //查询学期（下拉框）
    public List<TermVO> selxueqi();
    //查询班级（下拉框）
    public List<StudentClassVO> selclass();
    //查询课程名称（下拉框）
    public List<CourseVO> selcourse();

    public List selcoursetype();
}
