package com.ht.vo;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity
@Table(name = "studentClass")
public class StudentClassVO {//班级管理表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;//主键
    private String classNo;//班级编号
    private String className;//班级名称
    private Integer count;//班级人数
    private String teacher;//授课老师员工表外键
    private String classTeacher;//班主任员工表外键
    private Integer classType;//班级类别,关联班级类别表
    private String remark;//备注
    private Integer falled;//关联班级类别
    private Integer deptId;//关联系
    private Integer majorId;//关联专业表

    @Override
    public String toString() {
        return "StudentClassVO{" +
                "classId=" + classId +
                ", classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                ", count=" + count +
                ", teacher='" + teacher + '\'' +
                ", classTeacher='" + classTeacher + '\'' +
                ", classType=" + classType +
                ", remark='" + remark + '\'' +
                ", falled=" + falled +
                ", deptId=" + deptId +
                ", majorId=" + majorId +
                '}';
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFalled() {
        return falled;
    }

    public void setFalled(Integer falled) {
        this.falled = falled;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }
}
