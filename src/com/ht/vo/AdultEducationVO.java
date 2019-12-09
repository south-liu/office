package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table(name = "adultEducation")
public class AdultEducationVO {  //成教报名管理
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adultId;  //主键
    private String stuName;  //姓名
    private String sex;  //性别
    private String political;  //政治面貌
    private String document;  //证件类型
    private String card;  //证件号码
    private String birthday;  //出生日期
    private String nation;  //民族
    private String service;  //在职状况
    private String distribution;  //分布情况
    private String marital;  //婚姻状况
    private String tuition;  //学费来源
    private String account;  //户口性质
    private String birthplace;  //籍贯
    private String household;  //户籍所在地
    private String phone;  //手机号码
    private String qq;  //QQ号码
    private String email;  //E-mail
    private String addr;  //通信地址
    private String code;  //邮编
    private String profession;  //专业层次
    private String professionname;  //专业名称
    private String techname;  //教学点名称
    private String isgraduation;  //是否高中毕业(是/否)
    private String originaldegre;  //原学历层次
    private String graduatedschool;  //原毕业学校
    private String graduationtime;  //毕业时间
    private String originalsubject;  //原学科
    private String subjectcategory;  //原学科门类
    private String originaldegree;  //原学历学习类型
    private String major;  //原学历所学专业
    private String diploma;  //原学历毕业证书编号
    private String evidence;  //原学历证明材料
    private String materialcode;  //证明材料编号
    private String academicname;  //原学历姓名
    private String idnumber;  //原学历证件号码
    private String grade;  //入学测试成绩
    private String topImage;  //头像地址
    private String dataImage;  //附件地址
    private String Status;  //录取状态（未录取，已录取）
    private String biyehao;  //毕业证号
    private String kaoshao;  //考生号
    private String zhuanye;  //专业代号
    private String xuezhi;  //学制
    private String xuexi;  //学习形式
    private String zhuantai;  //状态
    private float yuding;  //预定报名费
    private float payment;  //付款
    private float arrears;  //欠款
    private String remark;  //备注
    private Integer fallId;  //界别Id
    private Integer appId;  //关联报考学校

    @Override
    public String toString() {
        return "AdultEducationVO{" +
                "adultId=" + adultId +
                ", stuName='" + stuName + '\'' +
                ", sex='" + sex + '\'' +
                ", political='" + political + '\'' +
                ", document='" + document + '\'' +
                ", card='" + card + '\'' +
                ", birthday='" + birthday + '\'' +
                ", nation='" + nation + '\'' +
                ", service='" + service + '\'' +
                ", distribution='" + distribution + '\'' +
                ", marital='" + marital + '\'' +
                ", tuition='" + tuition + '\'' +
                ", account='" + account + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", household='" + household + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", addr='" + addr + '\'' +
                ", code='" + code + '\'' +
                ", profession='" + profession + '\'' +
                ", professionname='" + professionname + '\'' +
                ", techname='" + techname + '\'' +
                ", isgraduation='" + isgraduation + '\'' +
                ", originaldegre='" + originaldegre + '\'' +
                ", graduatedschool='" + graduatedschool + '\'' +
                ", graduationtime='" + graduationtime + '\'' +
                ", originalsubject='" + originalsubject + '\'' +
                ", subjectcategory='" + subjectcategory + '\'' +
                ", originaldegree='" + originaldegree + '\'' +
                ", major='" + major + '\'' +
                ", diploma='" + diploma + '\'' +
                ", evidence='" + evidence + '\'' +
                ", materialcode='" + materialcode + '\'' +
                ", academicname='" + academicname + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", grade='" + grade + '\'' +
                ", topImage='" + topImage + '\'' +
                ", dataImage='" + dataImage + '\'' +
                ", Status='" + Status + '\'' +
                ", biyehao='" + biyehao + '\'' +
                ", kaoshao='" + kaoshao + '\'' +
                ", zhuanye='" + zhuanye + '\'' +
                ", xuezhi='" + xuezhi + '\'' +
                ", xuexi='" + xuexi + '\'' +
                ", zhuantai='" + zhuantai + '\'' +
                ", yuding=" + yuding +
                ", payment=" + payment +
                ", arrears=" + arrears +
                ", remark='" + remark + '\'' +
                ", fallId=" + fallId +
                ", appId=" + appId +
                '}';
    }

    public Integer getAdultId() {
        return adultId;
    }

    public void setAdultId(Integer adultId) {
        this.adultId = adultId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getTuition() {
        return tuition;
    }

    public void setTuition(String tuition) {
        this.tuition = tuition;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getHousehold() {
        return household;
    }

    public void setHousehold(String household) {
        this.household = household;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfessionname() {
        return professionname;
    }

    public void setProfessionname(String professionname) {
        this.professionname = professionname;
    }

    public String getTechname() {
        return techname;
    }

    public void setTechname(String techname) {
        this.techname = techname;
    }

    public String getIsgraduation() {
        return isgraduation;
    }

    public void setIsgraduation(String isgraduation) {
        this.isgraduation = isgraduation;
    }

    public String getOriginaldegre() {
        return originaldegre;
    }

    public void setOriginaldegre(String originaldegre) {
        this.originaldegre = originaldegre;
    }

    public String getGraduatedschool() {
        return graduatedschool;
    }

    public void setGraduatedschool(String graduatedschool) {
        this.graduatedschool = graduatedschool;
    }

    public String getGraduationtime() {
        return graduationtime;
    }

    public void setGraduationtime(String graduationtime) {
        this.graduationtime = graduationtime;
    }

    public String getOriginalsubject() {
        return originalsubject;
    }

    public void setOriginalsubject(String originalsubject) {
        this.originalsubject = originalsubject;
    }

    public String getSubjectcategory() {
        return subjectcategory;
    }

    public void setSubjectcategory(String subjectcategory) {
        this.subjectcategory = subjectcategory;
    }

    public String getOriginaldegree() {
        return originaldegree;
    }

    public void setOriginaldegree(String originaldegree) {
        this.originaldegree = originaldegree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    public String getAcademicname() {
        return academicname;
    }

    public void setAcademicname(String academicname) {
        this.academicname = academicname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getBiyehao() {
        return biyehao;
    }

    public void setBiyehao(String biyehao) {
        this.biyehao = biyehao;
    }

    public String getKaoshao() {
        return kaoshao;
    }

    public void setKaoshao(String kaoshao) {
        this.kaoshao = kaoshao;
    }

    public String getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(String zhuanye) {
        this.zhuanye = zhuanye;
    }

    public String getXuezhi() {
        return xuezhi;
    }

    public void setXuezhi(String xuezhi) {
        this.xuezhi = xuezhi;
    }

    public String getXuexi() {
        return xuexi;
    }

    public void setXuexi(String xuexi) {
        this.xuexi = xuexi;
    }

    public String getZhuantai() {
        return zhuantai;
    }

    public void setZhuantai(String zhuantai) {
        this.zhuantai = zhuantai;
    }

    public float getYuding() {
        return yuding;
    }

    public void setYuding(float yuding) {
        this.yuding = yuding;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public float getArrears() {
        return arrears;
    }

    public void setArrears(float arrears) {
        this.arrears = arrears;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFallId() {
        return fallId;
    }

    public void setFallId(Integer fallId) {
        this.fallId = fallId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }
}
