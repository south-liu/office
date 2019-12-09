package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "teacherTotal")//考评总表
public class TeacherTotalVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teachertotalId;//主键，标识列，自动生成
    private Integer classTeacher;//班主任或老师员工表主键
    private Integer classId;//关联班级表主键
    private Integer studentId;//关联学生id
    private String optime;//考评时间
    private String sugges;//问题与建议
    private Integer evaluationType;//1:授课老师2:班主任

    @Override
    public String toString() {
        return "TeacherTotal{" +
                "teachertotalId=" + teachertotalId +
                ", classTeacher=" + classTeacher +
                ", classId=" + classId +
                ", studentId=" + studentId +
                ", optime='" + optime + '\'' +
                ", sugges='" + sugges + '\'' +
                ", evaluationType=" + evaluationType +
                '}';
    }

    public Integer getTeachertotalId() {
        return teachertotalId;
    }

    public void setTeachertotalId(Integer teachertotalId) {
        this.teachertotalId = teachertotalId;
    }

    public Integer getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(Integer classTeacher) {
        this.classTeacher = classTeacher;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public String getSugges() {
        return sugges;
    }

    public void setSugges(String sugges) {
        this.sugges = sugges;
    }

    public Integer getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
    }
}
