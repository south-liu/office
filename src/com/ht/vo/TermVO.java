package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "term")
public class TermVO {//学期表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer termId;//学期id
    private  String termName;//学期名称
    private  String remark;//说明

    @Override
    public String toString() {
        return "TermVO{" +
                "termId=" + termId +
                ", termName='" + termName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
