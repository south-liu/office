package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "emailReceiver")
public class EmailReceiverVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer emailReceiverId;
    private Integer emailId;//邮件id
    private Integer receiver;//接收人id
    private Integer isRead;//是否已读 0未读 1已读

    @Override
    public String toString() {
        return "EmailReceiverVO{" +
                "emailReceiverId=" + emailReceiverId +
                ", emailId=" + emailId +
                ", receiver=" + receiver +
                ", isRead=" + isRead +
                '}';
    }

    public Integer getEmailReceiverId() {
        return emailReceiverId;
    }

    public void setEmailReceiverId(Integer emailReceiverId) {
        this.emailReceiverId = emailReceiverId;
    }

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
