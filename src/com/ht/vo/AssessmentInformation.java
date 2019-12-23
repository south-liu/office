package com.ht.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "assessmentInformation")// 考评内容分值
public class AssessmentInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer studentId;// 学生ID
    @Id
    private Integer assessmentId;// 考评ID
    @Id
    private Integer evaluationId;// 考评内容ID

    private Integer grossScore;// 分数

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getGrossScore() {
        return grossScore;
    }

    public void setGrossScore(Integer grossScore) {
        this.grossScore = grossScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssessmentInformation that = (AssessmentInformation) o;

        if (!studentId.equals(that.studentId)) return false;
        if (!assessmentId.equals(that.assessmentId)) return false;
        return evaluationId.equals(that.evaluationId);
    }

    @Override
    public int hashCode() {
        int result = studentId.hashCode();
        result = 31 * result + assessmentId.hashCode();
        result = 31 * result + evaluationId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AssessmentInformation{" +
                "studentId=" + studentId +
                ", assessmentId=" + assessmentId +
                ", evaluationId=" + evaluationId +
                ", grossScore=" + grossScore +
                '}';
    }
}
