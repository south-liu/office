package com.ht.service.zwj.other.studentClass;

import com.ht.service.zwj.other.CommonService;
import com.ht.vo.StudentClassVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("oStudentClassService")
public class OStudentClassService extends CommonService {
    public List<StudentClassVO> queryStudentClassByEmpId(int empId) {
        return this.findListBySql("select * from studentClass where classTeacher = " + empId + " || teacher = " + empId, StudentClassVO.class);
    }
}
