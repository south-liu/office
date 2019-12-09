package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table
public class FinanceshouldTuitionRecordVO {  //自动生产学生应收费记录表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer financeId;  //主键，标识列，自动生成
    private Integer financedate;  //缴费日期/退款日期
    private Integer stuId;  //关联学生表的主键
    private Integer termId;  //标示那个学期的收费”第一学期”，”第二学期”，”第三学期”，”第四学期”，”第五学期”,外键关联到学期表主键
    private String receipt;  //支付方式
    private Integer financeType;  //1:缴费，2：退款
    private float factMoney;  //实交金额(正)/退款金额(负)
    private Integer empId;  //(收款人)员工id关联员工表
    private String remark;  //说明
    private Integer invalid;  //1:有效，2：作废
    private Integer tuitionTypeId;  //关联收费项

    @Override
    public String toString() {
        return "FinanceshouldTuitionRecordVO{" +
                "financeId=" + financeId +
                ", financedate=" + financedate +
                ", stuId=" + stuId +
                ", termId=" + termId +
                ", receipt='" + receipt + '\'' +
                ", financeType=" + financeType +
                ", factMoney=" + factMoney +
                ", empId=" + empId +
                ", remark='" + remark + '\'' +
                ", invalid=" + invalid +
                ", tuitionTypeId=" + tuitionTypeId +
                '}';
    }

    public Integer getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Integer financeId) {
        this.financeId = financeId;
    }

    public Integer getFinancedate() {
        return financedate;
    }

    public void setFinancedate(Integer financedate) {
        this.financedate = financedate;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Integer getFinanceType() {
        return financeType;
    }

    public void setFinanceType(Integer financeType) {
        this.financeType = financeType;
    }

    public float getFactMoney() {
        return factMoney;
    }

    public void setFactMoney(float factMoney) {
        this.factMoney = factMoney;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getInvalid() {
        return invalid;
    }

    public void setInvalid(Integer invalid) {
        this.invalid = invalid;
    }

    public Integer getTuitionTypeId() {
        return tuitionTypeId;
    }

    public void setTuitionTypeId(Integer tuitionTypeId) {
        this.tuitionTypeId = tuitionTypeId;
    }
}
