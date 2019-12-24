package com.ht.dao.zwj;

import com.ht.vo.AssessmentInformationVO;
import com.ht.vo.AssessmentVO;
import com.ht.vo.EmpAssessmentSuggestVO;

import java.util.List;
import java.util.Map;

public interface AssessmentDao {
    List<Map<String, Object>> queryAllData(int page, int limit);

    long queryAllTotalNumber();

    AssessmentVO queryAssessmentById(int assessmentId);

    long addAssessment(AssessmentVO assessment);

    void updateAssessment(AssessmentVO assessment);

    void deleteAssessment(AssessmentVO assessment, List<Map<String, Object>> studentList);

    float queryAvgScore(int assessmentId, String ids);

    List<Map<String, Object>> queryAssessmentInformation(int assessmentId, int studentId);

    long assessmentInformationTotality(int assessmentId);

    List<Map<String, Object>> queryAllData(int page, int limit, String options);

    long queryTotalNumberByOptions(String toString);

    long insertAssessmentInformation(AssessmentInformationVO assessmentInformationVO);

    List<Map<String, Object>> queryStudentByAssessmentId(Integer assessmentId);

    long insertEmpAssessmentSuggest(EmpAssessmentSuggestVO empAssessmentSuggest);

    List<Map<String, Object>> queryAssessmentDetailContent(int assessmentId, int studentId);

    Float queryAvgScoreByAssessmentId(Integer assessmentId);

    Map<String, Object> queryAssessmentSuggest(int assessmentId, int studentId);

    List<Map<String, Object>> queryUnfinishedAssessment();
}
