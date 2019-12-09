package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "project") //项目答辩名称表
public class ProjectVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    private String projectName;//项目名称
    private String remark;//说明

    @Override
    public String toString() {
        return "ProjectVO{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
