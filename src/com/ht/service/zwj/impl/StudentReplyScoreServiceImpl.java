package com.ht.service.zwj.impl;

import com.ht.dao.zwj.StudentReplyScoreDao;
import com.ht.service.zwj.StudentReplyScoreService;
import com.ht.vo.ProjectVO;
import com.ht.vo.StudentReplyScoreVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StudentReplyScoreServiceImpl implements StudentReplyScoreService {
    @Resource
    private StudentReplyScoreDao studentReplyScoreDao;

    @Override
    public List<Map<String, Object>> allData(Integer page, Integer limit) {
        return studentReplyScoreDao.allData(page, limit);
    }

    @Override
    public long getTotality() {
        return studentReplyScoreDao.getTotality();
    }

    @Override
    public int addStudentReplyScore(StudentReplyScoreVO studentReplyScore) {
        return studentReplyScoreDao.addStudentReplyScore(studentReplyScore);
    }

    @Override
    public StudentReplyScoreVO getStudentReplyScoreById(int replyId) {
        return studentReplyScoreDao.getStudentReplyScoreById(replyId);
    }

    @Override
    public void deleteStudentReplyScore(int replyId) {
        StudentReplyScoreVO studentReplyScore = getStudentReplyScoreById(replyId);

        if (studentReplyScore != null) {
            studentReplyScoreDao.deleteStudentReplyScore(studentReplyScore);
        }
    }

    @Override
    public void updateStudentReplyScore(StudentReplyScoreVO studentReplyScore) {
        studentReplyScoreDao.updateStudentReplyScore(studentReplyScore);
    }

    @Override
    public void deleteMultiStudentReplyScore(Integer[] replyIds) {
        if (replyIds == null) {
            return;
        }
        for (Integer replyId : replyIds) {
            deleteStudentReplyScore(replyId);
        }
    }

    /**
     * 加搜索条件的查询数据
     *
     * @param projectId
     * @param studentClassId
     * @return
     */
    @Override
    public List<Map<String, Object>> findStudentReplyScoreByOptions(int projectId, int studentClassId, int page, int limit) {
        StringBuffer stringBuffer = new StringBuffer();

        String whereString = "where 1 = 1";
        if (projectId > 0) {
            whereString += " and srs.projectId = " + projectId;
        }
        if (studentClassId > 0) {
            whereString += " and cla.classId = " + studentClassId;
        }

        stringBuffer.append("select srs.*,cla.className,pro.projectName,stu.stuName,emp.empName,(select e.empName from emp as e where e.empId = srs.gradeEmpId) as 'gradeEmpName' from " + StudentReplyScoreDao.currentTableName + " as srs left join student as stu on stu.studId = srs.studentId left join studentClass as cla on stu.clazz = cla.classId left join project as pro on pro.projectId = srs.projectId left join emp on emp.empId = srs.empId " + whereString + " order by " + StudentReplyScoreDao.currentTableId + " asc limit " + ((page - 1) * limit) + "," + limit);

        return studentReplyScoreDao.findDataBySql(stringBuffer.toString());
    }

    /**
     * 加搜索条件的总数
     *
     * @param projectId
     * @param studentClassId
     * @return
     */
    @Override
    public long findStudentReplyScoreCountByOptions(int projectId, int studentClassId) {
        StringBuffer stringBuffer = new StringBuffer();

        String whereString = "where 1 = 1";
        if (projectId > 0) {
            whereString += " and srs.projectId = " + projectId;
        }
        if (studentClassId > 0) {
            whereString += " and cla.classId = " + studentClassId;
        }

        stringBuffer.append("select count(1) from " + StudentReplyScoreDao.currentTableName + " as srs left join student as stu on stu.studId = srs.studentId left join studentClass as cla on stu.clazz = cla.classId left join project as pro on pro.projectId = srs.projectId left join emp on emp.empId = srs.empId " + whereString);
        long count = studentReplyScoreDao.findCountByOptions(stringBuffer.toString());

        return count;
    }

    @Override
    public List<ProjectVO> findGradedProjectByStudentId(Integer stuId) {
        return studentReplyScoreDao.findGradedProjectByStudentId(stuId);
    }

    @Override
    public Map<String, Object> findProjectScoreByOptions(Integer stuId, Integer projectId) {
        return studentReplyScoreDao.findProjectScoreByOptions(stuId, projectId);
    }
}
