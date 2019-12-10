package com.ht.service.zwj;

import com.ht.vo.StudentFloorVO;
import com.ht.vo.StudentHuorVO;

import java.util.List;
import java.util.Map;

public interface StudentHuorService {
    List<Map<String,Object>> allData(Integer page, Integer limit);

    List<StudentHuorVO> allData();

    List<StudentHuorVO> allDataByFloorId(Integer floorId);

    long getTotality();

    int add(StudentHuorVO studentHuorVO);

    StudentHuorVO getStudentHuorByHuorName(String huorName);

    List<StudentFloorVO> allFloorData();

    StudentHuorVO getStudentHuorById(int hourId);

    void updateStudentHuor(StudentHuorVO studentHuor);

    void deleteMultiStudentHuor(Integer[] hourId);

    void delete(int hourId);
}
