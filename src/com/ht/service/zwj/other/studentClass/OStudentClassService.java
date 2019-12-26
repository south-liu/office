package com.ht.service.zwj.other.studentClass;

import com.ht.service.zwj.other.CommonService;
import com.ht.vo.StudentClassVO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("oStudentClassService")
public class OStudentClassService extends CommonService {
    /**
     * 通过教师ID查询该教师所带领的所有班级
     *
     * @param empId
     * @return
     */
    public List<StudentClassVO> queryStudentClassByEmpId(int empId) {
        return super.findListBySql("select * from studentClass where classTeacher = " + empId + " || teacher = " + empId, StudentClassVO.class);
    }

    /**
     * 通过届别ID查询该届别下面的所有班级
     *
     * @param fallId
     * @return
     */
    public List<StudentClassVO> queryStudentClassByFallId(int fallId) {
        if (fallId <= 0) {
            return Collections.emptyList();
        }
        return super.findListBySql("select * from studentClass where falled = " + fallId, StudentClassVO.class);
    }
}
