package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "empRole")//员工角色表
public class EmpRoleVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empRoleId;
    private Integer empId;//员工id
    private Integer characterId;//关联角色表

    @Override
    public String toString() {
        return "EmpRoleVO{" +
                "empRoleId=" + empRoleId +
                ", empId=" + empId +
                ", characterId=" + characterId +
                '}';
    }

    public Integer getEmpRoleId() {
        return empRoleId;
    }

    public void setEmpRoleId(Integer empRoleId) {
        this.empRoleId = empRoleId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }
}
