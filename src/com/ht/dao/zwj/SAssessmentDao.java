package com.ht.dao.zwj;

import com.ht.vo.AssessmentVO;

import java.util.List;

public interface SAssessmentDao {
    List<AssessmentVO> queryAssessmentByStudentClass(Integer clazz);
}
