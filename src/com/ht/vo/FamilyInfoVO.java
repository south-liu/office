package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "familyInfo")
public class FamilyInfoVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer familyId;//主键
    private String empId;//员工编号
    private String contactName;//联系人名称
    private String relationship;//关系
    private String phone;//联系电话
    private String remark;//说明

    @Override
    public String toString() {
        return "FamilyInfoVO{" +
                "familyId=" + familyId +
                ", empId='" + empId + '\'' +
                ", contactName='" + contactName + '\'' +
                ", relationship='" + relationship + '\'' +
                ", phone='" + phone + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
