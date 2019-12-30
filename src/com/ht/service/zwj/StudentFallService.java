package com.ht.service.zwj;

import com.ht.vo.StudentFallVO;

import java.util.List;

public interface StudentFallService {
    List<StudentFallVO> allData(Integer page, Integer limit);

    StudentFallVO getStudentFallById(int fallId);

    void delete(int fallId);

    int add(StudentFallVO studentFallVO);

    StudentFallVO getStudentFallByLevel(String level);

    void updateStudentFall(StudentFallVO studentFall);

    void deleteMultiStudentFall(Integer[] fallIds);

    long getTotality();

    List<StudentFallVO> allData();

    /**
     * 除去界别ID通过界别名称查询
     *
     * @param studentFall
     * @return
     */
    List<StudentFallVO> queryStudentFallByLevelNotFallid(StudentFallVO studentFall);
}
