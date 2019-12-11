package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "checkwork")//考勤管理
public class CheckWorkVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkworkId;//主键
    private Integer empId;//员工id
    private String noCardTime;//未打卡时间
    private String why;//未打卡原因
    private Integer deptHeadId;//审核人 部门负责人id(empid)
    private String checkTime;//审核时间
    private String remark;//审核说明
    private Integer status;//审核状态 0未审核 1审核通过 2审核不通过

    @Override
    public String toString() {
        return "CheckWorkVO{" +
                "checkworkId=" + checkworkId +
                ", empId=" + empId +
                ", noCardTime='" + noCardTime + '\'' +
                ", why='" + why + '\'' +
                ", deptHeadId=" + deptHeadId +
                ", checkTime='" + checkTime + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getCheckworkId() {
        return checkworkId;
    }

    public void setCheckworkId(Integer checkworkId) {
        this.checkworkId = checkworkId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getNoCardTime() {
        return noCardTime;
    }

    public void setNoCardTime(String noCardTime) {
        this.noCardTime = noCardTime;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public Integer getDeptHeadId() {
        return deptHeadId;
    }

    public void setDeptHeadId(Integer deptHeadId) {
        this.deptHeadId = deptHeadId;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
