package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "aduitModel")
public class AduitModelVO {//考核指标表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aduitModelId;//主键
    private String aduitName;//考核内容
    private Integer scores;//考核分数
    private Integer deptId;//部门id
    private String remark;//说明

    @Override
    public String toString() {
        return "AduitModelVO{" +
                "aduitModelId=" + aduitModelId +
                ", aduitName='" + aduitName + '\'' +
                ", scores=" + scores +
                ", deptId=" + deptId +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getAduitModelId() {
        return aduitModelId;
    }

    public void setAduitModelId(Integer aduitModelId) {
        this.aduitModelId = aduitModelId;
    }

    public String getAduitName() {
        return aduitName;
    }

    public void setAduitName(String aduitName) {
        this.aduitName = aduitName;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
