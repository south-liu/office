package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "classType")//班级类别表
public class ClassTypeVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classTypeId;
    private String classTypeName;

    @Override
    public String toString() {
        return "ClassTypeVO{" +
                "classTypeId=" + classTypeId +
                ", classTypeName='" + classTypeName + '\'' +
                '}';
    }

    public Integer getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
    }

    public String getClassTypeName() {
        return classTypeName;
    }

    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }
}
