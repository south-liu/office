package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "emp")
public class EmpVO {//员工表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;//主键,员工编号
    private String empName;//员工姓名
    private Integer deptId;//部门编号
    private String sex;//员工性别
    private String birthday;//出生日期
    private String cardId;//身份证
    private String nation;//籍贯
    private String phone;//手机号码
    private String qqCode;//qq号码
    private String weixin;//微信号码
    private String email;//邮箱
    private String married;//是否结婚（是/否）
    private String university;//毕业学校
    private String major;//专业
    private String education;//学历
    private String address;//住址
    private String remark;//说明
    private String bank;//银行
    private String accountName;//账户名
    private String bankNumber;//卡号
    private String aliPay;//支付宝
    private String hireDay;//入职时间
    private String fireDay;//离职时间
    private String password;//账号密码
    private Integer status;//状态（0；禁用  1：正常）
    private Integer postId;//职务id
    private String postName;//职务名称

    @Override
    public String toString() {
        return "EmpVO{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", deptId=" + deptId +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", cardId='" + cardId + '\'' +
                ", nation='" + nation + '\'' +
                ", phone='" + phone + '\'' +
                ", qqCode='" + qqCode + '\'' +
                ", weixin='" + weixin + '\'' +
                ", email='" + email + '\'' +
                ", married='" + married + '\'' +
                ", university='" + university + '\'' +
                ", major='" + major + '\'' +
                ", education='" + education + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", bank='" + bank + '\'' +
                ", accountName='" + accountName + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                ", aliPay='" + aliPay + '\'' +
                ", hireDay='" + hireDay + '\'' +
                ", fireDay='" + fireDay + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", postId=" + postId +
                ", postName='" + postName + '\'' +
                '}';
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQqCode() {
        return qqCode;
    }

    public void setQqCode(String qqCode) {
        this.qqCode = qqCode;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getAliPay() {
        return aliPay;
    }

    public void setAliPay(String aliPay) {
        this.aliPay = aliPay;
    }

    public String getHireDay() {
        return hireDay;
    }

    public void setHireDay(String hireDay) {
        this.hireDay = hireDay;
    }

    public String getFireDay() {
        return fireDay;
    }

    public void setFireDay(String fireDay) {
        this.fireDay = fireDay;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
