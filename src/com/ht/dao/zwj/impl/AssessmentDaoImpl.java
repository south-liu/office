package com.ht.dao.zwj.impl;

import com.ht.dao.zwj.AssessmentDao;
import com.ht.dao.zwj.DaoUtils;
import com.ht.vo.AssessmentInformationVO;
import com.ht.vo.AssessmentVO;
import com.ht.vo.EmpAssessmentSuggestVO;
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
    public List<Map<String, Object>> queryAssessmentInformation(int assessmentId, int studentId) {
        return super.queryAllBySql("select * from assessmentInformation where assessmentId = " + assessmentId + " and studentId = " + studentId,
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
    public long insertAssessmentInformation(AssessmentInformationVO assessmentInformationVO) {
        super.saveEntity(assessmentInformationVO);
        return assessmentInformationVO.getAssessmentId();
    }

    @Override
    public List<Map<String, Object>> queryStudentByAssessmentId(Integer assessmentId) {
        return super.queryAllBySql("select studId,stuName from student where studId in(select studentId from assessmentInformation where assessmentId = " + assessmentId + ")", Transformers.ALIAS_TO_ENTITY_MAP);
    }

    @Override
    public long insertEmpAssessmentSuggest(EmpAssessmentSuggestVO empAssessmentSuggest) {
        return super.saveEntity(empAssessmentSuggest);
    }

    @Override
    public List<Map<String, Object>> queryAssessmentDetailContent(int assessmentId, int studentId) {
        return super.queryAllBySql("select ai.*,e.evaluationName from assessmentInformation as ai left join evaluation as e on ai.evaluationId = e" +
                ".evaluationId where ai.assessmentId = " + assessmentId + " and ai.studentId = " + studentId, Transformers.ALIAS_TO_ENTITY_MAP);
    }

    /**
     * 通过考评ID得到考评的平均分
     *
     * @param assessmentId
     * @return
     */
    @Override
    public Float queryAvgScoreByAssessmentId(Integer assessmentId) {
        Map<String, Object> o = (Map<String, Object>) super.queryEntityBySql("select avg(score) as avg from (select ai.studentId,sum(ai.grossScore) as score from " +
                "assessmentInformation as " +
                "ai " +
                "group " +
                "by ai.assessmentId,ai.studentId having assessmentId = " + assessmentId + ") as assessmentGrossScore", Transformers.ALIAS_TO_ENTITY_MAP);

        if (o.get("avg") instanceof BigDecimal) {
            Number avg = (BigDecimal) o.get("avg");
            return avg.floatValue();
        }

        return null;
    }

    @Override
    public Map<String, Object> queryAssessmentSuggest(int assessmentId, int studentId) {
        return (Map<String, Object>) super.queryEntityBySql("select * from empAssessmentSuggest where assessmentId = " + assessmentId + " and studentId = " + studentId,
                Transformers.ALIAS_TO_ENTITY_MAP);
    }

    @Override
    public List<Map<String, Object>> queryUnfinishedAssessment() {
        return super.queryAllBySql("select * from assessment where state = 0 and endTime <= now()", Transformers.ALIAS_TO_ENTITY_MAP);
    }

    @Override
    public List<Map<String, Object>> queryEvaluationAvgScore(int assessmentId) {
        return super.queryAllBySql("select assessmentId,ai.evaluationId,e.evaluationName,avg(grossScore) as avg from assessmentInformation as ai left join " +
                        "evaluation as e on ai.evaluationId = e.evaluationId where assessmentId = " + assessmentId + " group by assessmentId,ai.evaluationId;",
                Transformers.ALIAS_TO_ENTITY_MAP);
    }
}
