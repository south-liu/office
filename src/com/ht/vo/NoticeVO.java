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
    private String empId;  //发布人
    private String noticeIime;  //发布时间
    private String classIds;  //班级ID列表，逗号分割
    private String deptIds;  //部门ID列表，逗号分割

    @Override
    public String toString() {
        return "NoticeVO{" +
                "noticeId=" + noticeId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", noticeType=" + noticeType +
                ", empId='" + empId + '\'' +
                ", noticeIime='" + noticeIime + '\'' +
                ", classIds='" + classIds + '\'' +
                ", deptIds='" + deptIds + '\'' +
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

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getNoticeIime() {
        return noticeIime;
    }

    public void setNoticeIime(String noticeIime) {
        this.noticeIime = noticeIime;
    }

    public String getClassIds() {
        return classIds;
    }

    public void setClassIds(String classIds) {
        this.classIds = classIds;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }
}
