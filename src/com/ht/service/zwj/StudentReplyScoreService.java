package com.ht.service.zwj;

import com.ht.vo.StudentReplyScoreVO;

import java.util.List;
import java.util.Map;

public interface StudentReplyScoreService {
    List<Map<String, Object>> allData(Integer page, Integer limit);

    long getTotality();

    int addStudentReplyScore(StudentReplyScoreVO studentReplyScore);

    StudentReplyScoreVO getStudentReplyScoreById(int replyId);

    void deleteStudentReplyScore(int replyId);

    void updateStudentReplyScore(StudentReplyScoreVO studentReplyScore);

    void deleteMultiStudentReplyScore(Integer[] replyIds);

    List<Map<String, Object>> findStudentReplyScoreByOptions(int projectId, int studentClassId, int empId, int page, int limit);

    int findCountByOptions(int projectId, int studentClassId, int empId);
}
