package com.ht.dao.zwj.impl;

import com.ht.dao.zwj.DaoUtils;
import com.ht.dao.zwj.EvaluationStandardDao;
import com.ht.vo.EvaluationVO;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("evaluationStandardDao")
public class EvaluationStandardDaoImpl extends DaoUtils implements EvaluationStandardDao {
    @Override
    public List<EvaluationVO> queryAllData(int page, int limit) {
        return this.queryAllBySql("select * from evaluation order by evaluationId desc limit " + ((page - 1) * limit) + ',' + limit,
                Transformers.aliasToBean(EvaluationVO.class));
    }

    @Override
    public long queryAllTotalNumber() {
        return this.queryTotalNumber("select count(1) from evaluation");
    }

    @Override
    public EvaluationVO queryEvaluationStandardById(int evaluationId) {
        return this.queryEntityByHql(EvaluationVO.class, evaluationId);
    }

    @Override
    public long addEvaluationStandard(EvaluationVO evaluation) {
        return this.saveEntity(evaluation);
    }

    @Override
    public EvaluationVO queryEvaluationStandardByName(String evaluationName) {
        return (EvaluationVO) this.queryEntityBySql("select * from evaluation where evaluationName = '" + evaluationName + "'",
                Transformers.aliasToBean(EvaluationVO.class));
    }

    @Override
    public void updateEvaluationStandard(EvaluationVO evaluation) {
        this.updateEntity(evaluation);
    }

    @Override
    public void deleteEvaluationStandard(EvaluationVO evaluation) {
        if (evaluation == null || evaluation.getEvaluationId() <= 0) {
            return;
        }
        this.deleteEntity(evaluation);
    }

    /**
     * 通过类型获取所有考评标准
     *
     * @param evaluationType
     * @return
     */
    @Override
    public List<EvaluationVO> queryAllDataByType(int evaluationType) {
        return super.queryAllBySql("select * from evaluation where evaluationType = " + evaluationType, Transformers.aliasToBean(EvaluationVO.class));
    }
}
