package com.ht.vo;

import javax.persistence.*;

/**
 * Created by shkstart on 2019/12/3.
 */
@Entity
@Table(name = "systemLog")
public class SystemLogVO {  //系统日志管理表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;
    private Integer empId;  //关联员工表主键
    private String ipAddr;  //Ip地址
    private String optime;  //操作时间
    private String tables;  //操作的数据表
    private String msg;  //操作内容

    @Override
    public String toString() {
        return "SystemLogVO{" +
                "logId=" + logId +
                ", empId=" + empId +
                ", ipAddr='" + ipAddr + '\'' +
                ", optime='" + optime + '\'' +
                ", tables='" + tables + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
