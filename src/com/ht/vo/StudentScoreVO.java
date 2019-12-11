package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "studentScore")
public class StudentScoreVO {//学生成绩记录管理
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scoreId;//成绩主键id
    private Integer stuId;//学生表外键
    private Integer score;//学生成绩
    private Integer rescore;//补考成绩
    private Integer courseId;//关联课程id
    private Integer testType;//1:笔试2:机试3:模拟面试
    private Integer termId;//关联学期id
    private String scoreTime;//考试时间
    private Integer empId;//录入人员,获取session值
    private String remark;//备注

    @Override
    public String toString() {
        return "StudentScoreVO{" +
                "scoreId=" + scoreId +
                ", stuId=" + stuId +
                ", score=" + score +
                ", rescore=" + rescore +
                ", courseId=" + courseId +
                ", testType=" + testType +
                ", termId=" + termId +
                ", scoreTime='" + scoreTime + '\'' +
                ", empId=" + empId +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRescore() {
        return rescore;
    }

    public void setRescore(Integer rescore) {
        this.rescore = rescore;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(String scoreTime) {
        this.scoreTime = scoreTime;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
