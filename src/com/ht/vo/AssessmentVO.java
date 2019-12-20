package com.ht.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assessment")
public class AssessmentVO {// 教师考评
    @Id
    @GeneratedValue
    private Integer assessmentId;// 考评ID
    private Integer empId;// 员工ID
    private Integer studentClassId;// 考评班级ID
    private String startTime;// 考评开始时间
    private String endTime;// 考评结束时间
    /**
     * 0：待考评中
     * 1：考评完成
     */
    private Integer state;// 考评状态
    private Float averageScore;// 考评平均分

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(Integer studentClassId) {
        this.studentClassId = studentClassId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssessmentVO that = (AssessmentVO) o;

        return assessmentId.equals(that.assessmentId);
    }

    @Override
    public int hashCode() {
        return assessmentId.hashCode();
    }

    @Override
    public String toString() {
        return "AssessmentVO{" +
                "assessmentId=" + assessmentId +
                ", empId=" + empId +
                ", studentClassId=" + studentClassId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", state=" + state +
                ", averageScore=" + averageScore +
                '}';
    }
}
