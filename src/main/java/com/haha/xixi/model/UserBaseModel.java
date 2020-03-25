package com.haha.xixi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cbm_mag_user")
@JsonIgnoreProperties(value = { "extInfo" })//避免输出前台后循环引用
public class UserBaseModel extends BaseModel implements UserDetails {

    private static final long serialVersionUID = 4050134674659471343L;

    private String username;// 登录名

    private String password;// 密码

    @OneToOne(mappedBy = "baseuser")
    private UserBaseExtModel extInfo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cbm_mag_l_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    private Set<RoleModel> roleSet;


    public UserBaseModel() {

    }

    public UserBaseModel(String username, String password, UserBaseExtModel ext) {
        super();
        this.username = username;
        this.password = password;
        this.extInfo = ext;
        this.creatDate = this.modifyDate = new Date();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserBaseExtModel getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(UserBaseExtModel extInfo) {
        this.extInfo = extInfo;
    }

    public Set<RoleModel> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<RoleModel> roleSet) {
        this.roleSet = roleSet;
    }
}
