package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "email") //邮件
public class EmailVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer emailId;
    private String title;//标题
    private String content;//内容
    private String sendTime;//发送时间
    private Integer empId;//发送人
    private String url;//附件地址
    private String fileName;//附件名字

    @Override
    public String toString() {
        return "EmailVO{" +
                "emailId=" + emailId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", empId=" + empId +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    public Integer getEmailId() {
        return emailId;
    }

    public void setEmailId(Integer emailId) {
        this.emailId = emailId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
