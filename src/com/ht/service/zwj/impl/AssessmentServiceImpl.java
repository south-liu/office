package com.ht.service.zwj.impl;

import com.ht.dao.zwj.AssessmentDao;
import com.ht.service.zwj.AssessmentService;
import com.ht.service.zwj.other.student.OStudentService;
import com.ht.vo.AssessmentInformation;
import com.ht.vo.AssessmentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("assessmentService")
public class AssessmentServiceImpl implements AssessmentService {
    @Resource
    private AssessmentDao assessmentDao;
    @Resource
    private OStudentService oStudentService;

    @Override
    public List<Map<String, Object>> queryAllData(int page, int limit) {
        return assessmentDao.queryAllData(page, limit);
    }

    @Override
    public long queryAllTotalNumber() {
        return assessmentDao.queryAllTotalNumber();
    }

    @Override
    public AssessmentVO queryAssessmentById(int assessmentId) {
        return assessmentDao.queryAssessmentById(assessmentId);
    }

    @Override
    public long addAssessment(AssessmentVO assessment) {
        return assessmentDao.addAssessment(assessment);
    }

    @Override
    public void updateAssessment(AssessmentVO assessment) {
        assessmentDao.updateAssessment(assessment);
    }

    /**
     * 通过考评对象查询到该考评关联的班级下面学生的所有记录
     *
     * @param assessmentId
     */
    @Override
    public void deleteAssessment(int assessmentId) {
        if (assessmentId > 0) {
            AssessmentVO assessmentVO = queryAssessmentById(assessmentId);
            List<Map<String, Object>> studentByClassId = oStudentService.findStudentByClassId(assessmentVO.getStudentClassId());
            assessmentDao.deleteAssessment(assessmentVO, studentByClassId);
        }
    }

    /**
     * 改变考评状态
     *
     * @param assessmentId
     * @param state
     * @return
     */
    @Override
    public int changeAssessmentState(int assessmentId, int state) {
        AssessmentVO assessment = this.queryAssessmentById(assessmentId);
        if (assessment == null) {
            return 0;
        }

        assessment.setState(state);// 改变状态
        if (state == 1) {// 结束考评
            /*
            结束考评：
            1.改变结束时间
            2.统计成绩
             */
            String nowDate = DateFormat.getDateTimeInstance().format(new Date());// 结束时间

            assessment.setAverageScore(queryAvgScoreByAssessment(assessment));// 计算平均分
            assessment.setEndTime(nowDate);
        }
        updateAssessment(assessment);// 更改
        return assessment.getAssessmentId();
    }

    /**
     * 通过班级id计算该次考评平均分
     *
     * @param assessment
     * @return
     */
    @Override
    public float queryAvgScoreByAssessment(AssessmentVO assessment) {
        // 通过考评的班级id获取该班级所有的学生id
        List<Map<String, Object>> studentByClassId = oStudentService.findStudentByClassId(assessment.getStudentClassId());
        if (studentByClassId != null && studentByClassId.size() > 0) {
            // 有关该考评的所有学生的ID
            StringBuffer studentIds = new StringBuffer();
            for (Map<String, Object> student : studentByClassId) {
                String studId = String.valueOf(student.get("studId"));
                studentIds.append(studId + ',');
            }
            String ids = studentIds.substring(0, studentIds.length() - 1);

            // 通过考评id和学生id字符串查询该条考评的平均分
            return assessmentDao.queryAvgScore(assessment.getAssessmentId(), ids);
        }
        return 0f;
    }

    @Override
    public Map<String, Object> queryAssessmentInformation(int assessmentId, int studentId) {
        return assessmentDao.queryAssessmentInformation(assessmentId, studentId);
    }

    @Override
    public long assessmentInformationTotality(int assessmentId) {
        return assessmentDao.assessmentInformationTotality(assessmentId);
    }

    @Override
    public int changeAssessmentEndTime(int assessmentId, int time) {
        AssessmentVO assessment = this.queryAssessmentById(assessmentId);

        if (assessment == null) {// 不存在该考评
            return 0;
        }

        DateFormat dateFormat = DateFormat.getDateTimeInstance();// 获取日期时间转换器
        String endTime = assessment.getEndTime();
        try {
            Date endDate = dateFormat.parse(endTime);// 将字符串转换成Date对象

            long deplayedTime = endDate.getTime() + time * 60 * 1000;// 计算延迟的时间
            endDate.setTime(deplayedTime);// 延迟时间
            endTime = dateFormat.format(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assessment.setEndTime(endTime);// 设置对象

        try {
            this.updateAssessment(assessment);// 修改数据库数据
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Map<String, Object>> searchAssessmentByOptions(int page, int limit, Integer empId, String startTime, String endTime) {
        StringBuffer querySQL = new StringBuffer();
        if (empId != null && empId > 0) {
            querySQL.append(" and a.empId = " + empId);
        }
        if (startTime != null && !startTime.isEmpty()) {
            querySQL.append(" and a.startTime > '" + startTime + "'");
        }
        if (endTime != null && !endTime.isEmpty()) {
            querySQL.append(" and a.endTime < '" + endTime + "'");
        }

        List<Map<String, Object>> maps = null;
        try {
            maps = assessmentDao.queryAllData(page, limit, querySQL.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    @Override
    public long searchAssessmentTotalityByOptions(Integer empId, String startTime, String endTime) {
        StringBuffer querySQL = new StringBuffer();
        if (empId != null && empId > 0) {
            querySQL.append(" and a.empId = " + empId);
        }
        if (startTime != null && !startTime.isEmpty()) {
            querySQL.append(" and a.startTime > '" + startTime + "'");
        }
        if (endTime != null && !endTime.isEmpty()) {
            querySQL.append(" and a.endTime < '" + endTime + "'");
        }
        return assessmentDao.queryTotalNumberByOptions(querySQL.toString());
    }

    @Override
    public long insertAssessmentInformation(AssessmentInformation assessmentInformation) {
        return assessmentDao.insertAssessmentInformation(assessmentInformation);
    }
}
