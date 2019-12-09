package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "major")
public class MajorVO {//专业表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer majorId;
    private String majorName;//专业名称
    private Integer collegeDeptId;//院系id
    private String remark;//说明

    @Override
    public String toString() {
        return "MajorVO{" +
                "majorId=" + majorId +
                ", majorName='" + majorName + '\'' +
                ", collegeDeptId=" + collegeDeptId +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getCollegeDeptId() {
        return collegeDeptId;
    }

    public void setCollegeDeptId(Integer collegeDeptId) {
        this.collegeDeptId = collegeDeptId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
