package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table(name = "studentJobs")
public class StudentJobsVO {  //就业追踪表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sjId;  //成绩主键id
    private Integer studentId;  //学生表外键
    private String company;  //公司名称
    private String addr;  //公司地址
    private float saraly;  //工资待遇
    private String welfare;  //福利待遇
    private String phone;  //学生电话

    @Override
    public String toString() {
        return "StudentJobsVO{" +
                "sjId=" + sjId +
                ", studentId=" + studentId +
                ", company='" + company + '\'' +
                ", addr='" + addr + '\'' +
                ", saraly=" + saraly +
                ", welfare='" + welfare + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Integer getSjId() {
        return sjId;
    }

    public void setSjId(Integer sjId) {
        this.sjId = sjId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public float getSaraly() {
        return saraly;
    }

    public void setSaraly(float saraly) {
        this.saraly = saraly;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
