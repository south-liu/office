package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table(name = "dataDoc")
public class DataDocVO {  //资料文档表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docId;  //主键，标识列，自动生成
    private String dataName;  //资料名称
    private String url;  //资料地址
    private String optime;  //上传时间
    private String remark;  //备注
    private Integer empId;  //上传人员

    @Override
    public String toString() {
        return "DataDocVO{" +
                "docId=" + docId +
                ", dataName='" + dataName + '\'' +
                ", url='" + url + '\'' +
                ", optime='" + optime + '\'' +
                ", remark='" + remark + '\'' +
                ", empId=" + empId +
                '}';
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
}
