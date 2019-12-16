package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "feedbackmsg")
public class FeedBackMsgVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackMsgId;
    private String feedbackMsgName;
    private Integer userId;//发送人Id
    private String userName;//发送人名称
    private Integer msgType;//1 问题反馈人 2 回复人
    private String singDate;
    private Integer feedbackId;//关联问题反馈表

    @Override
    public String toString() {
        return "FeedBackMsgVO{" +
                "feedbackMsgId=" + feedbackMsgId +
                ", feedbackMsgName='" + feedbackMsgName + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", msgType=" + msgType +
                ", singDate='" + singDate + '\'' +
                ", feedbackId=" + feedbackId +
                '}';
    }

    public Integer getFeedbackMsgId() {
        return feedbackMsgId;
    }

    public void setFeedbackMsgId(Integer feedbackMsgId) {
        this.feedbackMsgId = feedbackMsgId;
    }

    public String getFeedbackMsgName() {
        return feedbackMsgName;
    }

    public void setFeedbackMsgName(String feedbackMsgName) {
        this.feedbackMsgName = feedbackMsgName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getSingDate() {
        return singDate;
    }

    public void setSingDate(String singDate) {
        this.singDate = singDate;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }
}
