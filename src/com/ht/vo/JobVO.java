package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "job")
public class JobVO {//工作经历
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;//id
    private String empId;//员工编号
    private String companyName;//公司名称
    private String degree;//岗位
    private String startDate;//入职时间
    private String endDate;//离职时间
    private String reason;//离职原因
    private String remark;//说明

    @Override
    public String toString() {
        return "JobVO{" +
                "jobId=" + jobId +
                ", empId=" + empId +
                ", companyName='" + companyName + '\'' +
                ", degree='" + degree + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", reason='" + reason + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
