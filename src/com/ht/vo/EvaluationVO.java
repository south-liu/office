package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "evaluation")//员工考评标准表
public class EvaluationVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evaluationId;//主键，标识列，自动生成
    private Integer evaluationName;//考评名称
    private Integer score;//考核分值
    private Integer evaluationType;//1:授课老师2:班主任
    private String remark;//说明

    @Override
    public String toString() {
        return "Evaluation{" +
                "evaluationId=" + evaluationId +
                ", evaluationName=" + evaluationName +
                ", score=" + score +
                ", evaluationType=" + evaluationType +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getEvaluationName() {
        return evaluationName;
    }

    public void setEvaluationName(Integer evaluationName) {
        this.evaluationName = evaluationName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
