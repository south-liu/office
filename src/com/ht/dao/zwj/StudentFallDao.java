package com.ht.dao.zwj;

import com.ht.vo.StudentFallVO;

import java.util.List;

// 学生届别管理
public interface StudentFallDao {
    List<StudentFallVO> allData(Integer page, Integer limit);

    StudentFallVO getStudentFallById(int fallId);

    void delete(StudentFallVO studentFallVO);

    int add(StudentFallVO studentFallVO);

    StudentFallVO getStudentFallByLevel(String level);

    void update(StudentFallVO studentFallVO);

    long getTotality();

    List<StudentFallVO> allData();

    List<StudentFallVO> queryStudentFallByLevelNotFallid(Integer fallId, String level);
}
