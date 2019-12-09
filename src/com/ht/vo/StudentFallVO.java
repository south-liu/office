package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "studentFall")
public class StudentFallVO {//学生届别管理表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fallId;//主键
    private String level;//届别名称
    private String remark;//说明

    @Override
    public String toString() {
        return "StudentFallVO{" +
                "fallId=" + fallId +
                ", level='" + level + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getFallId() {
        return fallId;
    }

    public void setFallId(Integer fallId) {
        this.fallId = fallId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
