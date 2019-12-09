package com.ht.vo;

import org.omg.PortableInterceptor.INACTIVE;

import javax.persistence.*;

@Entity
@Table(name = "holiday")
public class HolidayVO {//请假申请表(员工)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer holidayId;
    private Integer empId;//关联Emp(请假人)
    private Integer holidayDay;//请假天数
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String title;//标题
    private Integer status;//状态 1:审批中 2：已完成 3：不批准
    private String remark;//内容

    @Override
    public String toString() {
        return "HolidayVO{" +
                "holidayId=" + holidayId +
                ", empId=" + empId +
                ", holidayDay=" + holidayDay +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getHolidayDay() {
        return holidayDay;
    }

    public void setHolidayDay(Integer holidayDay) {
        this.holidayDay = holidayDay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
