package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "stuStatusSet")//学生状态
public class StuStatusSetVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId;
    private String statusName;//状态名称：意向学生、预定报名学生、试学学生、在读学生、已毕业学生
    private String remark;

    @Override
    public String toString() {
        return "StuStatusSetVO{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
