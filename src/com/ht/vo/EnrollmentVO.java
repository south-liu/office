package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table(name = "enrollment")
public class EnrollmentVO {  //招生信息表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enrollmentId;  //主键，标识列，自动生成
    private String studName;  //姓名
    private String card;  //身份证
    private String sex;  //性别
    private String tell;  //手机号
    private String qq;  //QQ号码
    private String school;  //学校
    private String classes;  //班级
    private float amount;  //预定报名费
    private String computer;  //是否送电脑(是/否)
    private String testTime;  //试学时间
    private String startTime;  //入学时间
    private String signDate;  //录入时间
    private String luruId;  //录入人
    private Integer empId;  //员工id关联员工表
    private Integer status;  //关联学生状态表
    private String remark;  //备注
    private Integer studType;  //班级类别,关联班级类别表
    private String paymentTime;  //缴预定报名费时间
    private float score;  //入学成绩
    private float enrollMoney;  //发放金额
    private String enrollMoneyTime;  //发放时间
    private String reviewStatus;  //预定报名费审核
    private String negativeName;  //该字段用于招生老师没有账号时使用字段
    private String reviewer;  //审核人
    private String reviewerTime;  //审核时间
    private Integer majorId;  //关联专业id

    @Override
    public String toString() {
        return "EnrollmentVO{" +
                "enrollmentId=" + enrollmentId +
                ", studName='" + studName + '\'' +
                ", card='" + card + '\'' +
                ", sex='" + sex + '\'' +
                ", tell='" + tell + '\'' +
                ", qq='" + qq + '\'' +
                ", school='" + school + '\'' +
                ", classes='" + classes + '\'' +
                ", amount=" + amount +
                ", computer='" + computer + '\'' +
                ", testTime='" + testTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", signDate='" + signDate + '\'' +
                ", luruId='" + luruId + '\'' +
                ", empId=" + empId +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", studType=" + studType +
                ", paymentTime='" + paymentTime + '\'' +
                ", score=" + score +
                ", enrollMoney=" + enrollMoney +
                ", enrollMoneyTime='" + enrollMoneyTime + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", negativeName='" + negativeName + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", reviewerTime='" + reviewerTime + '\'' +
                ", majorId=" + majorId +
                '}';
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getLuruId() {
        return luruId;
    }

    public void setLuruId(String luruId) {
        this.luruId = luruId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStudType() {
        return studType;
    }

    public void setStudType(Integer studType) {
        this.studType = studType;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getEnrollMoney() {
        return enrollMoney;
    }

    public void setEnrollMoney(float enrollMoney) {
        this.enrollMoney = enrollMoney;
    }

    public String getEnrollMoneyTime() {
        return enrollMoneyTime;
    }

    public void setEnrollMoneyTime(String enrollMoneyTime) {
        this.enrollMoneyTime = enrollMoneyTime;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getNegativeName() {
        return negativeName;
    }

    public void setNegativeName(String negativeName) {
        this.negativeName = negativeName;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getReviewerTime() {
        return reviewerTime;
    }

    public void setReviewerTime(String reviewerTime) {
        this.reviewerTime = reviewerTime;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }
}
