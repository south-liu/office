package com.ht.service.zwj.impl;

import com.ht.dao.zwj.EvaluationStandardDao;
import com.ht.service.zwj.EvaluationStandardService;
import com.ht.vo.EvaluationVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("evaluationStandardService")
public class EvaluationStandardServiceImpl implements EvaluationStandardService {
    @Resource
    private EvaluationStandardDao evaluationStandardDao;

    @Override
    public List<EvaluationVO> queryAllData(int page, int limit) {
        return evaluationStandardDao.queryAllData(page, limit);
    }

    @Override
    public long queryAllTotalNumber() {
        return evaluationStandardDao.queryAllTotalNumber();
    }

    @Override
    public EvaluationVO queryEvaluationStandardById(int evaluationId) {
        return evaluationStandardDao.queryEvaluationStandardById(evaluationId);
    }

    @Override
    public long addEvaluationStandard(EvaluationVO evaluation) {
        return evaluationStandardDao.addEvaluationStandard(evaluation);
    }

    @Override
    public EvaluationVO queryEvaluationStandardByName(String evaluationName) {
        return evaluationStandardDao.queryEvaluationStandardByName(evaluationName);
    }

    @Override
    public List<EvaluationVO> queryAllDataByType(int state) {
        return evaluationStandardDao.queryAllDataByType(state);
    }

    @Override
    public void updateEvaluationStandard(EvaluationVO evaluation) {
        evaluationStandardDao.updateEvaluationStandard(evaluation);
    }

    @Override
    public void deleteEvaluationStandard(int evaluationId) {
        EvaluationVO evaluation = evaluationStandardDao.queryEvaluationStandardById(evaluationId);
        evaluationStandardDao.deleteEvaluationStandard(evaluation);
    }
}
