package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "studentFloor")
public class StudentFloorVO {//楼栋维护表
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer floorId;//楼栋主键id
    private String floorName;//楼栋名称

    @Override
    public String toString() {
        return "StudentFloorVO{" +
                "floorId=" + floorId +
                ", floorName='" + floorName + '\'' +
                '}';
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }
}
