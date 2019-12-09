package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "collegeDept")
public class CollegeDeptVO {//院系表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collegeDeptId;//id
    private String collegeDeptName;//院系名称
    private String remark;//说明

    @Override
    public String toString() {
        return "CollegeDeptVO{" +
                "collegeDeptId=" + collegeDeptId +
                ", collegeDeptName='" + collegeDeptName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getCollegeDeptId() {
        return collegeDeptId;
    }

    public void setCollegeDeptId(Integer collegeDeptId) {
        this.collegeDeptId = collegeDeptId;
    }

    public String getCollegeDeptName() {
        return collegeDeptName;
    }

    public void setCollegeDeptName(String collegeDeptName) {
        this.collegeDeptName = collegeDeptName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
