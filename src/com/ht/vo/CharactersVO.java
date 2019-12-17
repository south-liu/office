package com.ht.vo;

import javax.persistence.*;

@Entity
@Table(name = "characters")//角色管理
public class CharactersVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer characterId;
    private String characterName;//角色名称
    private Integer postId;//关联岗位表,给岗位设置权限

    @Override
    public String toString() {
        return "CharactersVO{" +
                "characterId=" + characterId +
                ", characterName='" + characterName + '\'' +
                ", postId=" + postId +
                '}';
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
