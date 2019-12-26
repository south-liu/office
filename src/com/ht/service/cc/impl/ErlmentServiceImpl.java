package com.ht.service.cc.impl;

import com.ht.dao.BaseDao;
import com.ht.service.cc.ErlmentService;
import com.ht.service.cc.MajorService;
import com.ht.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ErlmentServiceImpl extends BaseDao implements ErlmentService {
    @Resource
    private MajorService majorService;
    @Override
    public List selSpage(int currPage, int pageSize) {
        return pageListBySql("select c.classTypeName,m.majorName,em.*,e.empName from ((enrollment em left join emp e on e.empId=em.empId)left join  major m on m.majorId = em.majorId)left join classType c on c.classTypeId = em.studType",currPage,pageSize);
    }

    @Override
    public Integer seltotalAng() {
        return totalRowByHql("select count(*) from EnrollmentVO");
    }

    @Override
    public List<EmpVO> seltAllemp() {
        return findAllByHql("from EmpVO");
    }

    @Override
    public List<MajorVO> seltAllmajor() {
        return majorService.allMajor();
    }

    @Override
    public EnrollmentVO seltAllenrollment(int enrollmentId) {
        return (EnrollmentVO) selectOneByHql("from EnrollmentVO where enrollmentId ="+enrollmentId);
    }

    @Override
    public List<ClassTypeVO> seltAllclass() {
        return findAllByHql("from ClassTypeVO");
    }

    @Override
    public void AddErlment(EnrollmentVO enrollmentVO) {
        saveObject(enrollmentVO);
    }

    @Override
    public void UpdErlment(EnrollmentVO enrollmentVO) {
        update(enrollmentVO);
    }

    @Override
    public void delErlment(String id) {
        executeSQL("delete from enrollment where enrollmentId="+id);
    }

    @Override
    public void updErlmStats(Integer id, Integer stats) {
        executeSQL("update enrollment  set status="+stats+" where enrollmentId="+id);
    }

    @Override
    public void updErlmdate(Integer id, String date) {
        executeSQL("update enrollment set startTime='"+date+"'"+" where enrollmentId="+id);
    }

    @Override
    public void AddStudent(StudentVO studentVO) {
        saveObject(studentVO);
    }

    @Override
    public void updErlmamount(Integer id, float amount) {
        executeSQL("update enrollment set amount="+amount+",reviewStatus=1 where enrollmentId="+id);
    }

    @Override
    public void updErlreviewer(Integer id, String reviewer,String data) {
        executeSQL("update enrollment set reviewer='"+reviewer+"',reviewStatus=2,reviewerTime='"+data+"' where enrollmentId="+id);

    }

    @Override
    public void updErlenrollMoney(Integer id, float enrollMoney) {
        executeSQL("update enrollment set enrollMoney="+enrollMoney+" where enrollmentId="+id);

    }

}
