package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table(name = "weekArrange")
public class WeekArrangeVO {  //员工值班安排表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer weekArrangeId;
    private String weekArrangeName;  //班级名称
    private String ranges;  //值班要求
    private String week;  //一个星期中的哪一天
    private String empId;  //关联emp表主键
    private Integer orderId;  //排序
    private Integer duty;  //总值班： 1、是； 2、否
    private String startTime;  //开始时间
    private String endTime;  //结束时间
    private String remark;  //说明

    @Override
    public String toString() {
        return "WeekArrangeVO{" +
                "weekArrangeid=" + weekArrangeId +
                ", weekArrangeName='" + weekArrangeName + '\'' +
                ", ranges='" + ranges + '\'' +
                ", week='" + week + '\'' +
                ", empId='" + empId + '\'' +
                ", orderId=" + orderId +
                ", duty=" + duty +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getWeekArrangeId() {
        return weekArrangeId;
    }

    public void setWeekArrangeId(Integer weekArrangeid) {
        this.weekArrangeId = weekArrangeid;
    }

    public String getWeekArrangeName() {
        return weekArrangeName;
    }

    public void setWeekArrangeName(String weekArrangeName) {
        this.weekArrangeName = weekArrangeName;
    }

    public String getRanges() {
        return ranges;
    }

    public void setRanges(String ranges) {
        this.ranges = ranges;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDuty() {
        return duty;
    }

    public void setDuty(Integer duty) {
        this.duty = duty;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
