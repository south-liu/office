package com.ht.service.cc.impl;

import com.ht.dao.BaseDao;
import com.ht.service.cc.EmpJefService;
import com.ht.service.cc.StujefService;
import com.ht.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuJefServiceImpl extends BaseDao implements StujefService{
    @Override
    public List selEduSpage(int currPage, int pageSize, int stuId) {
        return pageListByHql("from StudentEduVO where stuId="+stuId,currPage,pageSize);
    }

    @Override
    public Integer seltotalStu() {
        return totalRowByHql("select count(*) from StudentEduVO");
    }

    @Override
    public Integer seltotalEduByStuId(Integer stuId) {
        return totalRowByHql("select count(*) from StudentEduVO where stuId = "+stuId);
    }

    @Override
    public void AddEdu(StudentEduVO studentEduVO) {
        saveObject(studentEduVO);
    }
    @Override
    public void UpdEdu(StudentEduVO studentEduVO) {
        update(studentEduVO);
    }
    @Override
    public void delEdu(String id) {
        executeSQL("delete from studentEdu where eduId ="+id);
    }




    //家庭背景
    @Override
    public List selFamSpage(int currPage, int pageSize, int stuId) {
        return pageListByHql("from StudentFamilyVO where stuId="+stuId,currPage,pageSize);
    }
    @Override
    public Integer seltotalFam() {
        return totalRowByHql("select count(*) from studentFamilyVO");
    }

    @Override
    public Integer seltotalFamByStuId(Integer stuId) {
        return totalRowByHql("select count(*) from StudentFamilyVO where stuId ="+stuId);
    }

    @Override
    public void AddFam(StudentFamilyVO studentFamilyVO) {
        saveObject(studentFamilyVO);
    }

    @Override
    public void UpdFam(StudentFamilyVO studentFamilyVO) {
        update(studentFamilyVO);
    }

    @Override
    public void delFam(String id) {
        executeSQL("delete from studentFamily where familyId ="+id);
    }


    //在校情况
    @Override
    public List selZxSpage(int currPage, int pageSize, int stuId) {
        return pageListBySql("select s.*,stu.stuName,e.empName from studentHappening s left join student stu on s.stuId=stu.studId left join emp e on s.empId=e.empId where stuId="+stuId,currPage,pageSize);
    }

    @Override
    public Integer seltotalZx() {
        return totalRowByHql("select count(*) from StudentHappeningVO");
    }

    @Override
    public Integer seltotalZxByStuId(Integer stuId) {
        return totalRowByHql("select count(*) from StudentHappeningVO where stuId ="+stuId);
    }

    @Override
    public void AddZx(StudentHappeningVO studentHappeningVO) {
        saveObject(studentHappeningVO);
    }

    @Override
    public void UpdZx(StudentHappeningVO studentHappeningVO) {
        update(studentHappeningVO);
    }

    @Override
    public void delZx(String id) {
        executeSQL("delete from studentHappening where happenId ="+id);
    }

    @Override
    public StudentHappeningVO happlist(int happenId) {
        return (StudentHappeningVO) selectOneByHql("from StudentHappeningVO where happenId ="+happenId);
    }
}
