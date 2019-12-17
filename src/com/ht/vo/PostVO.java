package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "post")//岗位
public class PostVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String postName;
    private String remark;
    private Integer deptId;//关联部门表主键

    @Override
    public String toString() {
        return "PostVO{" +
                "postId=" + postId +
                ", postName='" + postName + '\'' +
                ", remark='" + remark + '\'' +
                ", deptId=" + deptId +
                '}';
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
