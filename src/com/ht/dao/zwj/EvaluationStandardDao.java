package com.ht.dao.zwj;

import com.ht.vo.EvaluationVO;

import java.util.List;

public interface EvaluationStandardDao {
    List<EvaluationVO> queryAllData(int page, int limit);

    long queryAllTotalNumber();

    EvaluationVO queryEvaluationStandardById(int evaluationId);

    long addEvaluationStandard(EvaluationVO evaluation);

    EvaluationVO queryEvaluationStandardByName(String evaluationName);

    void updateEvaluationStandard(EvaluationVO evaluation);

    void deleteEvaluationStandard(EvaluationVO evaluation);

    List<EvaluationVO> queryAllDataByType(int evaluationType);
}
