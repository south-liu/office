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
    private String dayStr;//请假时长
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String type;//类型
    private Integer status;//状态 1:审批中 2：已完成 3：不批准
    private String remark;//内容
    private String taskId;//流程任务id


    @Override
    public String toString() {
        return "HolidayVO{" +
                "holidayId=" + holidayId +
                ", empId=" + empId +
                ", holidayDay=" + holidayDay +
                ", dayStr='" + dayStr + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", taskId=" + taskId +
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

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
