package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "weekly")
public class WeeklyVO {//周报管理
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer weeklyId;//主键
    private String empId;//员工编号
    private String workDay;//填写日期
    private String weekCur;//本周情况描述
    private String studentQuestion;//问题学生情况反馈
    private String idea;//意见建议
    private String weekNext;//下周工作计划


    @Override
    public String toString() {
        return "WeeklyVO{" +
                "weeklyId=" + weeklyId +
                ", empId='" + empId + '\'' +
                ", workDay='" + workDay + '\'' +
                ", weekCur='" + weekCur + '\'' +
                ", studentQuestion='" + studentQuestion + '\'' +
                ", idea='" + idea + '\'' +
                ", weekNext='" + weekNext + '\'' +
                '}';
    }

    public Integer getWeeklyId() {
        return weeklyId;
    }

    public void setWeeklyId(Integer weeklyId) {
        this.weeklyId = weeklyId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public String getWeekCur() {
        return weekCur;
    }

    public void setWeekCur(String weekCur) {
        this.weekCur = weekCur;
    }

    public String getStudentQuestion() {
        return studentQuestion;
    }

    public void setStudentQuestion(String studentQuestion) {
        this.studentQuestion = studentQuestion;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getWeekNext() {
        return weekNext;
    }

    public void setWeekNext(String weekNext) {
        this.weekNext = weekNext;
    }
}
