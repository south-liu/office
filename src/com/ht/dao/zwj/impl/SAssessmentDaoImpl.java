package com.ht.dao.zwj.impl;

import com.ht.dao.zwj.DaoUtils;
import com.ht.dao.zwj.SAssessmentDao;
import com.ht.vo.AssessmentVO;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sAssessmentDao")
public class SAssessmentDaoImpl extends DaoUtils implements SAssessmentDao {
    // 通过学生的班级id获取所有的考评通知
    @Override
    public List<AssessmentVO> queryAssessmentByStudentClass(Integer clazz) {
        return super.queryAllBySql("select * from assessment where studentClassId = " + clazz + " and state = 0 and startTime < now() and endTime > now()",
                Transformers.ALIAS_TO_ENTITY_MAP);
    }
}
