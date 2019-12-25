package com.ht.service.zwj.impl;

import com.ht.dao.zwj.StudentFallDao;
import com.ht.service.zwj.StudentFallService;
import com.ht.vo.StudentFallVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

// 学生届别管理service impl类
@Service
public class StudentFallServiceImpl implements StudentFallService {
    @Resource
    private StudentFallDao studentFallDao;

    // 分页查询列表
    @Override
    public List<StudentFallVO> allData(Integer page, Integer limit) {
        return studentFallDao.allData(page, limit);
    }

    // 通过id查询vo
    @Override
    public StudentFallVO getStudentFallById(int fallId) {
        return studentFallDao.getStudentFallById(fallId);
    }

    // 通过id删除
    @Override
    public void delete(int fallId) {
        StudentFallVO studentFallVO = getStudentFallById(fallId);
        studentFallDao.delete(studentFallVO);
    }

    @Override
    public int add(StudentFallVO studentFallVO) {
        return studentFallDao.add(studentFallVO);
    }

    @Override
    public StudentFallVO getStudentFallByLevel(String level) {
        return studentFallDao.getStudentFallByLevel(level);
    }

    @Override
    public void updateStudentFall(StudentFallVO studentFall) {
        studentFallDao.update(studentFall);
    }

    @Override
    public void deleteMultiStudentFall(Integer[] fallIds) {
        if (fallIds == null) {
            return;
        }
        for (Integer fallId : fallIds) {
            delete(fallId);
        }
    }

    @Override
    public long getTotality() {
        return studentFallDao.getTotality();
    }

    @Override
    public List<StudentFallVO> allData() {
        return studentFallDao.allData();
    }
}
