package com.ht.vo;

import javax.persistence.*;
/**
 * 户口性质:	 1.农业  2.非农
 * 专业类别:	 1.中技  2.高技  3.3+2
 * 学习方式：1.全日制  2.函授
 * 是否中专：1.否   2.是
 * 中专学籍：1.已退 2.保留
 * 学生状态 0退学 1在读 2毕业
 */

@Entity
@Table(name = "student")
public class StudentVO {//学生基本信息表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studId;//学生主键id
    private String passWord;//登入密码
    private String stuName;//学生姓名
    private String middleSchool;//毕业学校
    private String sex;//性别
    private String age;//年龄

    private String birthDay;//出生年月
    private String phone;//学生电话
    private String addr;//家庭地址
    private Integer clazz;//班级外键id
    private Integer huor;//宿舍外键id
    private String enterTime;//入学时间
    private String introdureTech;//介绍老师
    private Integer stat;//学生状态
    private String naTion;//民族
    private String naTives;//籍贯
    private Integer resiDence;//户口性质
    private String cardId;//身份证号
    private String professional;//就读专业
    private Integer proLevel;//专业类别
    private Integer studyType;//学习类别
    private String parents;//家长姓名
    private String parentsPhone;//家长电话
    private String intrPhone;//老师电话
    private String audiTion;//面试人
    private String auditionOption;//面试人意见
    private Integer isVocational;//是否中专
    private String vocationalsch;//中专学校
    private Integer vocationalFlag;//中专学籍
    private Integer enrollno;//省录取号
    private String qkMoney;//欠款金额
    private String score;//录取成绩
    private String dibao;//是否低保
    private String sourceType;//生源类型
    private String guarantee;//担保人
    private String soldier;//是否当兵
    private String registration;//报名号
    private String zhuxiao;//是否住校
    private String remark;//备注
    private String tuixue;//退学
    private String xiuxue;//休学
    private String stuno;//学号
    private String computer;//是否送电脑
    private String collar;//是否领用
    private String grants;//助学金

    @Override
    public String toString() {
        return "StudentVO{" +
                "studId=" + studId +
                ", passWord='" + passWord + '\'' +
                ", stuName='" + stuName + '\'' +
                ", middleSchool='" + middleSchool + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", phone='" + phone + '\'' +
                ", addr='" + addr + '\'' +
                ", clazz=" + clazz +
                ", huor=" + huor +
                ", enterTime='" + enterTime + '\'' +
                ", introdureTech='" + introdureTech + '\'' +
                ", stat=" + stat +
                ", naTion='" + naTion + '\'' +
                ", naTives='" + naTives + '\'' +
                ", resiDence=" + resiDence +
                ", cardId='" + cardId + '\'' +
                ", professional='" + professional + '\'' +
                ", proLevel=" + proLevel +
                ", studyType=" + studyType +
                ", parents='" + parents + '\'' +
                ", parentsPhone='" + parentsPhone + '\'' +
                ", intrPhone='" + intrPhone + '\'' +
                ", audiTion='" + audiTion + '\'' +
                ", auditionOption='" + auditionOption + '\'' +
                ", isVocational=" + isVocational +
                ", vocationalsch='" + vocationalsch + '\'' +
                ", vocationalFlag=" + vocationalFlag +
                ", enrollno=" + enrollno +
                ", qkMoney='" + qkMoney + '\'' +
                ", score='" + score + '\'' +
                ", dibao='" + dibao + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", guarantee='" + guarantee + '\'' +
                ", soldier='" + soldier + '\'' +
                ", registration='" + registration + '\'' +
                ", zhuxiao='" + zhuxiao + '\'' +
                ", remark='" + remark + '\'' +
                ", tuixue='" + tuixue + '\'' +
                ", xiuxue='" + xiuxue + '\'' +
                ", stuno='" + stuno + '\'' +
                ", computer='" + computer + '\'' +
                ", collar='" + collar + '\'' +
                ", grants='" + grants + '\'' +
                '}';
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getMiddleSchool() {
        return middleSchool;
    }

    public void setMiddleSchool(String middleSchool) {
        this.middleSchool = middleSchool;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getClazz() {
        return clazz;
    }

    public void setClazz(Integer clazz) {
        this.clazz = clazz;
    }

    public Integer getHuor() {
        return huor;
    }

    public void setHuor(Integer huor) {
        this.huor = huor;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getIntrodureTech() {
        return introdureTech;
    }

    public void setIntrodureTech(String introdureTech) {
        this.introdureTech = introdureTech;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public String getNaTion() {
        return naTion;
    }

    public void setNaTion(String naTion) {
        this.naTion = naTion;
    }

    public String getNaTives() {
        return naTives;
    }

    public void setNaTives(String naTives) {
        this.naTives = naTives;
    }

    public Integer getResiDence() {
        return resiDence;
    }

    public void setResiDence(Integer resiDence) {
        this.resiDence = resiDence;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public Integer getProLevel() {
        return proLevel;
    }

    public void setProLevel(Integer proLevel) {
        this.proLevel = proLevel;
    }

    public Integer getStudyType() {
        return studyType;
    }

    public void setStudyType(Integer studyType) {
        this.studyType = studyType;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getParentsPhone() {
        return parentsPhone;
    }

    public void setParentsPhone(String parentsPhone) {
        this.parentsPhone = parentsPhone;
    }

    public String getIntrPhone() {
        return intrPhone;
    }

    public void setIntrPhone(String intrPhone) {
        this.intrPhone = intrPhone;
    }

    public String getAudiTion() {
        return audiTion;
    }

    public void setAudiTion(String audiTion) {
        this.audiTion = audiTion;
    }

    public String getAuditionOption() {
        return auditionOption;
    }

    public void setAuditionOption(String auditionOption) {
        this.auditionOption = auditionOption;
    }

    public Integer getIsVocational() {
        return isVocational;
    }

    public void setIsVocational(Integer isVocational) {
        this.isVocational = isVocational;
    }

    public String getVocationalsch() {
        return vocationalsch;
    }

    public void setVocationalsch(String vocationalsch) {
        this.vocationalsch = vocationalsch;
    }

    public Integer getVocationalFlag() {
        return vocationalFlag;
    }

    public void setVocationalFlag(Integer vocationalFlag) {
        this.vocationalFlag = vocationalFlag;
    }

    public Integer getEnrollno() {
        return enrollno;
    }

    public void setEnrollno(Integer enrollno) {
        this.enrollno = enrollno;
    }

    public String getQkMoney() {
        return qkMoney;
    }

    public void setQkMoney(String qkMoney) {
        this.qkMoney = qkMoney;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDibao() {
        return dibao;
    }

    public void setDibao(String dibao) {
        this.dibao = dibao;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public String getSoldier() {
        return soldier;
    }

    public void setSoldier(String soldier) {
        this.soldier = soldier;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getZhuxiao() {
        return zhuxiao;
    }

    public void setZhuxiao(String zhuxiao) {
        this.zhuxiao = zhuxiao;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTuixue() {
        return tuixue;
    }

    public void setTuixue(String tuixue) {
        this.tuixue = tuixue;
    }

    public String getXiuxue() {
        return xiuxue;
    }

    public void setXiuxue(String xiuxue) {
        this.xiuxue = xiuxue;
    }

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
    }

    public String getCollar() {
        return collar;
    }

    public void setCollar(String collar) {
        this.collar = collar;
    }

    public String getGrants() {
        return grants;
    }

    public void setGrants(String grants) {
        this.grants = grants;
    }
}
