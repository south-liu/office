package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "headTeacher")//班主任和老师考评表
public class HeadTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer headTeacherId;//主键，标识列，自动生成
    private Integer evaluationId;//考评类别id关联考评类别表
    private String testScore;//考评分
    private Integer totalId;//关联班考评主表

    @Override
    public String toString() {
        return "HeadTeacher{" +
                "headTeacherId=" + headTeacherId +
                ", evaluationId=" + evaluationId +
                ", testScore='" + testScore + '\'' +
                ", totalId=" + totalId +
                '}';
    }

    public Integer getHeadTeacherId() {
        return headTeacherId;
    }

    public void setHeadTeacherId(Integer headTeacherId) {
        this.headTeacherId = headTeacherId;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getTestScore() {
        return testScore;
    }

    public void setTestScore(String testScore) {
        this.testScore = testScore;
    }

    public Integer getTotalId() {
        return totalId;
    }

    public void setTotalId(Integer totalId) {
        this.totalId = totalId;
    }
}
