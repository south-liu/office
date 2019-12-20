package com.ht.vo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "module")//权限表
public class ModuleVO implements Serializable {
    private static final long serialVersionUID = -2706606288840173179L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moduleId;
    private String moduleName;//权限名称
    private Integer parentId;//如果是0则为根节点
    private String url;
    private String icon;

    @Override
    public String toString() {
        return "ModuleVO{" +
                "moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", parentId=" + parentId +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
