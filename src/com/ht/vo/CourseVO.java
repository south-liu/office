package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class CourseVO {//课程表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;//主键，标识列，自动生成
    private String courseName;//课程名称
    private String isObligatory;//是否必修
    private Integer courseTypeId;//课程类别id外键
    private String remark;//说明

    @Override
    public String toString() {
        return "CourseVO{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", isObligatory='" + isObligatory + '\'' +
                ", courseTypeId=" + courseTypeId +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIsObligatory() {
        return isObligatory;
    }

    public void setIsObligatory(String isObligatory) {
        this.isObligatory = isObligatory;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
