package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "trial")
public class TrialVO {//试讲与培训计划表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trialId;//主键，标识列，自动生成
    private String date;//日期
    private String time;//时间(星期一---星期日)
    private Integer courseId;//关联课程/章节id
    private Integer type;//授课类型(试讲/培训)
    private Integer empId;//员工id关联员工表(授课老师)
    private String remark;//备注(针对课程重要内容)

    @Override
    public String toString() {
        return "TrialVO{" +
                "trialId=" + trialId +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", courseId=" + courseId +
                ", type=" + type +
                ", empId=" + empId +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getTrialId() {
        return trialId;
    }

    public void setTrialId(Integer trialId) {
        this.trialId = trialId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
