package com.ht.dao.zwj;

import com.ht.vo.ProjectVO;
import com.ht.vo.StudentReplyScoreVO;

import java.util.List;
import java.util.Map;

public interface StudentReplyScoreDao {
    String currentTableName = "studentReplyScore";
    String currentTableId = "replyId";

    List<Map<String, Object>> allData(Integer page, Integer limit);

    long getTotality();

    int addStudentReplyScore(StudentReplyScoreVO studentReplyScore);

    StudentReplyScoreVO getStudentReplyScoreById(int replyId);

    void updateStudentReplyScore(StudentReplyScoreVO studentReplyScore);

    void deleteStudentReplyScore(StudentReplyScoreVO studentReplyScore);

    List<Map<String,Object>> findDataBySql(String sql);

    long findCountByOptions(String sql);

    List<ProjectVO> findGradedProjectByStudentId(Integer stuId);

    Map<String, Object> findProjectScoreByOptions(Integer stuId, Integer projectId);
}
