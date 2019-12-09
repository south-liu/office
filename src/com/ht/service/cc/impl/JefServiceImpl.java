package com.ht.service.cc.impl;

import com.ht.dao.BaseDao;
import com.ht.service.cc.EmpJefService;
import com.ht.vo.EducationVO;
import com.ht.vo.FamilyInfoVO;
import com.ht.vo.JobVO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class JefServiceImpl extends BaseDao implements EmpJefService {

    @Override
    public List selJobSpage(int currPage, int pageSize,int empid) {
        return pageListByHql("from JobVO where empid="+empid,currPage,pageSize);
    }

    @Override
    public Integer seltotalJob() {
        return totalRowByHql("select count(*) from JobVO");
    }

    @Override
    public Integer seltotalJobByEmpId(Integer empId) {
        return totalRowByHql("select count(*) from JobVO where empId = "+empId);
    }

    @Override
    public void AddJob(JobVO jobVO) {
        saveObject(jobVO);
    }

    @Override
    public void UpdJob(JobVO jobVO) {
        update(jobVO);
    }
    @Override
    public void delJob(String id) {
        executeSQL("delete from job where JobId ="+id);
    }




    //家庭背景
    @Override
    public List selFamSpage(int currPage, int pageSize, int empid) {
        return pageListByHql("from FamilyInfoVO where empid="+empid,currPage,pageSize);
    }
    @Override
    public Integer seltotalFam() {
        return totalRowByHql("select count(*) from FamilyInfoVO");
    }

    @Override
    public Integer seltotalFamByEmpId(Integer empId) {
        return totalRowByHql("select count(*) from FamilyInfoVO where empId = "+empId);
    }

    @Override
    public void AddFam(FamilyInfoVO familyInfoVO) {
        saveObject(familyInfoVO);
    }

    @Override
    public void UpdFam(FamilyInfoVO familyInfoVO) {
        update(familyInfoVO);
    }

    @Override
    public void delFam(String id) {
        executeSQL("delete from familyInfo where familyId ="+id);
    }
}
