package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "education")
public class EducationVO {//教育经历
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eduId;//主键
    private String empId;//员工编号
    private String schoolName;//学校名称
    private String degree;//学历
    private String startDate;//入校时间
    private String endDate;//毕业时间
    private String remark;//奖惩情况

    @Override
    public String toString() {
        return "EducationVO{" +
                "eduId=" + eduId +
                ", empId='" + empId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", degree='" + degree + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getEduId() {
        return eduId;
    }

    public void setEduId(Integer eduId) {
        this.eduId = eduId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
