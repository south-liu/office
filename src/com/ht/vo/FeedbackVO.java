package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table(name = "feedback")
public class FeedbackVO {  //问题反馈表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;
    private Integer feedBackType;  //1：员工，2学生
    private String empId;
    private String empname;  //获取session中学生班级及姓名或员工姓名
    private String feedbackTime;  //反馈时间，获取系统时间
    private String remark;  //建议
    private String image;  //图片
    private Integer status;  //1 未处理 2 已处理
    private Integer depId;  //部门
    private String opinion;  //审批意见
    private String userId;  //审批人

    @Override
    public String toString() {
        return "FeedbackVO{" +
                "feedbackId=" + feedbackId +
                ", feedBackType=" + feedBackType +
                ", empId='" + empId + '\'' +
                ", empname='" + empname + '\'' +
                ", feedbackTime='" + feedbackTime + '\'' +
                ", remark='" + remark + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", depId=" + depId +
                ", opinion='" + opinion + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getFeedBackType() {
        return feedBackType;
    }

    public void setFeedBackType(Integer feedBackType) {
        this.feedBackType = feedBackType;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
