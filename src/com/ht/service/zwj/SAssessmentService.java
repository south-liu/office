package com.ht.service.zwj;

import com.ht.vo.AssessmentVO;
import com.ht.vo.StudentVO;

import java.util.List;

public interface SAssessmentService {
    List<AssessmentVO> queryAssessmentByStudent(StudentVO student);
}
