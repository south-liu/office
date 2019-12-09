package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "aduitLog")
public class AduitLogVO {//巡查数据表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aduitLogId;//主键
    private Integer aduitModelId;//考核指标表id
    private Integer empId;//员工id
    private Integer score;//考核分数
    private String auditDate;//考核时间
    private String remark;//说明
    private String auditPerson;//录入人
    private String image;//图片

    @Override
    public String toString() {
        return "AduitLogVO{" +
                "aduitLogId=" + aduitLogId +
                ", aduitModelId=" + aduitModelId +
                ", empId=" + empId +
                ", score=" + score +
                ", auditDate='" + auditDate + '\'' +
                ", remark='" + remark + '\'' +
                ", auditPerson='" + auditPerson + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Integer getAduitLogId() {
        return aduitLogId;
    }

    public void setAduitLogId(Integer aduitLogId) {
        this.aduitLogId = aduitLogId;
    }

    public Integer getAduitModelId() {
        return aduitModelId;
    }

    public void setAduitModelId(Integer aduitModelId) {
        this.aduitModelId = aduitModelId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
