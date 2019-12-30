package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "holidayStudent")
public class HolidayStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer holidayId;//主键，标识列，自动生成
    private Integer studentId;//关联学生表主键(请假人)
    private String holidayDay;//假期天数
    private String holidayHour;//假期小时
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String title;//标题
    private String remark;//内容
    private String status;//审批中，审批通过，审批未通过

    @Override
    public String toString() {
        return "HolidayStudent{" +
                "holidayId=" + holidayId +
                ", studentId=" + studentId +
                ", holidayDay='" + holidayDay + '\'' +
                ", holidayHour='" + holidayHour + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getHolidayHour() {
        return holidayHour;
    }

    public void setHolidayHour(String holidayHour) {
        this.holidayHour = holidayHour;
    }

    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getHolidayDay() {
        return holidayDay;
    }

    public void setHolidayDay(String holidayDay) {
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
