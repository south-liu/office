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
    public List<StudentVO> pageList(int page, int limit) {
        return studentDao.pageList(page,limit);
    }

    @Override
    public int countStudent() {
        return studentDao.countStudent();
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
    public void repass(Integer stuId) {
        studentDao.repass(stuId);
    }
}
