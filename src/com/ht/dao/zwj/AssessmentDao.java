package com.ht.dao.zwj;

import com.ht.vo.AssessmentVO;

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

    Map<String, Object> queryAssessmentInformation(int assessmentId, int studentId);

    long assessmentInformationTotality(int assessmentId);

    List<Map<String, Object>> queryAllData(int page, int limit, String options);

    long queryTotalNumberByOptions(String toString);
}
