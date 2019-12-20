package com.ht.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "assessmentInformation")
public class AssessmentInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer studentId;// 学生ID
    @Id
    private Integer assessmentId;// 考评ID

    private Integer grossScore;// 考评总分数
    private String suggest;// 建议

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

    public Integer getGrossScore() {
        return grossScore;
    }

    public void setGrossScore(Integer grossScore) {
        this.grossScore = grossScore;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssessmentInformation that = (AssessmentInformation) o;

        if (!studentId.equals(that.studentId)) return false;
        return assessmentId.equals(that.assessmentId);
    }

    @Override
    public int hashCode() {
        int result = studentId.hashCode();
        result = 31 * result + assessmentId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AssessmentInformation{" +
                "studentId=" + studentId +
                ", assessmentId=" + assessmentId +
                ", grossScore=" + grossScore +
                ", suggest='" + suggest + '\'' +
                '}';
    }
}
