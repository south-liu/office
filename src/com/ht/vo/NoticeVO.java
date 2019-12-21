package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table(name = "notice")
public class NoticeVO {  //公告通知明细表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeId;
    private String title;  //标题
    private String content;  //通知内容
    private Integer noticeType;  //类型：1、所有人； 2、员工； 3、学生
    private Integer empId;  //发布人
    private String noticeIime;  //发布时间

    @Override
    public String toString() {
        return "NoticeVO{" +
                "noticeId=" + noticeId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", noticeType=" + noticeType +
                ", empId='" + empId + '\'' +
                ", noticeIime='" + noticeIime + '\'' +
                '}';
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
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

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getNoticeIime() {
        return noticeIime;
    }

    public void setNoticeIime(String noticeIime) {
        this.noticeIime = noticeIime;
    }

}
