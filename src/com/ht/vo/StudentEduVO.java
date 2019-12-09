package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "studentEdu")
public class StudentEduVO {//学生教育经历表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eduId;//经历主键id
    private Integer stuId;//学生表外键
    private String school;//就读学校
    private String beginDate;//开始时间
    private String endDate;//结束时间

    @Override
    public String toString() {
        return "StudentEdu{" +
                "eduId=" + eduId +
                ", stuId=" + stuId +
                ", school='" + school + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public Integer getEduId() {
        return eduId;
    }

    public void setEduId(Integer eduId) {
        this.eduId = eduId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
