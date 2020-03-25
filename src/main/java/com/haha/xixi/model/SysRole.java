package com.haha.xixi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author pwl
 * @since 2019-06-20
 */
@Entity
public class SysRole implements Serializable {


    private static final long serialVersionUID = -4610750919637383854L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long roleId;
    private String roleName;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        ", roleId=" + roleId +
        ", roleName=" + roleName +
        "}";
    }
}
