package com.ht.dao.llb.impl;

import com.ht.dao.BaseDao;
import com.ht.dao.llb.IStudentDao;
import com.ht.service.llb.IStudentService;
import com.ht.vo.EmpVO;
import com.ht.vo.StudentVO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl extends BaseDao implements IStudentDao {
    @Override
    public StudentVO findById(Integer studId) {
        return (StudentVO) findOneByHql("from StudentVO where studId ="+studId);
    }

    @Override
    public List<StudentVO> allStu() {
        return findAllByHql("from StudentVO");
    }

    @Override
    public List<StudentVO> pageList(int page, int limit) {
        return pageListBySql("select s.*,sc.className,h.huorName from student s left join studentClass sc on s.clazz = sc.classId left join studentHuor h on s.huor = h.hourId",page,limit);
    }

    @Override
    public int countStudent() {
        return totalRowByHql("select count(*) from StudentVO");
    }

    @Override
    public void addStu(StudentVO studentVO) {
        saveObject(studentVO);
    }

    @Override
    public void delStu(String ids) {
        executeSQL("delete from student where studId in ("+ids+")");
    }

    @Override
    public void updStu(StudentVO studentVO) {
        update(studentVO);
    }

    @Override
    public void repass(Integer stuId) {
        executeSQL("update student set password = '123456' where studId = "+stuId);
    }
}
