package com.ht.service.zwj;

import com.ht.vo.EvaluationVO;

import java.util.List;

public interface EvaluationStandardService {
    List<EvaluationVO> queryAllData(int page, int limit);

    long queryAllTotalNumber();

    EvaluationVO queryEvaluationStandardById(int evaluationId);

    long addEvaluationStandard(EvaluationVO evaluation);

    EvaluationVO queryEvaluationStandardByName(String evaluationName);

    void updateEvaluationStandard(EvaluationVO evaluation);

    void deleteEvaluationStandard(int evaluationId);
}
