package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "empAssessment")
public class EmpAssessmentVO {//员工考核各项得分详情表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empAssessId;
    private Integer aduitModelId;//考核指标关联考核指标表
    private Integer score;//得分
    private Integer empAssTotalId;//关联考核总表

    @Override
    public String toString() {
        return "EmpAssessmentVO{" +
                "empAssessId=" + empAssessId +
                ", aduitModelId=" + aduitModelId +
                ", score=" + score +
                ", empAssTotalId=" + empAssTotalId +
                '}';
    }

    public Integer getEmpAssessId() {
        return empAssessId;
    }

    public void setEmpAssessId(Integer empAssessId) {
        this.empAssessId = empAssessId;
    }

    public Integer getAduitModelId() {
        return aduitModelId;
    }

    public void setAduitModelId(Integer aduitModelId) {
        this.aduitModelId = aduitModelId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getEmpAssTotalId() {
        return empAssTotalId;
    }

    public void setEmpAssTotalId(Integer empAssTotalId) {
        this.empAssTotalId = empAssTotalId;
    }
}