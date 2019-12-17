package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "charModule")//角色权限管理
public class CharModuleVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer charModuleId;
    private Integer moduleId;//管理系统权限
    private Integer characterId;//关联角色表

    @Override
    public String toString() {
        return "CharModuleVO{" +
                "charModuleId=" + charModuleId +
                ", moduleId='" + moduleId + '\'' +
                ", characterId='" + characterId + '\'' +
                '}';
    }

    public Integer getCharModuleId() {
        return charModuleId;
    }

    public void setCharModuleId(Integer charModuleId) {
        this.charModuleId = charModuleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }
}
