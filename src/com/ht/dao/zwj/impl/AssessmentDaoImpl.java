package com.ht.dao.zwj.impl;

import com.ht.dao.zwj.AssessmentDao;
import com.ht.dao.zwj.DaoUtils;
import com.ht.vo.AssessmentInformation;
import com.ht.vo.AssessmentVO;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository("assessmentDao")
public class AssessmentDaoImpl extends DaoUtils implements AssessmentDao {
    @Override
    public List<Map<String, Object>> queryAllData(int page, int limit) {
        return super.queryAllBySql("select a.*,emp.empName,sc.className,emp.postId,emp.postName from assessment as a left join emp on a.empId = emp" +
                ".empId" +
                " left join studentClass as sc on a.studentClassId = sc.classId" +
                " order by a.assessmentId desc" +
                " limit " + ((page - 1) * limit) + ',' + limit, Transformers.ALIAS_TO_ENTITY_MAP);
    }

    @Override
    public long queryAllTotalNumber() {
        return super.queryTotalNumber("select count(1) from assessment");
    }

    @Override
    public AssessmentVO queryAssessmentById(int assessmentId) {
        return super.queryEntityByHql(AssessmentVO.class, assessmentId);
    }

    @Override
    public long addAssessment(AssessmentVO assessment) {
        return super.saveEntity(assessment);
    }

    @Override
    public void updateAssessment(AssessmentVO assessment) {
        super.updateEntity(assessment);
    }

    @Override
    public void deleteAssessment(AssessmentVO assessment, List<Map<String, Object>> studentList) {
        if (assessment == null || assessment.getAssessmentId() <= 0) {
            return;
        }
        if (studentList != null) {
            for (Map<String, Object> map : studentList) {
                int studId = Integer.parseInt(String.valueOf(map.get("studId")));
                try {
                    super.updateBySql("delete from assessmentInformation where assessmentId = " + assessment.getAssessmentId() + " and studentId = " + studId);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        super.deleteEntity(assessment);
    }

    /**
     * 查询平均分
     *
     * @param assessmentId
     * @param ids
     * @return
     */
    @Override
    public float queryAvgScore(int assessmentId, String ids) {
        Map map = (Map) super.queryEntityBySql("select avg(grossScore) as avg from assessmentInformation where assessmentId = " + assessmentId +
                " and  studentId in" +
                "(" + ids + ")", Transformers.ALIAS_TO_ENTITY_MAP);
        BigDecimal avg = (BigDecimal) map.get("avg");
        if (avg != null) {
            return avg.floatValue();
        }

        return 0f;
    }

    @Override
    public Map<String, Object> queryAssessmentInformation(int assessmentId, int studentId) {
        return (Map<String, Object>) super.queryEntityBySql("select * from assessmentInformation where assessmentId = " + assessmentId + " and studentId = " + studentId,
                Transformers.ALIAS_TO_ENTITY_MAP);
    }

    @Override
    public long assessmentInformationTotality(int assessmentId) {
        return super.queryTotalNumber("select count(1) from assessmentInformation where assessmentId = " + assessmentId);
    }

    @Override
    public List<Map<String, Object>> queryAllData(int page, int limit, String options) {
        return super.queryAllBySql("select a.*,emp.empName,sc.className,emp.postId,emp.postName from assessment as a left join emp on a.empId = emp" +
                ".empId left join studentClass as sc on a.studentClassId = sc.classId where 1 = 1 " + options +
                " order by a.assessmentId desc" +
                " limit " + ((page - 1) * limit) + ',' + limit, Transformers.ALIAS_TO_ENTITY_MAP);
    }

    @Override
    public long queryTotalNumberByOptions(String toString) {
        return queryTotalNumber("select count(1) from assessment as a where 1 = 1 " + toString);
    }

    @Override
    public long insertAssessmentInformation(AssessmentInformation assessmentInformation) {
        super.saveEntity(assessmentInformation);
        return assessmentInformation.getAssessmentId();
    }

    @Override
    public List<Map<String, Object>> queryStudentByAssessmentId(Integer assessmentId) {
        return super.queryAllBySql("select studId,stuName from student where studId in(select studentId from assessmentInformation where assessmentId = " + assessmentId + ")", Transformers.ALIAS_TO_ENTITY_MAP);
    }
}
