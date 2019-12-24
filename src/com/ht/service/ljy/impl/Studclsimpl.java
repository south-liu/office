package com.ht.service.ljy.impl;
import com.ht.dao.ljy.ljyDao;
import com.ht.service.ljy.studentclassService;
import com.ht.vo.StudentClassVO;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by JY on 2019/12/6.
 */

@Service
public class Studclsimpl extends ljyDao implements studentclassService {


    @Override
    public List studentclass_list() {
        return listbysql("select * from studentClass ");
    }

    @Override
    public List studentclass_list(int page, int limit) {
//        return pagelistbysql("select * from studentClass", page, limit);
        return pagelistbysql("select stcls.*,e.empName,e.postName,clst.classTypeName,stufal.`level`, mjr.majorName,dept.collegeDeptName  from studentClass stcls left join emp e on stcls.teacher=e.empId left join classType clst on stcls.classType=clst.classTypeId left join studentFall stufal on stcls.falled=stufal.fallId left join major mjr on stcls.majorId=mjr.majorId left join collegeDept  dept on stcls.deptId=dept.collegeDeptId", page, limit);
    }

    @Override
    public int studentclass_count() {
        return selTotalRow("select count(*) from studentClass");
    }

    @Override
    public void studentclass_delete(StudentClassVO studentClassVO) {
        deleteobject(studentClassVO);
    }

    @Override
    public void studentclass_add(StudentClassVO studentClassVO) {
        addobject(studentClassVO);
    }

    @Override
    public void student_update(StudentClassVO studentClassVO) {
        updateobject(studentClassVO);
    }


    @Override
    public StudentClassVO studentclassbyid(int classId) {
        return (StudentClassVO) selectOneByHql("from StudentClassVO where classId=" + classId);
    }

    @Override
    public int studentclasschoose_count(int falled) {
        return selTotalRow("select count(*)  from studentClass stcls left join emp e on stcls.teacher=e.empId left join classType clst on stcls.classType=clst.classTypeId left join studentFall stufal on stcls.falled=stufal.fallId left join major mjr on stcls.majorId=mjr.majorId left join dept dept on stcls.deptId=dept.deptId  where falled="+falled);
    }

    @Override
    public List studentclass_choose(int falled,int page,int limit) {
        return pagelistbysql("select stcls.*,e.empName,e.postName,clst.classTypeName,stufal.`level`, mjr.majorName,dept.deptName  from studentClass stcls left join emp e on stcls.teacher=e.empId left join classType clst on stcls.classType=clst.classTypeId left join studentFall stufal on stcls.falled=stufal.fallId left join major mjr on stcls.majorId=mjr.majorId left join dept dept on stcls.deptId=dept.deptId  where falled="+falled,page,limit);
    }
}
