package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table(name = "cooperation")
public class CooperationVO {  //合作伙伴表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cooperationId;  //主键，标识列，自动生成
    private String name;  //姓名
    private String sex;  //性别
    private String tell;  //联系电话
    private String school;  //学校
    private String classes;  //班级
    private String remark;  //备注

    @Override
    public String toString() {
        return "CooperationVO{" +
                "cooperationId=" + cooperationId +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tell='" + tell + '\'' +
                ", school='" + school + '\'' +
                ", classes='" + classes + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
