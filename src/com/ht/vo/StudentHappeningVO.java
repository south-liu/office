package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "studentHappening")
public class StudentHappeningVO {//学生情况记录
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer happenId;//键id
    private Integer stuId;//学生表外键
    private String happening;//情况记录
    private String opTime;//记录时间
    private Integer empId;//记录人，session获取id

    @Override
    public String toString() {
        return "StudentHappeningVO{" +
                "happenId=" + happenId +
                ", stuId=" + stuId +
                ", happening='" + happening + '\'' +
                ", opTime='" + opTime + '\'' +
                ", empId=" + empId +
                '}';
    }

    public Integer getHappenId() {
        return happenId;
    }

    public void setHappenId(Integer happenId) {
        this.happenId = happenId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getHappening() {
        return happening;
    }

    public void setHappening(String happening) {
        this.happening = happening;
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
}
