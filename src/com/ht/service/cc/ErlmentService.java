package com.ht.service.cc;

import com.ht.vo.*;

import java.util.List;

public interface ErlmentService {
    //分页查询
    public List selSpage(int currPage, int pageSize);
    //查询总行数
    public Integer seltotalAng();
    //查询所有员工
    public List<EmpVO> seltAllemp();
    //查询所有专业
    public List<MajorVO> seltAllmajor();

    public EnrollmentVO seltAllenrollment( int enrollmentId);
    //查询所有班级类别
    public List<ClassTypeVO> seltAllclass();

    //添加方法
    public void AddErlment(EnrollmentVO enrollmentVO);
    //修改方法
    public void UpdErlment(EnrollmentVO enrollmentVO);
    //删除方法
    public void delErlment(String id);
    //修改学生状态
    public void updErlmStats(Integer id,Integer stats);

    //修改学生状态
    public void updErlmdate(Integer id,String date);

    public void AddStudent(StudentVO studentVO);

    public void updErlmamount(Integer id,float amount);

    public void updErlreviewer(Integer id,String reviewer,String data);

    public void updErlenrollMoney(Integer id,float enrollMoney);

}
