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
    public StudentVO findByPhone(String phone) {
        return (StudentVO) findOneByHql("from StudentVO where phone = '"+phone+"'");
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
    public List<StudentVO> pageListWhere(int page, int limit, String stuName, String phone, Integer clazz, Integer huor) {
        String sql = "select s.*,sc.className,h.huorName from student s left join studentClass sc on s.clazz = sc.classId left join studentHuor h on s.huor = h.hourId where 1=1 ";
        if (stuName!=null && !"".equals(stuName)){
            sql+=" and s.stuName like '%"+stuName+"%'";
        }
        if (phone!=null && !"".equals(phone)){
            sql+=" and s.phone like '%"+phone+"%'";
        }
        if (clazz!=null && !"".equals(clazz)){
            sql+=" and s.clazz = "+clazz;
        }
        if (huor!=null && !"".equals(huor)){
            sql+=" and s.huor = "+huor;
        }
        return pageListBySql(sql,page,limit);
    }

    @Override
    public int countStudent() {
        return totalRowByHql("select count(*) from StudentVO");
    }

    @Override
    public int countStuWhere(String stuName, String phone, Integer clazz, Integer huor) {
        String sql = "select count(*) from student s where 1=1";
        if (stuName!=null && !"".equals(stuName)){
            sql+=" and s.stuName like '%"+stuName+"%'";
        }
        if (phone!=null && !"".equals(phone)){
            sql+=" and s.phone like '%"+phone+"%'";
        }
        if (clazz!=null && !"".equals(clazz)){
            sql+=" and s.clazz = "+clazz;
        }
        if (huor!=null && !"".equals(huor)){
            sql+=" and s.huor = "+huor;
        }
        return totalRowBySql(sql);
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
    public void repass(Integer stuId,String password) {
        executeSQL("update student set password = '"+password+"' where studId = "+stuId);
    }

    @Override
    public void updStuStatus(Integer stuId,Integer stat) {
        executeSQL("update student set stat = "+stat+" where studId = "+stuId);
    }
}
