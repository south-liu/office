package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "studentHuor")
public class StudentHuorVO {//宿舍资料
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hourId;//宿舍主键id
    private String huorName;//宿舍房号(508)
    private String address;//宿舍地址
    private Integer count;//宿舍人数
    private Integer floorId;//楼栋id外键
    private Integer numberBeds;//床位数
    private Integer huoeIddsc;//序号

    @Override
    public String toString() {
        return "StudentHuorVO{" +
                "hourId=" + hourId +
                ", huorName='" + huorName + '\'' +
                ", address='" + address + '\'' +
                ", count=" + count +
                ", floorId=" + floorId +
                ", numberBeds=" + numberBeds +
                ", huoeIddsc=" + huoeIddsc +
                '}';
    }

    public Integer getHourId() {
        return hourId;
    }

    public void setHourId(Integer hourId) {
        this.hourId = hourId;
    }

    public String getHuorName() {
        return huorName;
    }

    public void setHuorName(String huorName) {
        this.huorName = huorName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Integer getNumberBeds() {
        return numberBeds;
    }

    public void setNumberBeds(Integer numberBeds) {
        this.numberBeds = numberBeds;
    }

    public Integer getHuoeIddsc() {
        return huoeIddsc;
    }

    public void setHuoeIddsc(Integer huoeIddsc) {
        this.huoeIddsc = huoeIddsc;
    }
}
