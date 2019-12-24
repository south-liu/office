package com.ht.vo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "empAssessmentSuggest")//班主任和老师考评表
public class EmpAssessmentSuggestVO implements Serializable {
    private static final long serialVersionUID = 7134083510583540145L;
    @Id
    private int assessmentId;// 考评ID
    @Id
    private int studentId;// 学生ID

    private String suggest;// 考评建议

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

        EmpAssessmentSuggestVO that = (EmpAssessmentSuggestVO) o;

        if (assessmentId != that.assessmentId) return false;
        return studentId == that.studentId;
    }

    @Override
    public int hashCode() {
        int result = assessmentId;
        result = 31 * result + studentId;
        return result;
    }

    @Override
    public String toString() {
        return "EmpAssessmentSuggestVO{" +
                "assessmentId=" + assessmentId +
                ", studentId=" + studentId +
                ", suggest='" + suggest + '\'' +
                '}';
    }
}
