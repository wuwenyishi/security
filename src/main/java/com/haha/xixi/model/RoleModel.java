package com.haha.xixi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cbm_mag_role")
public class RoleModel extends BaseModel implements GrantedAuthority {

    private static final long serialVersionUID = 1339819643178639201L;

    private String name;

    private Integer status = 0;// 状态是否停用 1、停用；0=正常

    private String description;//描述


    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "cbm_mag_l_user_role", 
               joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
               inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<UserBaseExtModel> userSet;


    @Override
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserBaseExtModel> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<UserBaseExtModel> userSet) {
        this.userSet = userSet;
    }
}
