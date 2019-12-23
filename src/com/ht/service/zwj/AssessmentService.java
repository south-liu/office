package com.ht.service.zwj;

import com.ht.vo.AssessmentInformation;
import com.ht.vo.AssessmentVO;

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

    float queryAvgScoreByAssessment(AssessmentVO assessment);

    Map<String, Object> queryAssessmentInformation(int assessmentId, int studentId);

    long assessmentInformationTotality(int assessmentId);

    int changeAssessmentEndTime(int assessmentId, int time);

    List<Map<String, Object>> searchAssessmentByOptions(int page, int limit, Integer empId, String startTime, String endTime);

    long searchAssessmentTotalityByOptions(Integer empId, String startTime, String endTime);

    long insertAssessmentInformation(AssessmentInformation assessmentInformation);

    List<Map<String, Object>> queryStudentByAssessmentId(Integer assessmentId);
}
