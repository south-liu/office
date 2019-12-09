package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "dept")
public class DeptVO {//部门表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;//主键，标识列，自动生成
    private String deptName;//部门名称
    private Integer parentId;//父部门名称 0则为最高部门
    private String chairman;//部门负责人
    private String remark;//备注
    private String deptType;//部门类型

    @Override
    public String toString() {
        return "DeptVO{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", parentId=" + parentId +
                ", chairman='" + chairman + '\'' +
                ", remark='" + remark + '\'' +
                ", deptType='" + deptType + '\'' +
                '}';
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }
}
