package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "studentFamily")
public class StudentFamilyVO {//学生家庭情况表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer familyId;//家庭主键id
    private Integer stuId;//学生表外键
    private String familyName;//亲属姓名
    private String relaTion;//与学生关系
    private String familyPhone;//亲属电话

    @Override
    public String toString() {
        return "StudentFamilyVO{" +
                "familyId=" + familyId +
                ", stuId=" + stuId +
                ", familyName='" + familyName + '\'' +
                ", relaTion='" + relaTion + '\'' +
                ", familyPhone='" + familyPhone + '\'' +
                '}';
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getRelaTion() {
        return relaTion;
    }

    public void setRelaTion(String relaTion) {
        this.relaTion = relaTion;
    }

    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }
}
