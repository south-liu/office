package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "chatRecord")
public class ChatRecordVO {//谈心记录表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  chatId;//谈心主键id
    private Integer  sayFace;//学生表外键
    private Integer  teacher;//员工表外键
    private String address;//地址
    private String sayScon;//谈心内容
    private String chatDate;//时间


    @Override
    public String toString() {
        return "ChatRecordVO{" +
                "chatId=" + chatId +
                ", sayFace=" + sayFace +
                ", teacher=" + teacher +
                ", address='" + address + '\'' +
                ", sayScon='" + sayScon + '\'' +
                ", chatDate='" + chatDate + '\'' +
                '}';
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getSayFace() {
        return sayFace;
    }

    public void setSayFace(Integer sayFace) {
        this.sayFace = sayFace;
    }

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSayScon() {
        return sayScon;
    }

    public void setSayScon(String sayScon) {
        this.sayScon = sayScon;
    }

    public String getChatDate() {
        return chatDate;
    }

    public void setChatDate(String chatDate) {
        this.chatDate = chatDate;
    }
}
