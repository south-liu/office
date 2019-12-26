package com.ht.service.llb.impl;

import com.ht.dao.llb.IStudentDao;
import com.ht.service.llb.IStudentService;
import com.ht.vo.StudentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Resource
    private IStudentDao studentDao;

    @Override
    public StudentVO findById(Integer studId) {
        return studentDao.findById(studId);
    }

    @Override
    public StudentVO findByPhone(String phone) {
        return studentDao.findByPhone(phone);
    }

    @Override
    public List<StudentVO> allStu() {
        return studentDao.allStu();
    }

    @Override
    public List<StudentVO> pageList(int page, int limit,Integer empId,String postName) {
        return studentDao.pageList(page,limit,empId,postName);
    }

    @Override
    public List<StudentVO> pageListWhere(int page, int limit,Integer empId,String postName, String stuName, String phone, Integer clazz, Integer huor) {
        return studentDao.pageListWhere(page,limit,empId,postName,stuName,phone,clazz,huor);
    }

    @Override
    public List pageListWhere(int page, int limit, String stuName, String phone) {
        return studentDao.pageListWhere(page,limit,stuName,phone);
    }

    @Override
    public int countStudent() {
        return studentDao.countStudent();
    }

    @Override
    public int countStuWhere(String stuName, String phone, Integer clazz, Integer huor) {
        return studentDao.countStuWhere(stuName,phone,clazz,huor);
    }

    @Override
    public void addStu(StudentVO studentVO) {
        studentDao.addStu(studentVO);
    }

    @Override
    public void delStu(String ids) {
        studentDao.delStu(ids);
    }

    @Override
    public void updStu(StudentVO studentVO) {
        studentDao.updStu(studentVO);
    }

    @Override
    public void repass(Integer stuId,String password) {
        studentDao.repass(stuId,password);
    }

    @Override
    public void updStuStatus(Integer stuId, Integer stat) {
        studentDao.updStuStatus(stuId,stat);
    }
}
