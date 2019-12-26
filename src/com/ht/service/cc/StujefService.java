package com.ht.service.cc;

import com.ht.vo.*;

import java.util.List;

public interface StujefService {
    //分页查询
    public List selEduSpage(int currPage, int pageSize, int stuId);
    //查询总行数
    public Integer seltotalStu();

    //查询总行数bystuId
    public Integer seltotalEduByStuId(Integer stuId);

    /*
        工作经历
    */
    //添加方法
    public void AddEdu(StudentEduVO studentEduVO);
    //修改方法
    public void UpdEdu(StudentEduVO StudentEduVO);
    //删除方法
    public void delEdu(String id);

    public StudentEduVO allstuedubyId(Integer eduId);


    //分页查询
    public List selFamSpage(int currPage, int pageSize, int stuId);
    //查询总行数
    public Integer seltotalFam();

    public Integer seltotalFamByStuId(Integer stuId);
    /*
        家庭联系信息
    */
    //添加方法
    public void AddFam(StudentFamilyVO studentFamily);
    //修改方法
    public void UpdFam(StudentFamilyVO studentFamilyVO);
    //删除方法
    public void delFam(String id);


    /*
        在校情况
    */

    //分页查询
    public List selZxSpage(int currPage, int pageSize, int stuId);
    //查询总行数
    public Integer seltotalZx();

    public Integer seltotalZxByStuId(Integer stuId);
    //添加方法
    public void AddZx(StudentHappeningVO studentHappeningVO);
    //修改方法
    public void UpdZx(StudentHappeningVO studentHappeningVO);
    //删除方法
    public void delZx(String id);

    public StudentHappeningVO happlist(int happenId);
}

