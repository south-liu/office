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


    //查询我的周报
    public List selmyweekly(Integer empId, int page, int limit);
    //查询我的周报总行数
    public Integer selcountmyweekly(Integer empId);
    //去修改周报（按周报ID查询）
    public WeeklyVO selmyupd(Integer weeklyId);
    //修改我的周报
    public void updmyweekly(WeeklyVO weeklyVO);


    //不带分页
    public List selwee();
    //周报管理分页查询（page、limit为layui中页面传回的属性名）
    public List selwee(int page, int limit);
    //查询周报管理总行数
    public Integer selcountwee();
    //查询部门(下拉框)
    public List<DeptVO> seldept();
    //添加周报
    public void addwee(WeeklyVO weekly);
    //删除周报
    public void delweekly(Integer weeklyId);


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


    //查询课程类别
    public List selcoursetype(int page, int limit);
    //查询总行数
    public Integer selcountcoursetype();
    //添加课程类别
    public void addtype(CourseTypeVO courseType);
    //删除课程类别
    public void deltype(Integer courseTypeId);
    //修改课程类别
    public void updtype(CourseTypeVO courseTypeVO);



    //查询课程表
    public List selcourse(int page, int litmit);
    //查询课程表总行数
    public Integer selcountcourse();
    //查询课程类别（下拉框）
    public List<CourseTypeVO> selscourseType();
    //添加课程
    public void addcourse(CourseVO course);
    //删除课程
    public void delcourse(Integer courseId);
    //修改课程
    public void updcourse(CourseVO course);
    //通过ID查询需要修改的数据
    public CourseVO selcourseid(Integer courseId);



    public List selcoursetype();
    //查询指定学生的成绩
    public StudentScoreVO selstudentscore(Integer scoreId);
    //分页查询指定学生成绩
    public List selstudentscore(int page, int limit, Integer stuId);
    //查询制定学生成绩的总行数
    public Integer selcountstudentscore(Integer stuId);
    //添加学生成绩
    public void addstudentscore(StudentScoreVO studentScore);
    //点击编辑按钮修改学生成绩
    public void updstudentscore(StudentScoreVO studentScore);
    //删除学生成绩
    public void delstudentscore(Integer scoreId);



    //不带分页
    public List seladuitmodel();
    //分页查询考核指标
    public List seladuitmodel(int page, int limit);
    //查询考核指标总行数
    public Integer selcountaduitmodel();
    //添加考核指标
    public void addaduitmodel(AduitModelVO aduitModel);
    //删除考核指标
    public void deladuitmodel(Integer aduitModelId);


    //不带分页
    public List seladuitlog();
    //分页查询员工考核
    public List seladuitlog(int page, int limit);
    //查询员工考核总行数
    public Integer selcountaduitlog();
    //查询考核指标（下拉框）
    public List<AduitModelVO> seladuitm();
    //查询员工（下拉框）
    public List<EmpVO> selemp();
    //添加员工考核
    public void addaduitlog(AduitLogVO aduitLog);
    //删除员工考核
    public void deladuitlog(Integer aduitLogId);
    //搜索员工考核
    public List searchaduitlog(String sql, int page, int limit);
    //查询搜索总行数
    public Integer selcountad(String sql);




}
