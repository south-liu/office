package com.ht.service.zwj.impl;

import com.ht.dao.zwj.SAssessmentDao;
import com.ht.service.zwj.SAssessmentService;
import com.ht.vo.AssessmentVO;
import com.ht.vo.StudentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sAssessmentService")
public class SAssessmentServiceImpl implements SAssessmentService {
    @Resource
    private SAssessmentDao sAssessmentDao;

    @Override
    public List<AssessmentVO> queryAssessmentByStudent(StudentVO student) {
        if (student == null) {
            return null;
        }
        return sAssessmentDao.queryAssessmentByStudentClass(student.getClazz());
    }
}
