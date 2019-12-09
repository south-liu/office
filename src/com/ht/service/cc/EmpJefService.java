package com.ht.service.cc;

import com.ht.vo.CollegeDeptVO;
import com.ht.vo.EducationVO;
import com.ht.vo.FamilyInfoVO;
import com.ht.vo.JobVO;

import java.util.List;

public interface EmpJefService {
    //分页查询
    public List selJobSpage(int currPage, int pageSize, int empid);
    //查询总行数
    public Integer seltotalJob();

    //查询总行数byEmpId
    public Integer seltotalJobByEmpId(Integer empId);

/*
    工作经历
*/
    //添加方法
    public void AddJob(JobVO jobVO);
    //修改方法
    public void UpdJob(JobVO jobVO);
    //删除方法
    public void delJob(String id);


    //分页查询
    public List selFamSpage(int currPage, int pageSize, int empid);
    //查询总行数
    public Integer seltotalFam();

    public Integer seltotalFamByEmpId(Integer empId);
    /*
        家庭联系信息
    */
    //添加方法
    public void AddFam(FamilyInfoVO familyInfoVO);
    //修改方法
    public void UpdFam(FamilyInfoVO familyInfoVO);
    //删除方法
    public void delFam(String id);

}
