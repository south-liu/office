package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "aduitType")
public class AduitTypeVO {//考核指标类别
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aduitTypeId;//主键
    private String aduitTypeName;//考核类型名字
    private Integer deptId;//部门id

    @Override
    public String toString() {
        return "AduitTypeVO{" +
                "aduitTypeId=" + aduitTypeId +
                ", aduitTypeName='" + aduitTypeName + '\'' +
                ", deptId=" + deptId +
                '}';
    }

    public Integer getAduitTypeId() {
        return aduitTypeId;
    }

    public void setAduitTypeId(Integer aduitTypeId) {
        this.aduitTypeId = aduitTypeId;
    }

    public String getAduitTypeName() {
        return aduitTypeName;
    }

    public void setAduitTypeName(String aduitTypeName) {
        this.aduitTypeName = aduitTypeName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
