package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "empAssessmentTotal")
public class EmpAssessmentTotalVO {//员工考核总表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empAssTotalId;
    private Integer empId;//关联员工id
    private Integer totalScores;//总得分
    private String remark;//备注

    @Override
    public String toString() {
        return "EmpAssessmentTotalVO{" +
                "empAssTotalId=" + empAssTotalId +
                ", empId=" + empId +
                ", totalScores=" + totalScores +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getEmpAssTotalId() {
        return empAssTotalId;
    }

    public void setEmpAssTotalId(Integer empAssTotalId) {
        this.empAssTotalId = empAssTotalId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(Integer totalScores) {
        this.totalScores = totalScores;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
