package com.ht.service.zwj;

import com.ht.vo.AssessmentInformationVO;
import com.ht.vo.AssessmentVO;
import com.ht.vo.EmpAssessmentSuggestVO;
import com.ht.vo.EvaluationVO;

import java.util.List;
import java.util.Map;

public interface AssessmentService {
    List<Map<String, Object>> queryAllData(int page, int limit);

    long queryAllTotalNumber();

    AssessmentVO queryAssessmentById(int assessmentId);

    long addAssessment(AssessmentVO assessment);

    void updateAssessment(AssessmentVO assessment);

    void deleteAssessment(int assessmentId);

    int changeAssessmentState(int assessmentId, int state);

    Float queryAvgScoreByAssessment(AssessmentVO assessment);

    List<Map<String, Object>> queryAssessmentInformation(int assessmentId, int studentId);

    long assessmentInformationTotality(int assessmentId);

    int changeAssessmentEndTime(int assessmentId, int time);

    List<Map<String, Object>> searchAssessmentByOptions(int page, int limit, Integer empId, String startTime, String endTime);

    long searchAssessmentTotalityByOptions(Integer empId, String startTime, String endTime);

    long insertAssessmentInformations(AssessmentInformationVO assessmentInformation, int[] allTheScore, List<EvaluationVO> evaluationStandardList);

    List<Map<String, Object>> queryStudentByAssessmentId(Integer assessmentId);

    long insertEmpAssessmentSuggest(EmpAssessmentSuggestVO empAssessmentSuggest);

    List<Map<String, Object>> queryAssessmentDetailContent(int assessmentId, int studentId);

    Map<String, Object> queryAssessmentSuggest(int assessmentId, int studentId);

    List<Map<String, Object>> queryUnfinishedAssessment();

    List<Map<String, Object>> queryEvaluationAvgScore(int assessmentId);
}
